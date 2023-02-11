package com.ku.business.repository.spring;

import com.ku.business.entity.Document;
import com.ku.business.entity.Order;
import com.ku.business.entity.OrderStatus;
import com.ku.business.exception.RepositoryException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ku.business.repository.hibernate.Repository.DOCUMENT_CONTENT_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_COMPLETED_AT_UTC_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_CREATED_AT_UTC_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_ID_COLUMN;
import static com.ku.business.repository.hibernate.Repository.ORDER_STATUS_TYPE_COLUMN;

@Component
public class DocumentRepository implements CrudRepository<Document> {
    private final Connection connection;
    public static final String FIND_BY_ID_QUERY = """
        SELECT d.id, d.order_id, d.document_content,
            o.id order_id, o.order_status order_status, o.created_at_utc created_at_utc, 
            o.completed_at_utc completed_at_utc
        FROM documents d
        LEFT JOIN orders o ON d.order_id = o.id
        WHERE d.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM documents";
    public static final String DELETE_BY_ID_QUERY = "DELETE FROM documents WHERE id = ?";
    public static final String INSERT_QUERY = """
        INSERT INTO documents (order_id, document_content) 
        VALUES (?,?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE documents
        SET order_id = ?, document_content = ? 
        WHERE id = ?
    """;
    public DocumentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Document> findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return buildDocuments(resultSet);
            }
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find Document with id=%d", id), s);
        }
    }
    private Optional<Document> buildDocuments(ResultSet resultSet) throws Exception {
        Document document = buildDocumentsWithoutEntities(resultSet);
        if (resultSet.getString(ORDER_ID_COLUMN) != null) {
            document.setOrder(buildOrder(resultSet));
        }
        return Optional.of(document);
    }

    private Document buildDocumentsWithoutEntities(ResultSet resultSet) throws Exception {
        Document document = new Document();
        document.setId(resultSet.getLong(ID_COLUMN));
        document.setDocumentContent(resultSet.getString(DOCUMENT_CONTENT_COLUMN));
        return document;
    }

    private Order buildOrder(ResultSet resultSet) throws Exception {
        Order order = new Order();
        order.setId(resultSet.getLong(ORDER_ID_COLUMN));
        order.setCreatedAtUtc((resultSet.getTimestamp(ORDER_CREATED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setCompletedAtUtc((resultSet.getTimestamp(ORDER_COMPLETED_AT_UTC_COLUMN)).toLocalDateTime());
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS_TYPE_COLUMN)));
        return order;
    }
    @Override
    public List<Document> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Document> documents = new ArrayList<>();
            while (resultSet.next()) {
                documents.add(buildDocumentsWithoutEntities(resultSet));
            }
            return documents;
        } catch (Exception e) {
            throw new RepositoryException("Table details is empty!", e);
        }
    }

    @Override
    public void update(Document document, Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            makeQueryForInsertOrUpdateDocuments(document, preparedStatement);
            preparedStatement.setLong(3, document.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Can't update Document with id=%d", document.getId()), e);
        }
    }
    public PreparedStatement makeQueryForInsertOrUpdateDocuments(Document document, PreparedStatement preparedStatement) throws Exception {
        preparedStatement.setLong(1, document.getOrder().getId());
        preparedStatement.setString(2, document.getDocumentContent());
        return preparedStatement;
    }
    @Override
    public void save(Document document) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            makeQueryForInsertOrUpdateDocuments(document, preparedStatement).executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException("Try to save document with null document content or order id", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException(String.format("Can't find document with id=%d", id), e);
        }
    }
}
