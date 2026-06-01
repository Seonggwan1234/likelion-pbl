package com.likelion.mission07.dto;

public class MemberCreateRequest {

    private String name;
    private int age;
    private String part;

    public MemberCreateRequest() {}

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPart() { return part; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPart(String part) { this.part = part; }
}
