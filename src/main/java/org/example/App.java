package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static int sayingNum = 1;
    private List<GoodSaying> list;


    public void init() {
        list = new ArrayList<>();
    }

    public void showTitle() {
        System.out.println("== 명언 앱 ==");
    }

    public GoodSaying addSaying(String author, String saying) {
        GoodSaying temp = new GoodSaying(author, saying);
        list.add(temp);
        sayingNum++;
        return temp;
    }

    public void printSaying() {
        System.out.println("번호\t/ 작가\t/ 명언");
        for(GoodSaying gs : list) {
            System.out.println(gs);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        init();
        showTitle();
        while (true) {
            System.out.print("명령) ");

            String command = sc.nextLine();

            if (command.equals("종료")) {
                break;
            }
            else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String saying = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();

                GoodSaying temp = addSaying(author, saying);
                System.out.printf("%d번 명언이 등록되었습니다.\n", temp.getId());
            }
            else if(command.equals("목록")) {
                printSaying();
            }
        }

        sc.close();
    }
}
