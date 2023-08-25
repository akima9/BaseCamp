package com.giyong.community.service;

import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentService {
    Comment write(CommentDto commentDto);

    Comment modify(CommentDto commentDto);

    Page<Comment> findAll(Pageable pageable);

    List<Comment> findAllByBoardId(Long boardId);

    void remove(Long commentId);
}
