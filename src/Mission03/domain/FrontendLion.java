package Mission03.domain;

import Mission03.policy.WorkPolicy;

public class FrontendLion extends Lion implements WorkPolicy {

    public FrontendLion(String name, String major, int generation) {
        super(name, major, generation);
    }

    @Override
    public void introduce() {
        System.out.println("[프론트엔드] 안녕하세요! " + getInfo() + " 입니다.");
    }

    @Override
    public void doWork() {
        System.out.println(name + " - UI 구현 및 사용자 경험을 담당합니다.");
    }
}
