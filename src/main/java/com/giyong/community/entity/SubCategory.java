package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class SubCategory {
    @Id
    @GeneratedValue
    private int subCategoryId;
    @JsonBackReference
    @ManyToOne
    private MainCategory mainCategory;
    private String subCategoryName;
    private String creater;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
