package com.likelion.mission10.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AssignmentUpdateRequest {

    @NotBlank(message = "제목은 필수입니다")
    @Size(max = 100, message = "제목은 100자를 넘을 수 없습니다")
    private String title;

    @NotBlank(message = "내용은 필수입니다")
    @Size(max = 2000, message = "내용은 2000자를 넘을 수 없습니다")
    private String content;

    public AssignmentUpdateRequest() {}

    public String getTitle() { return title; }
    public String getContent() { return content; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
