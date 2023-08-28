package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"mainCategories", "subCategories"})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminId;
    private String adminPw;
    private String adminName;
    @JsonManagedReference
    @OneToMany(mappedBy = "admin")
    private List<MainCategory> mainCategories;
    @JsonManagedReference
    @OneToMany(mappedBy = "admin")
    private List<SubCategory> subCategories;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
