package com.example.quora.services.impl;

import com.example.quora.Entity.Social;
import com.example.quora.dto.SocialDto;
import com.example.quora.repository.SocialRepository;
import com.example.quora.services.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SocialServiceImpl implements SocialService {

    @Autowired
    SocialRepository socialRepository;

    @Override
    public void save(Social social) {
        socialRepository.save(social);
    }

    @Override
    public SocialDto getQuestionSocialDto(String questionId) {
        SocialDto socialdto = new SocialDto();
        socialdto.setQuestionId(questionId);
        List<Social> socialList = socialRepository.findByQuestionId(questionId);
        int countUp=0;
        int countDown=0;
        for(Social social : socialList) {
            if(social.getReactionType().equals("upvote")) {
                countUp++;
            }
            else {
                countDown++;
            }
        }
        socialdto.setCountDown(countDown);
        socialdto.setCountUp(countUp);
        return socialdto;
    }

    @Override
    public SocialDto getAnswerSocialDto(String answerId) {
        SocialDto socialdto = new SocialDto();
        socialdto.setQuestionId(answerId);
        List<Social> socialList = socialRepository.findByAnswerId(answerId);
        int countUp=0;
        int countDown=0;
        for(Social social : socialList) {
            if(social.getReactionType().equals("upvote")) {
                countUp++;
            }
            else {
                countDown++;
            }
        }
        socialdto.setCountDown(countDown);
        socialdto.setCountUp(countUp);
        return socialdto;
    }

    @Override
    public SocialDto getCommentSocialDto(String commentId) {
        SocialDto socialdto = new SocialDto();
        socialdto.setQuestionId(commentId);
        List<Social> socialList = socialRepository.findByCommentId(commentId);
        int countUp=0;
        int countDown=0;
        for(Social social : socialList) {
            if(social.getReactionType().equals("upvote")) {
                countUp++;
            }
            else {
                countDown++;
            }
        }
        socialdto.setCountDown(countDown);
        socialdto.setCountUp(countUp);
        return socialdto;
    }
}