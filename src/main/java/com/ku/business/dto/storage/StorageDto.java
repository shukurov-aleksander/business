package com.ku.business.dto.storage;

import com.ku.business.dto.company.CompanyDto;
import com.ku.business.dto.service.ServiceDto;

public class StorageDto {
    Long id;
    Integer quantity;
    CompanyDto company;
    ServiceDto service;

    public StorageDto(
            Long id,
            Integer quantity,
            CompanyDto company,
            ServiceDto service
    ) {
        this.id = id;
        this.quantity = quantity;
        this.company = company;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
        this.service = service;
    }
}