package org.example;

public class GoodSaying {
    private String author;
    private String saying;
    private int id;

    public GoodSaying(String author, String saying) {
        this.author = author;
        this.saying = saying;
        id = App.sayingNum;
    }

    public String getAuthor() {
        return author;
    }

    public String getSaying() {
        return saying;
    }

    public int getId() {
        return id;
    }
}
