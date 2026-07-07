package com.likelion.mission10.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TeamUpdateRequest {

    @NotBlank(message = "팀 이름은 필수입니다")
    @Size(max = 50, message = "팀 이름은 50자를 넘을 수 없습니다")
    private String name;

    public TeamUpdateRequest() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
