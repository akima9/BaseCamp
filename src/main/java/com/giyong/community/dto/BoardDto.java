package com.giyong.community.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BoardDto {
    private Long boardId;
    private String title;
    private String content;
    private Long viewCount;
    private Long CommentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
