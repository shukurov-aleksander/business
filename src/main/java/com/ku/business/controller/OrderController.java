package com.ku.business.controller;

import com.ku.business.dto.OrderDto;
import com.ku.business.dto.OrderListDto;
import com.ku.business.dto.OrderSaveDto;
import com.ku.business.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@Tag(name = "Orders", description = "Orders information")
@RequestMapping("/orders")
public class OrderController {
    private OrderService service;

    @GetMapping("{id}")
    @Operation(summary = "Find order by id")
    public OrderDto findById(
        @Parameter(description = "Order id", required = true, example = "1")
        @PathVariable Long id
    ) {
        return service.findById(id).get();
    }

    @GetMapping
    @Operation(summary = "Find orders")
    public List<OrderListDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @Operation(summary = "Save order")
    public void save(OrderSaveDto order) {
        service.save(order);
    }

    @PutMapping
    @Operation(summary = "Update order")
    public void update(OrderSaveDto order) {
        service.update(order);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete order by id")
    public void delete(
        @Parameter(description = "Order id", required = true, example = "1")
        @PathVariable Long id
    ) {
        service.delete(id);
    }

    @Autowired
    public void setService(OrderService service) {
        this.service = service;
    }
}
