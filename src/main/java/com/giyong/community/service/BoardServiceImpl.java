package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board write(BoardDto boardDto) {
        boardDto.setCreatedAt(new Date());
        boardDto.setUpdatedAt(new Date());

        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(boardDto, Board.class);

        return boardRepository.save(board);
    }

    @Override
    public Board modify(BoardDto boardDto) {
        Board oldBoard = boardRepository.findById(boardDto.getBoardId()).orElse(null);

        ModelMapper modelMapper = new ModelMapper();
        BoardDto dto = modelMapper.map(oldBoard, BoardDto.class);
        dto.setTitle(boardDto.getTitle());
        dto.setContent(boardDto.getContent());
        dto.setUpdatedAt(new Date());

        Board modifyBoard = modelMapper.map(dto, Board.class);

        return boardRepository.save(modifyBoard);
    }

    @Override
    public List<Board> findAll() {
        return (List<Board>) boardRepository.findAll();
    }

    @Override
    public Board findById(Integer boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    @Override
    public void remove(Integer boardId) {
        boardRepository.deleteById(boardId);
    }
}
