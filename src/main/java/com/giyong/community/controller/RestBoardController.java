package com.giyong.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giyong.community.dto.BoardDto;
import com.giyong.community.entity.Board;
import com.giyong.community.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class RestBoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/boards")
    public String boards(@RequestBody Map<String, Object> map, BoardDto boardDto, @SessionAttribute(name = "memberId", required = false) String memberId) throws JsonProcessingException {
        map.forEach((key, value) -> {
            if (key.equals("blocks")) {
                ArrayList<Map<String, String>> blocksList = (ArrayList<Map<String, String>>) value;
                for (Map block : blocksList) {
                    if (block.get("type").equals("header")) {
                        Map<String, String> data = (Map<String, String>) block.get("data");
                        boardDto.setTitle(data.get("text"));
                        break;
                    }

                }
            }
        });
        boardDto.setContent(map.toString());
        String title = boardDto.getTitle();
        if (title == null) {
            boardDto.setTitle("제목없음");
        }
        boardDto.setWriter(memberId);
        Board board = boardService.write(boardDto);
        return objectMapper.writeValueAsString(board.getContent());
    }
}