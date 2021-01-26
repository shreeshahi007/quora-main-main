package com.example.quora.repository;

import com.example.quora.Entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment,String> {
    List<Comment> findByAnswerId(String answerId);

    List<Comment> findByQuestionId(String questionId);
}
