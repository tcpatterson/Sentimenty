package com.seniordesigndbgt.dashboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

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

    public Press() {}

    public Press(String source, String url, String title) {
        this.source = source;
        this.url = url;
        this.title = title;
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
}
