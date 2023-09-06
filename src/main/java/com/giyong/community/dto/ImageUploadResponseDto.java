package com.giyong.community.dto;

import lombok.Data;

@Data
public class ImageUploadResponseDto {
    private int success;
    private ImageFileDto file;
}
