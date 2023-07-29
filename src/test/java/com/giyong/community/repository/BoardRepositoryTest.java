package com.giyong.community.repository;

import com.giyong.community.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void crud() {
        Board board = new Board();
        board.setTitle("첫번째 글");
        board.setContent("첫번째 글 입니다.");
        board.setWriter("test@test.net");
        board.setCreatedAt(new Date());
        board.setUpdatedAt(new Date());

        //Insert
        boardRepository.save(board);

        //Update
        board.setContent("첫번째 글을 수정합니다.");
        boardRepository.save(board);

        //Select
        Optional<Board> findBoard = boardRepository.findById(1);

        assertTrue(board.getTitle().equals(findBoard.get().getTitle()));

        //Delete
        boardRepository.deleteById(findBoard.get().getBoardId());
        Optional<Board> findBoard2 = boardRepository.findById(findBoard.get().getBoardId());

        assertTrue(findBoard2.isEmpty());
    }
}