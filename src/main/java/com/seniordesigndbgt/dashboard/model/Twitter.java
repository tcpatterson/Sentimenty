package com.seniordesigndbgt.dashboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="twitter")
public class Twitter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "text")
    private String text;


    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "author")
    private String author;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "keyword")
    private String keyword;

    public Twitter(String author, String text, String keyword) {
        this.author = author;
        this.text = text;
        this.keyword = keyword;
    }

    public Twitter() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("@"+author+" - ");
        sb.append(text);

        return sb.toString();
    }
}
