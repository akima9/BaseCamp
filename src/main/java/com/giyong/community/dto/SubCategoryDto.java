package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubCategoryDto {
    private int subCategoryId;
    private int mainCategoryId;
    private String subCategoryName;
    private String creater;
    private Date createdAt;
    private Date updatedAt;
}
