package Mission03.app;

import Mission03.domain.BackendLion;
import Mission03.domain.DesignLion;
import Mission03.domain.FrontendLion;
import Mission03.domain.Lion;
import Mission03.policy.WorkPolicy;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BackendLion backend   = new BackendLion("김성관", "컴퓨터공학", 13);
        FrontendLion frontend = new FrontendLion("정선호", "컴퓨터공학", 13);
        DesignLion design     = new DesignLion("김건호", "컴퓨터공학", 13);

        // 다형성: Lion 타입 리스트로 introduce() 호출 — instanceof 없이 오버라이딩으로 분기
        List<Lion> team = new ArrayList<>();
        team.add(backend);
        team.add(frontend);
        team.add(design);

        System.out.println("=== 팀원 소개 ===");
        for (Lion lion : team) {
            lion.introduce();
        }

        // 정책 인터페이스를 통한 기능 분리 — WorkPolicy 타입으로 doWork() 호출
        List<WorkPolicy> workers = new ArrayList<>();
        workers.add(backend);
        workers.add(frontend);
        workers.add(design);

        System.out.println("\n=== 역할별 업무 ===");
        for (WorkPolicy worker : workers) {
            worker.doWork();
        }
    }
}
