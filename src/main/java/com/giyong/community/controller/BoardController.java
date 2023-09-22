package com.giyong.community.controller;

import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.entity.SubCategory;
import com.giyong.community.service.BoardService;
import com.giyong.community.service.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private SubCategoryService subCategoryService;

    @DeleteMapping("/boards")
    public String deleteBoard(BoardDto boardDto) {
        boardService.remove(boardDto.getBoardId());
        return "redirect:/boards/list?subCategoryId=" + boardDto.getSubCategoryId();
    }

    @GetMapping("/boards/list")
    public String goToBoardList(Long subCategoryId, BoardDto boardDto, Model m, @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Board> boards = boardService.findAllBySubCategoryId(boardDto.getSubCategoryId(), pageable);
        SubCategory subCategory = subCategoryService.findById(subCategoryId);
        m.addAttribute("boards", boards);
        m.addAttribute("subCategory", subCategory);
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
    public String writeBoard(BoardDto dto, Model m, @SessionAttribute(name = "memberId", required = false) Long memberId) {
        dto.setMemberId(memberId);
        Board board = boardService.write(dto);
        m.addAttribute("board", board);
        return "redirect:/boards?subCategoryId="+board.getSubCategory().getSubCategoryId()+"&boardId="+board.getBoardId()+"&page=0";
    }

    @GetMapping("/boards/modify")
    public String goToBoardModify(Long boardId, Model m) {
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
    public String delete(Long boardId) {
        boardService.remove(boardId);
        return "redirect:/boards/list";
    }

    @GetMapping("/boards")
    public String getBoard(Long subCategoryId, Long boardId, Integer page, HttpSession session, Model m) {
        boardService.upViewCount(boardId, session);
        Board board = boardService.findById(boardId);

        m.addAttribute("board", board);
        m.addAttribute("page", page);
        m.addAttribute("subCategoryId", subCategoryId);

        return "board/board1/view";
    }
}
