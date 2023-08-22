package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giyong.community.dto.MemberDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String memberId;
    private String memberPw;
    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<Board> boards;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
