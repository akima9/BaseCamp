package com.giyong.community.service;

import com.giyong.community.dto.CommentDto;
import com.giyong.community.entity.Comment;
import com.giyong.community.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Comment write(CommentDto commentDto) {
        // commentDto에 생성 날짜 및 수정 날짜 세팅
        commentDto.setCreatedAt(new Date());
        commentDto.setUpdatedAt(new Date());

        // commentDto -> comment entity
        Comment comment = modelMapper.map(commentDto, Comment.class);

        return commentRepository.save(comment);
    }

    @Override
    public Comment modify(CommentDto commentDto) {
        // commentId로 기존 comment select
        Comment comment = commentRepository.findById(commentDto.getCommentId()).orElse(null);

        // comment -> findCommentDto
        CommentDto findCommentDto = modelMapper.map(comment, CommentDto.class);

        // findCommentDto에 commentDto 세팅
        findCommentDto.setContent(commentDto.getContent());
        findCommentDto.setUpdatedAt(new Date());

        // findCommentDto -> modifyComment
        Comment modifyComment = modelMapper.map(findCommentDto, Comment.class);

        return commentRepository.save(modifyComment);
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return (Page<Comment>) commentRepository.findAll(pageable);
    }

    @Override
    public void remove(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
