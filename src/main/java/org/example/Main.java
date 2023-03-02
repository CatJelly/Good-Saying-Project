package org.example;

/*
* 자바 명언 앱 자동 생성기 프로젝트
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("== 명언 앱 ==");

        while(true) {
            System.out.print("명령) ");

            String command = sc.nextLine();

            if(command.equals("종료")) {
                break;
            }

        }

        sc.close();
    }
}