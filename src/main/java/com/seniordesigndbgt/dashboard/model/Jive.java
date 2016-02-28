package com.seniordesigndbgt.dashboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Table(name="jive")
public class Jive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 3, max = 200)
    @Column(name = "url")
    private String url;

    @NotNull
    @Size(min = 3, max = 1000)
    @Column(name = "content", unique = true)
    private String content;

    @Size(min = 3, max = 200)
    @Column(name = "sentiment")
    private String sentiment;

    public Jive() {}

    public Jive(String url, String content, String sentiment) {
        this.url = url;
        this.content = content;
        this.sentiment = sentiment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

}
