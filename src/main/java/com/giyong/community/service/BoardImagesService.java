package com.giyong.community.service;

import com.giyong.community.dto.BoardImagesDto;
import com.giyong.community.entity.BoardImages;
import org.springframework.web.multipart.MultipartFile;

public interface BoardImagesService {
    BoardImages upload(BoardImagesDto boardImagesDto);

    void store(MultipartFile file);
}
