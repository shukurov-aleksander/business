package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Schema(description = "Content data transfer object")
@Accessors(chain = true)
public class ContentDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Content quantity", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long quantity;
    @Schema(description = "Content service", requiredMode = Schema.RequiredMode.REQUIRED)
    private ServiceDto service;
    @Schema(description = "Content orders", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<OrderListDto> orders;
}