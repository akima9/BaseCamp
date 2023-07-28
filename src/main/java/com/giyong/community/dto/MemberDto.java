package com.giyong.community.dto;

import com.giyong.community.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class MemberDto {
    private String memberId;
    private String memberPw;
    private String confirmPw;
    private String nickname;
    private Date createdAt;
    private Date updatedAt;
}
