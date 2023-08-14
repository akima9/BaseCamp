package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Board;
import com.giyong.community.entity.Comment;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceImplTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BoardService boardService;
    private ModelMapper modelMapper = new ModelMapper();
    @Test
    void writeComment() {
        // 게시글 작성
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목입니다.");
        boardDto.setContent("내용입니다.");
        boardDto.setWriter("tester");
        Board board = boardService.write(boardDto);
        // 댓글 작성
        CommentDto commentDto = new CommentDto();
        commentDto.setBoardId(board.getBoardId());
        commentDto.setContent("댓글입니다.");
        commentDto.setWriter("giyong");
        Comment comment = commentService.write(commentDto);
        commentService.write(commentDto);

        assertTrue(comment.getContent().equals("댓글입니다."));
    }

    @Test
    void modify() {
        // 게시글 작성
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목입니다.");
        boardDto.setContent("내용입니다.");
        boardDto.setWriter("tester");
        Board board = boardService.write(boardDto);
        // 댓글 작성
        CommentDto commentDto = new CommentDto();
        commentDto.setBoardId(board.getBoardId());
        commentDto.setContent("댓글입니다.");
        commentDto.setWriter("giyong");
        Comment comment = commentService.write(commentDto);
        // 댓글 수정
        CommentDto modifyCommentDto = modelMapper.map(comment, CommentDto.class);
        modifyCommentDto.setContent("댓글을 수정합니다.");
        Comment modifyComment = commentService.modify(modifyCommentDto);

        System.out.println("modifyComment = " + modifyComment);
        assertTrue(modifyComment.getContent().equals("댓글을 수정합니다."));
    }

    @Test
    void remove() {
        // 게시글 작성
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("제목입니다.");
        boardDto.setContent("내용입니다.");
        boardDto.setWriter("tester");
        Board board = boardService.write(boardDto);
        // 댓글 작성
        CommentDto commentDto = new CommentDto();
        commentDto.setBoardId(board.getBoardId());
        commentDto.setContent("댓글입니다.");
        commentDto.setWriter("giyong");
        Comment comment = commentService.write(commentDto);

        commentService.remove(comment.getCommentId());
    }
}