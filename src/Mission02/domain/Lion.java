package Mission02.domain;

public class Lion {
    // 필드 접근 제어자 분리
    public String name;
    String major;         // default
    private int generation;

    // 생성자 (모든 필드 초기화, 출력문 없음)
    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    // [Step 2] 유효성 검증 로직을 객체 내부로 캡슐화
    public boolean isValid() {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (major == null || major.trim().isEmpty()) {
            return false;
        }
        if (generation < 1) {
            return false;
        }
        return true;
    }
}
