package com.likelion.mission10.dto;

import com.likelion.mission10.domain.Member;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MemberResponse {

    private Long id;
    private String name;
    private int age;
    private String part;
    private Long teamId;
    private String teamName;
    private List<AssignmentResponse> assignments;

    public MemberResponse(Member member) {
        this(member, true);
    }

    private MemberResponse(Member member, boolean includeAssignments) {
        this.id = member.getId();
        this.name = member.getName();
        this.age = member.getAge();
        this.part = member.getPart();
        this.teamId = member.getTeam() != null ? member.getTeam().getId() : null;
        this.teamName = member.getTeam() != null ? member.getTeam().getName() : null;
        this.assignments = includeAssignments
                ? member.getAssignments().stream().map(AssignmentResponse::new).collect(Collectors.toList())
                : Collections.emptyList();
    }

    public static MemberResponse withoutAssignments(Member member) {
        return new MemberResponse(member, false);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPart() { return part; }
    public Long getTeamId() { return teamId; }
    public String getTeamName() { return teamName; }
    public List<AssignmentResponse> getAssignments() { return assignments; }
}
