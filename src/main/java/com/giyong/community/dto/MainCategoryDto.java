package com.giyong.community.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.util.Date;

@Data
public class MainCategoryDto {
    private Long mainCategoryId;
    private String mainCategoryName;
    private String creater;
    private Date createdAt;
    private Date updatedAt;
}
