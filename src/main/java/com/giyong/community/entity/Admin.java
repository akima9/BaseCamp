package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminId;
    private String adminPw;
    private String adminName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
