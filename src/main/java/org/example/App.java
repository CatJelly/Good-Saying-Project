package org.example;

import java.util.*;

public class App {
    static int sayingNum = 1;
    private Map<Integer, GoodSaying> map;


    public void init() {
        map = new HashMap<>();
    }

    public void showTitle() {
        System.out.println("== 명언 앱 ==");
    }

    public GoodSaying addSaying(String author, String saying) {
        GoodSaying temp = new GoodSaying(author, saying);
        map.put(sayingNum, temp);
        sayingNum++;
        return temp;
    }

    public void printSaying() {
        System.out.println("번호\t/ 작가\t/ 명언");
        for(GoodSaying gs : map.values()) {
            System.out.println(gs);
        }
    }

    public GoodSaying checkId(int id) {
        return map.get(id);
    }
    public void deleteSaying(int id) {
        GoodSaying deleteObj = checkId(id);
        if(deleteObj == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }
        else {
            map.remove(id);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
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
            else if(command.contains("삭제")) {
                String [] split = command.split("\\?|=");
                deleteSaying(Integer.parseInt(split[2]));
            }
        }

        sc.close();
    }
}
