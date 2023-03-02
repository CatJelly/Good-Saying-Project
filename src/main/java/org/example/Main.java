package org.example;

/*
 * 자바 명언 앱 자동 생성기 프로젝트
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int sayingNum = 1;

    public static void main(String[] args) {
        List<GoodSaying> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");

            String command = sc.nextLine();

            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String saying = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                GoodSaying temp = new GoodSaying(author, saying);
                list.add(temp);

                System.out.printf("%d번 명언이 등록되었습니다.\n", temp.getId());
            }
        }

        sc.close();
    }
}