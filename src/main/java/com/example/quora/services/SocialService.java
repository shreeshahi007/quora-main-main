package com.example.quora.services;

import com.example.quora.Entity.Social;
import com.example.quora.dto.SocialDto;
import org.springframework.stereotype.Service;

@Service
public interface SocialService {

    void save(Social social);

    SocialDto getQuestionSocialDto(String questionId);

    SocialDto getAnswerSocialDto(String answerId);

    SocialDto getCommentSocialDto(String commentId);
}
