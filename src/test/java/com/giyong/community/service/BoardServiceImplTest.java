package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardServiceImplTest {
    @Autowired
    private BoardService boardService;

    @Test
    void write() {
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("Title");
        boardDto.setContent("Content");
        Board board = boardService.write(boardDto);
        assertTrue(board.getTitle().equals(boardDto.getTitle()));
    }

    @Test
    void modify() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void remove() {
    }

    @Test
    void upViewCount() {
    }

    @Test
    void hasUserViewedPost() {
    }

    @Test
    void findCommentCount() {
    }
}