package org.example.goodSaying.entity;

public class GoodSaying {
    private String author;
    private String saying;
    private int id;

    public GoodSaying(int id, String author, String saying) {
        this.author = author;
        this.saying = saying;
        this.id = id;
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

    public void setAuthor(String author) { this.author = author; }

    public void setSaying(String saying) { this.saying = saying; }

    @Override
    public String toString() {
        return String.format("%d\t/ %s\t/ %s", id, author, saying);
    }
}
