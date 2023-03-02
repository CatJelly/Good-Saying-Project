package org.example;

import org.example.goodSaying.entity.GoodSaying;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class App {
    static final String FILE_PATH = "data.txt";
    static int sayingNum = 1;
    private Scanner sc;
    private Map<Integer, GoodSaying> map;
    private File file;
    private FileReader fr;
    private FileWriter fw;
    private BufferedReader br;
    private BufferedWriter bw;

    public void init() {
        map = new HashMap<>();
        sc = new Scanner(System.in);
        file = new File(FILE_PATH);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fr = new FileReader(file);
            fw = new FileWriter(file, true);
        } catch (IOException e) {
        }

        br = new BufferedReader(fr);
        bw = new BufferedWriter(fw);
    }

    public void finish() {
        sc.close();
        try {
            bw.close();
            br.close();
            fw.close();
            fr.close();
        } catch (IOException e) {
        }
    }

    public void showTitle() {
        System.out.println("== 명언 앱 ==");
    }

    public void showCommandLine() {
        System.out.print("명령) ");
    }

    public GoodSaying addSaying(String author, String saying) {
        GoodSaying temp = new GoodSaying(sayingNum, author, saying);
        map.put(sayingNum, temp);
        sayingNum++;
        return temp;
    }

    public void printSaying() {
        System.out.println("번호\t/ 작가\t/ 명언");
        System.out.println("---------------------------------");
        for (int key : map.keySet().stream().sorted((o1, o2) -> o2 - o1).toList()) {
            System.out.println(map.get(key));
        }
    }

    public GoodSaying checkId(int id) {
        return map.get(id);
    }

    public void deleteSaying(int id) {
        GoodSaying deleteObj = checkId(id);
        if (deleteObj == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        } else {
            map.remove(id);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        }
    }

    public void modifySaying(int id) {
        GoodSaying modifyObj = checkId(id);
        if (modifyObj == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        } else {
            System.out.printf("명언(기존) : %s\n", modifyObj.getSaying());
            System.out.print("명언 : ");
            String saying = sc.nextLine().trim();
            modifyObj.setSaying(saying);
            System.out.printf("작가(기존) : %s\n", modifyObj.getAuthor());
            System.out.print("작가 : ");
            String author = sc.nextLine().trim();
            modifyObj.setAuthor(author);
            map.put(id, modifyObj);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        }
    }

    public void writeSaying() {
        for (GoodSaying gs : map.values()) {
            String res = String.format("%d\t%s\t%s\n", gs.getId(), gs.getAuthor(), gs.getSaying());
            try {
                bw.write(res);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readSaying() {
        String line = "";
        int id = 1;
        while (true) {
            try {
                if ((line = br.readLine()) == null)
                    break;
            } catch (IOException e) {
            }
            String[] split = line.split("\t");
            id = Integer.parseInt(split[0]);
            map.put(id, new GoodSaying(id, split[1], split[2]));
        }
        App.sayingNum = id + 1;
    }

    public void build() {
        try {
            BufferedWriter jsonBw = new BufferedWriter(new FileWriter(new File("data.json")));
            JSONObject json = new JSONObject();
            List<GoodSaying> list = map.values().stream().toList();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(list);
            jsonBw.write(jsonArray.toString());
            jsonBw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        init();
        readSaying();
        showTitle();
        while (true) {
            showCommandLine();

            String command = sc.nextLine().trim();

            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String saying = sc.nextLine().trim();
                System.out.print("작가 : ");
                String author = sc.nextLine().trim();

                GoodSaying temp = addSaying(author, saying);
                System.out.printf("%d번 명언이 등록되었습니다.\n", temp.getId());
            } else if (command.equals("목록")) {
                printSaying();
            } else if (command.contains("삭제")) {
                String[] split = command.split("\\?|=");
                deleteSaying(Integer.parseInt(split[2]));
            } else if (command.contains("수정")) {
                String[] split = command.split("\\?|=");
                modifySaying(Integer.parseInt(split[2]));
            } else if(command.equals("빌드")) {
                build();
                System.out.println("data.json 파일의 내용이 갱신되었습니다.");
            }

        }

        writeSaying();
        finish();
    }
}
