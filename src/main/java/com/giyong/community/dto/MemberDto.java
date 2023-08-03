package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;
@Data
public class MemberDto {
    private String memberId;
    private String memberPw;
    private String confirmPw;
    private String nickname;
    private Date createdAt;
    private Date updatedAt;
}
