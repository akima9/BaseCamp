package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {
    private int commentId;
    private int boardId;
    private String content;
    private String writer;
    private Date createdAt;
    private Date updatedAt;
}
