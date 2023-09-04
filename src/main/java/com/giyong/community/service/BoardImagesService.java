package com.giyong.community.service;

import com.giyong.community.dto.BoardImagesDto;
import com.giyong.community.entity.BoardImages;

public interface BoardImagesService {
    BoardImages upload(BoardImagesDto boardImagesDto);
}
