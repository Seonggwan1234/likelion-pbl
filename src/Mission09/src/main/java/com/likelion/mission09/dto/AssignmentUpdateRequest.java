package com.likelion.mission09.dto;

public class AssignmentUpdateRequest {

    private String title;
    private String content;

    public AssignmentUpdateRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
