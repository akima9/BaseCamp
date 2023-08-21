package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class MainCategory {
    @Id
    @GeneratedValue
    private int mainCategoryId;
    private String mainCategoryName;
    @JsonManagedReference
    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.REMOVE)
    private List<SubCategory> subCategories;
    private String creater;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
