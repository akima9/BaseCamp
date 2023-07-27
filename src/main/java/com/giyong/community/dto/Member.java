package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;
@Data
public class Member {
    private String id;
    private String password;
    private String nickname;
    private Date created_at;
    private Date updated_at;
}
