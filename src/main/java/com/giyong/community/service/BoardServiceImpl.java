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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional
    public Board write(BoardDto boardDto) {
        System.out.println("BoardServiceImpl.write");
        boardDto.setViewCount(0L);
        System.out.println("boardDto = " + boardDto);
        ModelMapper modelMapper = new ModelMapper();
        Board board = modelMapper.map(boardDto, Board.class);

        return boardRepository.save(board);
    }

    @Override
    public Board modify(BoardDto boardDto) {
        Board oldBoard = boardRepository.findById(boardDto.getBoardId()).orElse(null);

        ModelMapper modelMapper = new ModelMapper();
        BoardDto dto = modelMapper.map(oldBoard, BoardDto.class);

        if (boardDto.getTitle() != null) {
            dto.setTitle(boardDto.getTitle());
        }

        if (boardDto.getContent() != null) {
            dto.setContent(boardDto.getContent());
        }

        if (boardDto.getSubCategoryId() != null) {
            dto.setSubCategoryId(boardDto.getSubCategoryId());
        }

        if (boardDto.getMemberId() != null) {
            dto.setMemberId(boardDto.getMemberId());
        }

        Board modifyBoard = modelMapper.map(dto, Board.class);

        return boardRepository.save(modifyBoard);
    }

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return (Page<Board>) boardRepository.findAll(pageable);
    }

    @Override
    public Page<Board> findAllBySubCategoryId(Long subCategoryId, Pageable pageable) {
        return (Page<Board>) boardRepository.findAllBySubCategorySubCategoryId(pageable, subCategoryId);
    }

    @Override
    public Board findById(Long boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }

    @Override
    public void remove(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public void upViewCount(Long boardId, HttpSession session) {
        boolean userHasViewed = hasUserViewedPost(boardId, session);
        if (!userHasViewed) {
            Board findBoard = boardRepository.findById(boardId).orElse(null);
            ModelMapper modelMapper = new ModelMapper();
            BoardDto dto = modelMapper.map(findBoard, BoardDto.class);
            dto.setViewCount(dto.getViewCount() + 1);
            Board upViewCountBoard = modelMapper.map(dto, Board.class);
            boardRepository.save(upViewCountBoard);
            // 사용자가 해당 게시글을 조회한 것으로 표시
            Set<Long> viewedMemberIds = (Set<Long>) session.getAttribute("viewedMemberIds");
            viewedMemberIds.add(boardId);
        }
    }

    public boolean hasUserViewedPost(Long boardId, HttpSession session) {
        Set<Long> viewedMemberIds = (Set<Long>) session.getAttribute("viewedMemberIds");
        if (viewedMemberIds == null) {
            viewedMemberIds = new HashSet<>();
            session.setAttribute("viewedMemberIds", viewedMemberIds);
        }
        return viewedMemberIds.contains(boardId);
    }

    public Long findCommentCount(Long boardId) {
        return boardRepository.findCommentCountByBoardId(boardId);
    }
}
