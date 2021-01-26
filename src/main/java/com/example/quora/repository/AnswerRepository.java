package com.example.quora.repository;

import com.example.quora.Entity.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,String> {
    List<Answer> findByQuestionId(String questionId);
}
