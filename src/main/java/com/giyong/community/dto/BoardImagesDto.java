package com.giyong.community.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardImagesDto {
    private Long boardImageId;
    private Long boardId;
    private Long memberId;
    private String originName;
    private String storedName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
