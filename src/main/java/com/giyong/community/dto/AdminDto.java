package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdminDto {
    private String adminId;
    private String adminPw;
    private String confirmPw;
    private String adminName;
    private Date createdAt;
    private Date updatedAt;
}
