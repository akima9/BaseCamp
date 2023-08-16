package com.giyong.community.controller;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.service.BoardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @DeleteMapping("/boards")
    public String deleteBoard(BoardDto boardDto) {
        boardService.remove(boardDto.getBoardId());
        return "redirect:/boards/list";
    }

    @GetMapping("/boards/list")
    public String goToBoardList(Model m, @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Board> boards = boardService.findAll(pageable);
        ArrayList<BoardDto> boardList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        int boardNumber = 1;
        for (Board board : boards) {
            BoardDto boardDto = modelMapper.map(board, BoardDto.class);
            boardDto.setBoardNumber(boardNumber);
            boardDto.setCommentCount(boardService.findCommentCount(boardDto.getBoardId()));
            boardList.add(boardDto);
            boardNumber++;
        }
        m.addAttribute("boards", new PageImpl<>(boardList, pageable, boards.getTotalElements()));
        return "board/board1/list";
    }

    @GetMapping("/boards/write")
    public String goToBoardWrite(HttpServletRequest request, RedirectAttributes redirect) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("memberId") == null) {
            redirect.addFlashAttribute("resCode", 600);
            return "redirect:/login";
        }
        return "board/board1/write";
    }

    @PostMapping("/boards")
    public String writeBoard(BoardDto dto, Model m) {
        Board board = boardService.write(dto);
        m.addAttribute("board", board);
        return "board/board1/view";
    }

    @GetMapping("/boards/modify")
    public String goToBoardModify(Integer boardId, Model m) {
        Board board = boardService.findById(boardId);
        m.addAttribute("board", board);
        return "board/board1/modify";
    }

    @PutMapping("/boards")
    public String modify(BoardDto dto, Model m) {
        Board board = boardService.write(dto);
        m.addAttribute("board", board);
        return "board/board1/view";
    }

    @GetMapping("/boards/delete")
    public String delete(Integer boardId) {
        boardService.remove(boardId);
        return "redirect:/boards/list";
    }

    @GetMapping("/boards")
    public String getBoard(Integer boardId, Integer page, HttpSession session, Model m) {
        boardService.upViewCount(boardId, session);
        Board board = boardService.findById(boardId);
        m.addAttribute("board", board);
        m.addAttribute("page", page);
        return "board/board1/view";
    }
}
