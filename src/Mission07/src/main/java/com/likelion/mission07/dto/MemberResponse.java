package com.likelion.mission07.dto;

import com.likelion.mission07.domain.Member;

public class MemberResponse {

    private Long id;
    private String name;
    private int age;
    private String part;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.part = member.getPart();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPart() { return part; }
}
