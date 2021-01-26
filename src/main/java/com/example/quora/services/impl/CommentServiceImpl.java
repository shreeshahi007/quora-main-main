package com.example.quora.services.impl;

import com.example.quora.Entity.Comment;
import com.example.quora.dto.CommentDto;
import com.example.quora.repository.CommentRepository;
import com.example.quora.services.CommentService;
import com.example.quora.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    SocialService socialService;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public CommentDto getCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentId(comment.getCommentId());
        commentDto.setCommentString(comment.getCommentString());
        commentDto.setUserId(comment.getUserId());
        commentDto.setTimeStamp(comment.getTimeStamp());
        commentDto.setSocialDto(socialService.getCommentSocialDto(comment.getCommentId()));
        return commentDto;
    }

    @Override
    public List<Comment> findByAnswerId(String answerId) {
        return commentRepository.findByAnswerId(answerId);
    }

    @Override
    public List<Comment> findByQuestionId(String questionId) {
        return commentRepository.findByQuestionId(questionId);
    }
}