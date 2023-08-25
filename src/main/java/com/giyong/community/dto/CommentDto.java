package com.giyong.community.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentDto {
    private Long commentId;
    private String content;
    private Long memberId;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
