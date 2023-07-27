package com.giyong.community.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    private String id;
    private String password;
    private String nickname;
    private Date created_at;
    private Date updated_at;

    @Builder
    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
