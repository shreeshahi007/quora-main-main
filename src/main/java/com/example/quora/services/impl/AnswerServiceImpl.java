package com.example.quora.services.impl;

import com.example.quora.Entity.Answer;
import com.example.quora.Entity.Comment;
import com.example.quora.dto.AnswerDto;
import com.example.quora.dto.CommentDto;
import com.example.quora.repository.AnswerRepository;
import com.example.quora.services.AnswerService;
import com.example.quora.services.CommentService;
import com.example.quora.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    SocialService socialService;

    @Autowired
    CommentService commentService;

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public AnswerDto getAnswerDto(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        List<Comment> commentList = commentService.findByAnswerId(answer.getAnswerId());
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            commentDtoList.add(commentService.getCommentDto(comment));
        }
        answerDto.setAnswerId(answer.getAnswerId());
        answerDto.setAnswerString(answer.getAnswerString());
        answerDto.setUserId(answer.getUserId());
        answerDto.setSocialDto(socialService.getAnswerSocialDto(answer.getAnswerId()));
        answerDto.setTimeStamp(answer.getTimeStamp());
        answerDto.setCommentDtoList(commentDtoList);
        return answerDto;
    }

    @Override
    public List<Answer> findByQuestionId(String questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}