package com.ku.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Schema(description = "Storage save or update data transfer object")
@Accessors(chain = true)
public class StorageSaveDto {
    @Schema(description = "Id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;
    @Schema(description = "Storage quantity", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer quantity;
}