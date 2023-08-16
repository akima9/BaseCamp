package com.giyong.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Comment;
import com.giyong.community.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestCommentController {
    @Autowired
    private CommentService commentService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/comments")
    public String getComments(Integer boardId) throws JsonProcessingException {
        List<Comment> comments = commentService.findAllByBoardId(boardId);
        return objectMapper.writeValueAsString(comments);
    }
}
