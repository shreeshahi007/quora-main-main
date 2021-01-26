package com.example.quora.dto;

public class SocialDto {

    private String questionId;
    private String answerId;
    private String CommentId;
    private int CountUp;
    private int CountDown;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    public int getCountUp() {
        return CountUp;
    }

    public void setCountUp(int countUp) {
        CountUp = countUp;
    }

    public int getCountDown() {
        return CountDown;
    }

    public void setCountDown(int countDown) {
        CountDown = countDown;
    }
}
