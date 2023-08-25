package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @OneToMany(mappedBy = "mainCategory")
    private List<SubCategory> subCategories;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
