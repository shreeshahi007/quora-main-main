package com.example.quora.dto;

public class CommentDto {

    private String commentId;
    private String commentString;
    private String userId;
    private SocialDto socialDto;
    private String timestamp;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SocialDto getSocialDto() {
        return socialDto;
    }

    public void setSocialDto(SocialDto socialDto) {
        this.socialDto = socialDto;
    }

    public String getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timestamp = timeStamp;
    }
}
