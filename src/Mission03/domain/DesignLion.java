package Mission03.domain;

import Mission03.policy.WorkPolicy;

public class DesignLion extends Lion implements WorkPolicy {

    public DesignLion(String name, String major, int generation) {
        super(name, major, generation);
    }

    @Override
    public void introduce() {
        System.out.println("[디자인] 안녕하세요! " + getInfo() + " 입니다.");
    }

    @Override
    public void doWork() {
        System.out.println(name + " - 화면 설계 및 디자인 시스템을 담당합니다.");
    }
}
