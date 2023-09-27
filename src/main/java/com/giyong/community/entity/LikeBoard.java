package com.giyong.community.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LikeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
