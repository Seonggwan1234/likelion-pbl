package Mission02.app;

import Mission02.domain.Lion;
import java.util.Scanner;

public class Step2and3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("전공: ");
        String major = scanner.nextLine();

        System.out.print("기수: ");
        String genInput = scanner.nextLine();
        int generation = Integer.parseInt(genInput.replaceAll("[^0-9]", ""));

        Lion lion = new Lion(name, major, generation);

        if (lion.isValid()) {
            System.out.println("Lion 객체 스스로 유효성을 확인했습니다.");
            System.out.println("이름 접근 성공: " + lion.name);
        } else {
            System.out.println("Lion 객체 내부에 유효하지 않은 상태가 있습니다.");
        }

        scanner.close();
    }
}