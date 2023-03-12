package com.ku.business.dto;

import com.ku.business.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Schema(description = "Order save or update data transfer object")
@Accessors(chain = true)
public class OrderSaveDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Order status", required = true, example = "CREATED")
    private OrderStatus orderStatus;
    @Schema(description = "Order created at", required = true, example = "2020-07-18 06:20:29.277")
    private LocalDateTime createdAtUtc;
    @Schema(description = "Order completed at", required = true, example = "2023-01-21 06:20:29.277")
    private LocalDateTime completedAtUtc;
}