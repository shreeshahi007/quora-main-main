package com.example.quora.repository;

import com.example.quora.Entity.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question,String> {
    List<Question> findByUserId(String followingId);

    List<Question> findByCategoryId(String categoryId);

    @Query(value = "select * from ques where answer_status = 0",nativeQuery = true)
    List<Question> findUnansweredQuestions();
}
