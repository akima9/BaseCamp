package com.giyong.community.service;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board write(BoardDto boardDto) {
        boardDto.setViewCount(0);
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
    public Page<Board> findAll(Pageable pageable) {
        return (Page<Board>) boardRepository.findAll(pageable);
    }

    @Override
    public Board findById(Integer boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    @Override
    public void remove(Integer boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public void upViewCount(Integer boardId, HttpSession session) {
        boolean userHasViewed = hasUserViewedPost(boardId, session);
        if (!userHasViewed) {
            Board findBoard = boardRepository.findById(boardId).orElse(null);
            ModelMapper modelMapper = new ModelMapper();
            BoardDto dto = modelMapper.map(findBoard, BoardDto.class);
            dto.setViewCount(dto.getViewCount() + 1);
            Board upViewCountBoard = modelMapper.map(dto, Board.class);
            boardRepository.save(upViewCountBoard);
            // 사용자가 해당 게시글을 조회한 것으로 표시
            Set<Integer> viewedMemberIds = (Set<Integer>) session.getAttribute("viewedMemberIds");
            viewedMemberIds.add(boardId);
        }
    }

    public boolean hasUserViewedPost(Integer boardId, HttpSession session) {
        Set<Integer> viewedMemberIds = (Set<Integer>) session.getAttribute("viewedMemberIds");
        if (viewedMemberIds == null) {
            viewedMemberIds = new HashSet<>();
            session.setAttribute("viewedMemberIds", viewedMemberIds);
        }
        return viewedMemberIds.contains(boardId);
    }

    public Integer findCommentCount(Integer boardId) {
        return boardRepository.findCommentCountByBoardId(boardId);
    }
}
