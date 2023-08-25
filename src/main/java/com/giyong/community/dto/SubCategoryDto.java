package com.giyong.community.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class SubCategoryDto {
    private Long subCategoryId;
    private String subCategoryName;
    private Long mainCategoryId;
    private Long adminId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
