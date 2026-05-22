package Mission05.app;

import Mission05.config.AppConfig;
import Mission05.domain.BackendLion;
import Mission05.domain.DesignLion;
import Mission05.domain.FrontendLion;
import Mission05.service.LionService;

public class Main {

    public static void main(String[] args) {
        // main은 AppConfig를 통해서만 객체를 생성한다 (직접 new 금지)
        AppConfig config = new AppConfig();

        // ─── MemoryRepository 사용 ───────────────────────────
        System.out.println("=== [MemoryRepository 모드] ===");
        LionService service = config.lionService();

        service.register(new BackendLion("김성관", "컴퓨터공학", 13));
        service.register(new FrontendLion("정선호", "컴퓨터공학", 13));
        service.register(new DesignLion("김건호", "컴퓨터공학", 13));
        service.register(new BackendLion("김나나", "소프트웨어학", 13));
        service.register(new BackendLion("김성관", "컴퓨터공학", 13)); // 중복 테스트

        System.out.println("\n--- 전체 멤버 ---");
        service.printAll();

        System.out.println("\n--- 백엔드 파트 ---");
        service.printByPart("백엔드");

        System.out.println("\n--- 멤버 삭제 ---");
        service.remove("정선호");
        service.remove("없는사람");

        System.out.println("\n--- 삭제 후 전체 멤버 ---");
        service.printAll();

        // ─── MockRepository 사용 (Repository만 교체, Service 로직 동일) ───
        System.out.println("\n=== [MockRepository 모드] ===");
        LionService mockService = config.mockLionService();

        System.out.println("--- 사전 로드된 Mock 데이터 ---");
        mockService.printAll();

        System.out.println("\n--- 프론트엔드 파트 ---");
        mockService.printByPart("프론트엔드");

        System.out.println("\n--- Mock 멤버 추가 ---");
        mockService.register(new BackendLion("추가백엔드", "컴퓨터공학", 13));
        mockService.printAll();
    }
}
