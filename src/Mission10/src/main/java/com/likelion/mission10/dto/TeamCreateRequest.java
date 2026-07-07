package com.likelion.mission10.dto;

import jakarta.validation.constraints.NotBlank;

public class TeamCreateRequest {

    @NotBlank(message = "팀 이름은 필수입니다")
    private String name;

    public TeamCreateRequest() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
