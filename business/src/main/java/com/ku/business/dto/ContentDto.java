package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Schema(description = "Content data transfer object")
@Accessors(chain = true)
public class ContentDto {
    @Schema(description = "Id", required = true, example = "1")
    private Long id;
    @Schema(description = "Content quantity", required = true, example = "1")
    private Long quantity;
    @Schema(description = "Content service", required = true)
    private ServiceDto service;
    @Schema(description = "Content orders", required = true)
    private Set<OrderListDto> orders;
}