package com.example.quora.services;


import com.example.quora.Entity.Answer;
import com.example.quora.Entity.Comment;
import com.example.quora.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void save(Comment comment);

    CommentDto getCommentDto(Comment comment);

    List<Comment> findByAnswerId(String answerId);

    List<Comment> findByQuestionId(String questionId);
}