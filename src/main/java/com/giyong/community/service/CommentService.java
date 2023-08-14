package com.giyong.community.service;

import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Comment write(CommentDto commentDto);

    Comment modify(CommentDto commentDto);

    Page<Comment> findAll(Pageable pageable);

    void remove(Integer commentId);
}
