package com.giyong.community.controller;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/boards")
    public String boards(BoardDto dto) {
        boardService.create(dto);
        return "redirect:/boards/list";
    }

    @PutMapping("/boards")
    public String modify(BoardDto dto) {
        System.out.println("BoardController.modify");
        System.out.println("dto = " + dto);
        Board board = boardService.modify(dto);
        return "redirect:/boards?boardId" + board.getBoardId();
    }

    @GetMapping("/boards")
    public String boards(int boardId, Model model) {
        System.out.println("BoardController.boards");
        Optional<Board> board = boardService.findById(boardId);
        model.addAttribute("board", board.get());
        return "board/board1/view";
    }

    @GetMapping("/boards/list")
    public String boards(Model model) {
        Iterable<Board> list = boardService.findAll();
        model.addAttribute("list", list);
        return "board/board1/list";
    }

    @GetMapping("/boards/write")
    public String write() {
        return "board/board1/write";
    }

    @GetMapping("/boards/modify")
    public String modify(int boardId, Model model) {
        Optional<Board> board = boardService.findById(boardId);
        model.addAttribute("board", board.get());
        return "board/board1/modify";
    }
}
