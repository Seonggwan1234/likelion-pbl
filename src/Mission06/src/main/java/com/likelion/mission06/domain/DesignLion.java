package com.likelion.mission06.domain;

import com.likelion.mission06.policy.WorkPolicy;

public class DesignLion extends Lion implements WorkPolicy {

    public DesignLion(String name, String major, int generation) {
        super(name, major, generation, "디자인");
    }

    @Override
    public void introduce() {
        System.out.println("[디자인] 안녕하세요! " + getInfo() + " 입니다.");
    }

    @Override
    public void doWork() {
        System.out.println(name + " - UI/UX 디자인 및 브랜딩을 담당합니다.");
    }
}
