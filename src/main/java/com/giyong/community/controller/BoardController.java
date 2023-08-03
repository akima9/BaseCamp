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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/boards/list")
    public String goToBoardList(Model m) {
        List<Board> boards = boardService.findAll();
        m.addAttribute("boards", boards);
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
    public String getBoard(Integer boardId, Model m) {
        Board board = boardService.findById(boardId);
        m.addAttribute("board", board);
        return "board/board1/view";
    }
}
