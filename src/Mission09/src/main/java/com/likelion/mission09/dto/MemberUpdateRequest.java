package com.likelion.mission09.dto;

public class MemberUpdateRequest {

    private String name;
    private int age;
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
