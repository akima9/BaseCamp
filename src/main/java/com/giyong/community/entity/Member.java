package com.giyong.community.entity;

import com.giyong.community.dto.MemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private int mno;
    private String memberId;
    private String memberPw;
    private String nickname;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
