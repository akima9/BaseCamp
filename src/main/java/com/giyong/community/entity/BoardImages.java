package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class BoardImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImageId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String originName;
    private String storedName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
