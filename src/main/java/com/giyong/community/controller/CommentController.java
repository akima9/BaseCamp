package com.giyong.community.controller;

import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Comment;
import com.giyong.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @DeleteMapping("/comments")
    public String deleteComment(CommentDto commentDto) {
        commentService.remove(commentDto.getCommentId());
        return "redirect:/boards?boardId=" + commentDto.getBoardId();
    }
    @PutMapping("/comments")
    public String modifyComment(CommentDto commentDto) {
        Comment comment = commentService.modify(commentDto);
        return "redirect:/boards?boardId=" + comment.getBoard().getBoardId();
    }
    @PostMapping("/comments")
    public String createComment(CommentDto commentDto) {
        Comment comment = commentService.write(commentDto);
        return "redirect:/boards?boardId=" + comment.getBoard().getBoardId();
    }
}
