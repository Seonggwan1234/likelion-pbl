package com.likelion.mission09.dto;

import com.likelion.mission09.domain.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamResponse {

    private Long id;
    private String name;
    private int memberCount;
    private List<MemberResponse> members;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.memberCount = team.getMembers().size();
        this.members = team.getMembers().stream()
                .map(MemberResponse::withoutAssignments)
                .collect(Collectors.toList());
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getMemberCount() { return memberCount; }
    public List<MemberResponse> getMembers() { return members; }
}
