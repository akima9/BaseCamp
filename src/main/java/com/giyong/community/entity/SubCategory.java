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
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;
    @JsonBackReference
    @ManyToOne
    private MainCategory mainCategory;
    private String subCategoryName;
    private String creater;
    @JsonManagedReference
    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.REMOVE)
    private List<Board> boards;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
