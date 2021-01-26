package com.example.quora.services.impl;

import com.example.quora.Entity.Answer;
import com.example.quora.Entity.Comment;
import com.example.quora.Entity.FollowingList;
import com.example.quora.Entity.Question;
import com.example.quora.dto.AnswerDto;
import com.example.quora.dto.CommentDto;
import com.example.quora.dto.QuestionDto;
import com.example.quora.repository.QuestionRepository;
import com.example.quora.services.AnswerService;
import com.example.quora.services.CommentService;
import com.example.quora.services.QuestionService;
import com.example.quora.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    SocialService socialService;

    @Autowired
    AnswerService answerService;

    @Autowired
    CommentService commentService;

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question findById(String questionId) {
        return questionRepository.findById(questionId).get();
    }

    //TODO Enter the restTemplate urls
    @Override
    public List<QuestionDto> getFeed(String userId) {
        FollowingList followingList = restTemplate.getForObject("http://localhost:9007/getFollowingList/"+userId,FollowingList.class);
        List<String> followingListIds= followingList.getFollowersList();
        List<QuestionDto> feedQuestionDto = new ArrayList<>();
        for(String followingId : followingListIds) {
            List<Question> questionList = questionRepository.findByUserId(followingId);
            for(Question question : questionList) {
                if(question.getAnswerStatus()==1){
                    feedQuestionDto.add(getQuestionDto(question));
                }
            }
        }
        return feedQuestionDto;
    }

    //TODO Enter the restTemplate urls
    @Override
    public List<QuestionDto> getFeedByCategoryId(String categoryId,String userId) {
        FollowingList followingList = restTemplate.getForObject("http://localhost:9007/getFollowingList/"+userId,FollowingList.class);
        List<String> followingListIds= followingList.getFollowersList();
        List<Question> questionList = questionRepository.findByCategoryId(categoryId);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for(Question question : questionList) {
            if(followingListIds.contains(question.getUserId())) {
                questionDtoList.add(getQuestionDto(question));
            }
            else if(restTemplate.getForObject("http://"+question.getUserId(),Boolean.class)){
                questionDtoList.add(getQuestionDto(question));
            }
        }
        return questionDtoList;
    }

    //TODO Enter the restTemplate url
    @Override
    public List<Question> getUnansweredQuestions(String userId) {
        List<Question> questionList = questionRepository.findUnansweredQuestions();
        FollowingList followingList = restTemplate.getForObject("http://localhost:9007/getFollowingList/"+userId,FollowingList.class);
        List<String> followingListIds= followingList.getFollowersList();
        for(Question question : questionList) {
            if(!followingListIds.contains(question.getUserId()) && !restTemplate.getForObject("http://localhost:9007/isPublic/"+question.getUserId(),Boolean.class)){
                questionList.remove(question);
            }
        }
        return questionList;
    }

    @Override
    public QuestionDto getAllAnswers(String questionId) {
        Question question = questionRepository.findById(questionId).get();
        return getQuestionDto(question);
    }

    @Override
    public QuestionDto getQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        Answer answer = answerService.findByQuestionId(question.getQuestionId()).get(0);
        List<AnswerDto> answerDtoList = new ArrayList<>();
        answerDtoList.add(answerService.getAnswerDto(answer));
        List<Comment> commentList = commentService.findByQuestionId(question.getQuestionId());
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            commentDtoList.add(commentService.getCommentDto(comment));
        }
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setQuestionString(question.getQuestionString());
        questionDto.setCategoryId(question.getCategoryId());
        questionDto.setUserId(question.getUserId());
        questionDto.setSocialDto(socialService.getQuestionSocialDto(question.getQuestionId()));
        questionDto.setTimeStamp(question.getTimeStamp());
        questionDto.setAnswerDtoList(answerDtoList);
        questionDto.setCommentDtoList(commentDtoList);

        return questionDto;
    }
}