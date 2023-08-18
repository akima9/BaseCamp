package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SubCategory {
    @Id
    @GeneratedValue
    private int subCategoryId;
    private int mainCategoryId;
    private int subCategoryName;
    private String creater;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
