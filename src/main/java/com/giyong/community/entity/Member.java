package com.giyong.community.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Member {
    @Id
    private String id;
    private String password;
    private String nickname;
    private Date createdAt;
    private Date updatedAt;

//    @Builder
//    public Member(String id, String password) {
//        this.id = id;
//        this.password = password;
//    }
}
