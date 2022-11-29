package com.ku.business.repository.hibernate;

import com.ku.business.HibernateUtil;
import com.ku.business.entity.Storage;
import com.ku.business.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StorageRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Storage s
            LEFT JOIN FETCH s.company
            LEFT JOIN FETCH s.service
        WHERE s.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Storage";

    private final SessionFactory sessionFactory;

    public StorageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Storage findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Storage.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception s) {
            throw new RepositoryException(String.format("Can't find storage with id=%d!", id), s);
        }
    }

    public List<Storage> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Storage.class).list();
        } catch (Exception e) {
            throw new RepositoryException("Table storages is empty!", e);
        }
    }

    public void save(Storage storage) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.persist(storage);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Failed to save storages where id = %d!", storage.getId()), e);
            }
        }
    }

    public void update(Storage storage) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.merge(storage);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't update storage with id=%d. This storage is not exist!", storage.getId()), e);
            }
        }
    }

    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.remove(id);
                session.getTransaction().commit();
            } catch (RepositoryException e) {
                session.getTransaction().rollback();
                throw new RepositoryException(String.format("Can't delete storage with id=%d. This storage is not exist!", id), e);
            }
        }
    }
}