package com.likelion.mission10.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // 연관관계의 주인(N쪽). member_id 외래키를 이 필드가 관리한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Assignment() {
    }

    public Assignment(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Member getMember() { return member; }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
