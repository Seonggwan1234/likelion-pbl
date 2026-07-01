package com.likelion.mission09.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String part;

    // 연관관계의 주인(N쪽). team_id 외래키를 이 필드가 관리한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // Member가 연관관계의 주인이 아닌 쪽(1쪽). 외래키는 Assignment.member가 관리한다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    protected Member() {
    }

    public Member(String name, int age, String part, Team team) {
        this.name = name;
        this.age = age;
        this.part = part;
        this.team = team;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPart() { return part; }
    public Team getTeam() { return team; }
    public List<Assignment> getAssignments() { return assignments; }

    public void update(String name, int age, String part) {
        this.name = name;
        this.age = age;
        this.part = part;
    }

    public void assignTeam(Team team) {
        this.team = team;
    }
}
