package com.giyong.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    @GetMapping("/list")
    public String boards() {
        return "board/board1/list";
    }

    @GetMapping("/write")
    public String write() {
        return "board/board1/write";
    }
}
