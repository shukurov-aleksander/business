package com.ku.business.dto;

import com.ku.business.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Detail list data transfer object for list of the companies")
@Accessors(chain = true)
public class DetailListDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Detail order", requiredMode = Schema.RequiredMode.REQUIRED)
    private OrderDto order;
    @Schema(description = "Detail operation type", requiredMode = Schema.RequiredMode.REQUIRED, example = "PURCHASE")
    private OperationType operationType;
}