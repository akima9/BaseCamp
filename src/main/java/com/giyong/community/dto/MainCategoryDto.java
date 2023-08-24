package com.giyong.community.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MainCategoryDto {
    private Long mainCategoryId;
    private String mainCategoryName;
    private Long adminId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
