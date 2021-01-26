package com.example.quora.services;

import com.example.quora.Entity.Question;
import com.example.quora.dto.QuestionDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface QuestionService {

    void save(Question question);

    Question findById(String questionId);

    List<QuestionDto> getFeed(String userId);

    QuestionDto getQuestionDto(Question question);

    List<QuestionDto> getFeedByCategoryId(String categoryId,String userId);

    List<Question> getUnansweredQuestions(String userId);

    QuestionDto getAllAnswers(String questionId);
}
