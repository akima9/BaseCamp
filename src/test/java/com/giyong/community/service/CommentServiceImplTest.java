package com.giyong.community.service;

import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Board;
import com.giyong.community.entity.Comment;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("댓글 작성")
    @Order(1)
    void write() {
        Board board = boardService.findById(1L);
        CommentDto commentDto = new CommentDto();
        commentDto.setBoardId(board.getBoardId());
        commentDto.setContent("봤다!");
        commentDto.setMemberId(1L);
        Comment comment = commentService.write(commentDto);

        assertTrue(comment.getCommentId() == 1);
    }

    @Test
    void modify() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findAllByBoardId() {
    }

    @Test
    void remove() {
    }
}