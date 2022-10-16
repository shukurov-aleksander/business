package com.ku.business.entity;


import java.util.List;

public class Content {
    private Long id;
    private Long quantity;
    private Service serviceId;
    private List<Order> orders;

    public Content() {
    }

    public Content(Long id, Long quantity, Service serviceId, List<Order> orders) {
        this.id = id;
        this.quantity = quantity;
        this.serviceId = serviceId;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (obj == null) {return false;}
        if (getClass() != obj.getClass()) {return false;}
        Content aThat = (Content) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {return false;}
        } else if (!getId().equals(aThat.getId())) {return false;}

        if (getQuantity() == null) {
            if (aThat.getQuantity() != null) {return false;}
        } else if (!getQuantity().equals(aThat.getQuantity())) {return false;}

        if (getServiceId() == null) {
            if (aThat.getServiceId() != null) {return false;}
        } else if (!getServiceId().equals(aThat.getServiceId())) {return false;}

        if ((getOrders() == null && aThat.getOrders() != null) || (getOrders() != null && aThat.getOrders() == null)) {return false;}
        else if (getOrders() != null && aThat.getOrders() != null) {
            for (int i = 0; i < getOrders().size(); i++) {
                if (!getOrders().get(i).getId().equals(aThat.getOrders().get(i).getId())) {return false;}
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        int prime = 31;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (quantity == null ? 0 : quantity.hashCode());
        result = prime * result + (serviceId == null ? 0 : serviceId.hashCode());
        if(orders != null) {
            for (Order order : orders) {
                result = prime * result + (order != null && order.getId() != null ?  (order.getId().hashCode()) : 0);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName() + " {id=" + getId() + ", quantity=" +
                getQuantity() + ", serviceID=" + getServiceId() + "} contains [");
        if (!getOrders().isEmpty()) {
            for (Order order: orders) {
                stringBuilder.append("detail {" + order.getId() + "}, ");
            }
        }
        stringBuilder.setLength(stringBuilder.length()-2);
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }
}
