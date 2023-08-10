package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    void write() {
        BoardDto dto = new BoardDto();
        dto.setTitle("게시글 제목");
        dto.setContent("게시글 본문");
        dto.setWriter("giyong");

        Board board = boardService.write(dto);
        assertTrue(dto.getTitle().equals(board.getTitle()));
    }

    @Test
    void modify() {
        BoardDto dto = new BoardDto();
        dto.setTitle("게시글 제목");
        dto.setContent("게시글 본문");
        dto.setWriter("giyong");

        Board board = boardService.write(dto);
        dto.setBoardId(board.getBoardId());
        dto.setTitle("수정수정");

        Board modifyBoard = boardService.modify(dto);
        assertFalse("게시글 제목".equals(modifyBoard.getTitle()));
        assertTrue("수정수정".equals(modifyBoard.getTitle()));
    }

    @Test
    void findById() {
        BoardDto dto = new BoardDto();
        dto.setTitle("게시글 제목");
        dto.setContent("게시글 본문");
        dto.setWriter("giyong");

        Board board = boardService.write(dto);
        Board findBoard = boardService.findById(board.getBoardId());

        assertTrue(board.getTitle().equals(findBoard.getTitle()));
    }

    @Test
    void upViewCount() {
//        BoardDto boardDto = new BoardDto();
//        boardDto.setTitle("TEST");
//        boardDto.setContent("test");
//        boardDto.setWriter("tester");
//        boardDto.setViewCount(0);
//        boardDto.setCreatedAt(new Date());
//
//        Board board = boardService.write(boardDto);
//        boardDto.setBoardId(board.getBoardId());
//        HttpSession session = new HttpSession();
//        boardService.upViewCount(boardDto.getBoardId(),);
//        Board findBoard = boardService.findById(boardDto.getBoardId());
//
//        assertTrue(findBoard.getViewCount() == 1);
    }
}