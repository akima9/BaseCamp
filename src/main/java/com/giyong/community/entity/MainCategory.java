package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainCategoryId;
    private String mainCategoryName;
    @JsonManagedReference
    @OneToMany(mappedBy = "mainCategory", cascade = CascadeType.REMOVE)
    private List<SubCategory> subCategories;
    private String creater;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
