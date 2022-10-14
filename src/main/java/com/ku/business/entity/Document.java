package com.ku.business.entity;

public class Document {
    private Long id;
    private Order orderId;
    private String documentContent;

    public Document() {
    }

    public Document(Long id, Order orderId, String documentContent) {
        this.id = id;
        this.orderId = orderId;
        this.documentContent = documentContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    @Override
    public boolean equals(Object aThat) {
        if (this == aThat) {return true;}
        if (aThat == null || getClass() != aThat.getClass()) {return false;}
        Document documents = (Document) aThat;
        return id.equals(documents.id) &&
                orderId.equals(documents.orderId) &&
                documentContent.equals(documents.documentContent);
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (orderId.getId() == null ? 0 : orderId.hashCode());
        result = prime * result + (documentContent == null ? 0 : documentContent.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return  this.getClass().getSimpleName() + " [" +
                "id=" + id +
                ", orderId=" + orderId.toString() +
                " , documentContent='" + documentContent +"']";
    }
}