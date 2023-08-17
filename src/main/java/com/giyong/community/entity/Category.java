package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private int categoryId;
    private int parentCategoryId;
    private String categoryName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
