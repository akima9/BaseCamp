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
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;
    private String subCategoryName;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @JsonManagedReference
    @OneToMany(mappedBy = "subCategory")
    private List<Board> boards;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
