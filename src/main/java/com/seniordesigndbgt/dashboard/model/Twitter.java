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
    @Size(min=1, max = 750)
    @Column(name="url")
    private String url;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name="author")
    private String author;

    public Twitter(String author, String text, String url) {
        this.author = author;
        this.text = text;
        this.url = url;
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

    public String getUrl() { return url; }


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("@"+author+" - ");
        sb.append(text);

        return sb.toString();
    }
}
