package com.example.quora.repository;

import com.example.quora.Entity.Social;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialRepository extends CrudRepository<Social,String> {

    List<Social> findByQuestionId(String questionId);

    List<Social> findByAnswerId(String answerId);

    List<Social> findByCommentId(String commentId);
}
