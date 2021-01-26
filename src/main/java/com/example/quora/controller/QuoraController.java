package com.example.quora.controller;

import com.example.quora.Entity.Answer;
import com.example.quora.Entity.Comment;
import com.example.quora.Entity.Question;
import com.example.quora.Entity.Social;
import com.example.quora.dto.QuestionDto;
import com.example.quora.services.AnswerService;
import com.example.quora.services.CommentService;
import com.example.quora.services.QuestionService;
import com.example.quora.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/quora")
public class QuoraController {

    @Autowired
    AnswerService answerService;

    @Autowired
    CommentService commentService;

    @Autowired
    QuestionService questionService;

    @Autowired
    SocialService socialService;

    @PostMapping(value = "/add/question")
    public void addQuestion(@RequestBody Question question) {
        question.setAnswerStatus(0);
        questionService.save(question);
    }

    @PostMapping(value = "/add/answer")
    public void addAnswer(@RequestBody Answer answer) {
        Question question = questionService.findById(answer.getQuestionId());
        question.setAnswerStatus(1);
        answerService.save(answer);
        questionService.save(question);
    }

    @PostMapping(value = "/add/comment")
    public void addComment(@RequestBody Comment comment) {
        commentService.save(comment);
    }

    @PostMapping(value = "/add/reaction")
    public void addSocial(@RequestBody Social social) {
        socialService.save(social);
    }

    @GetMapping(value = "/get/feed/{userId}")
    public List<QuestionDto> getFeed(@PathVariable("userId") String userId) {
        return questionService.getFeed(userId);
    }

    @GetMapping(value = "/get/feed/category/{categoryId}/{userId}")
    public List<QuestionDto> getFeedByCategory(@PathVariable("categoryId") String categoryId, @PathVariable("userId") String userId) {
        return questionService.getFeedByCategoryId(categoryId,userId);
    }

    @GetMapping(value = "/get/unanswered/{userId}")
    public List<Question> getUnansweredQuestions(@PathVariable("userId") String userId) {
        return questionService.getUnansweredQuestions(userId);
    }

    @GetMapping(value = "/get/answers/{questionId}")
    public QuestionDto getAllAnswers(@PathVariable("questionId")String questionId) {
        return questionService.getAllAnswers(questionId);
    }
}
