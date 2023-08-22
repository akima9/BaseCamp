package com.giyong.community.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class MemberDto {
    private Long id;
    private String memberId;
    private String memberPw;
    private String confirmPw;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
