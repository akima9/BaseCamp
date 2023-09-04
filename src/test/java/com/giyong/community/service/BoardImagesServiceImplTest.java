package com.giyong.community.service;

import com.giyong.community.dto.BoardImagesDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardImagesServiceImplTest {

    @Test
    void upload() {
        BoardImagesDto boardImagesDto = new BoardImagesDto();
        boardImagesDto.setBoardId(0L);
        boardImagesDto.setMemberId(0L);
        boardImagesDto.setOriginName("test.png");
        boardImagesDto.setStoredName("aaa");
    }
}