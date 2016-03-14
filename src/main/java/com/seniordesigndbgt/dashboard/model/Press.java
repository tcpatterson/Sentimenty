package com.seniordesigndbgt.dashboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="press")
public class Press {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @JoinColumn(name = "source")
    private String source;

    @NotNull
    @Size(min = 3, max = 200)
    @Column(name = "url")
    private String url;

    @NotNull
    @Size(min = 3, max = 200)
    @Column(name = "title", unique = true)
    private String title;

    @Size(min = 3, max = 200)
    @Column(name = "sentiment")
    private String sentiment;

    @Column(name = "keywords")
    private String keywords;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", columnDefinition="DATETIME")
    private Date time;

    public Press() {}

    public Press(String source, String url, String title, Date time) {
        this.source = source;
        this.url = url;
        this.title = title;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
