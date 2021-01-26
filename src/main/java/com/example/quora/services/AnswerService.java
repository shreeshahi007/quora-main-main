package com.example.quora.services;

import com.example.quora.Entity.Answer;
import com.example.quora.dto.AnswerDto;

import java.util.List;

public interface AnswerService {

    void save(Answer answer);

    AnswerDto getAnswerDto(Answer answer);

    List<Answer> findByQuestionId(String questionId);
}