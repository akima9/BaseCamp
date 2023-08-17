package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {
    private int categoryId;
    private int parentCategoryId;
    private String categoryName;
    private Date createdAt;
    private Date updatedAt;
}
