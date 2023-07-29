package com.giyong.community.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDto {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private Date createdAt;
    private Date updatedAt;
}
