package Mission03.domain;

import Mission03.policy.WorkPolicy;

public class BackendLion extends Lion implements WorkPolicy {

    public BackendLion(String name, String major, int generation) {
        super(name, major, generation);
    }

    @Override
    public void introduce() {
        System.out.println("[백엔드] 안녕하세요! " + getInfo() + " 입니다.");
    }

    @Override
    public void doWork() {
        System.out.println(name + " - API 개발 및 서버 로직을 담당합니다.");
    }
}
