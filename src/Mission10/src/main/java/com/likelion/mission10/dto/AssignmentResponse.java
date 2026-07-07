package com.likelion.mission10.dto;

import com.likelion.mission10.domain.Assignment;

public class AssignmentResponse {

    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private String memberName;

    public AssignmentResponse(Assignment assignment) {
        this.id = assignment.getId();
        this.title = assignment.getTitle();
        this.content = assignment.getContent();
        this.memberId = assignment.getMember() != null ? assignment.getMember().getId() : null;
        this.memberName = assignment.getMember() != null ? assignment.getMember().getName() : null;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Long getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
}
