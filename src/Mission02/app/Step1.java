package Mission02.app;

import Mission02.domain.Lion;
import java.util.Scanner;

public class Step1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("전공: ");
        String major = scanner.nextLine();

        System.out.print("기수: ");
        String genInput = scanner.nextLine();
        int generation = Integer.parseInt(genInput.replaceAll("[^0-9]", ""));

        boolean isValidData = true;
        if (name == null || name.trim().isEmpty()) {
            isValidData = false;
        }
        if (major == null || major.trim().isEmpty()) {
            isValidData = false;
        }
        if (generation < 1) {
            isValidData = false;
        }

        if (isValidData) {
            Lion lion = new Lion(name, major, generation);
            System.out.println("검증 통과: Lion 객체가 생성되었습니다.");
        } else {
            System.out.println("검증 실패: 유효하지 않은 데이터입니다. 객체를 생성하지 않습니다.");
        }

        scanner.close();
    }
}