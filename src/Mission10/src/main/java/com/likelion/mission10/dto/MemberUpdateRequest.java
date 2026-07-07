package com.likelion.mission10.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class MemberUpdateRequest {

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @Min(value = 1, message = "나이는 1 이상이어야 합니다")
    private int age;

    @NotBlank(message = "파트는 필수입니다")
    private String part;

    private Long teamId;

    public MemberUpdateRequest() {}

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPart() { return part; }
    public Long getTeamId() { return teamId; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPart(String part) { this.part = part; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
}
