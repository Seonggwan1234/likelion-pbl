import java.util.Scanner;

public class Ex_Scanner {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("명단의 수를 입력하세요: ");
        int size = sc.nextInt();
        System.out.println();

        while(size <= 5)
        {
            System.out.println("6명 이상부터 작성 가능합니다.");
            System.out.print("명단의 수를 다시 입력하세요: ");
            size = sc.nextInt();
            System.out.println();
        }

        String[] arr = new String[size];

        for(int i = 0; i < size; i++)
        {
            System.out.print("이름: ");
            arr[i] = sc.next();

        }

        System.out.println();
        System.out.println("명단 출력");

        for(int i = 0; i < size; i++)
        {
            System.out.println(i+1 +"." + arr[i]);
        }
    }
}
