package com.giyong.community.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class AdminDto {
    private Long id;
    private String adminId;
    private String adminPw;
    private String confirmPw;
    private String adminName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
