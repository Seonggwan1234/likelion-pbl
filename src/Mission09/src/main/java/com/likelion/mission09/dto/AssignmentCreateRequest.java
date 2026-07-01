package com.likelion.mission09.dto;

public class AssignmentCreateRequest {

    private String title;
    private String content;
    private Long memberId;

    public AssignmentCreateRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Long getMemberId() { return memberId; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
}
