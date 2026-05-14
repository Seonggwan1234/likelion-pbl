package Mission04.app;

import Mission04.domain.BackendLion;
import Mission04.domain.DesignLion;
import Mission04.domain.FrontendLion;
import Mission04.domain.Lion;
import Mission04.service.LionManager;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        LionManager manager = new LionManager();

        System.out.println("=== 멤버 등록 ===");
        manager.addMember(new BackendLion("김성관", "컴퓨터공학", 13));
        manager.addMember(new FrontendLion("정선호", "컴퓨터공학", 13));
        manager.addMember(new DesignLion("김건호", "컴퓨터공학", 13));
        manager.addMember(new BackendLion("김나나", "소프트웨어학", 13));
        manager.addMember(new FrontendLion("나나김", "경영학", 13));

        System.out.println();
        manager.addMember(new BackendLion("김성관", "컴퓨터공학", 13));

        System.out.println("\n=== 전체 멤버 소개 ===");
        for (Lion lion : manager.getAllMembers()) {
            lion.introduce();
        }

        System.out.println("\n=== 이름 검색: '김' ===");
        List<Lion> searchResult = manager.searchByName("김");
        if (searchResult.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            for (Lion lion : searchResult) {
                System.out.println(lion.getInfo());
            }
        }

        System.out.println("\n=== 파트 필터링: '백엔드' ===");
        List<Lion> backendList = manager.filterByPart("백엔드");
        for (Lion lion : backendList) {
            System.out.println(lion.getInfo());
        }

        System.out.println("\n=== 파트별 그룹 현황 ===");
        Map<String, List<Lion>> partMap = manager.groupByPart();
        for (Map.Entry<String, List<Lion>> entry : partMap.entrySet()) {
            System.out.println("[" + entry.getKey() + "] " + entry.getValue().size() + "명");
            for (Lion lion : entry.getValue()) {
                System.out.println("  - " + lion.getInfo());
            }
        }
    }
}
