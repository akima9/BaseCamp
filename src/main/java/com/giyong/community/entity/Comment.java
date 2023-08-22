package com.giyong.community.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long boardId;
    private String content;
    private String writer;
    @JsonBackReference
    @ManyToOne
    private Board board;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
