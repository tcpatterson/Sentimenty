package com.seniordesigndbgt.dashboard.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
@Table(name="trend")
public class Trend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 3, max = 200)
    @Column(name = "trendTitle")
    private String trendTitle;

    @NotNull
    @Size(min = 3, max = 500)
    @Column(name = "mentions")
    private String mentions;

    public Trend() {}

    public Trend(String trendTitle, String mentions) {
        this.trendTitle = trendTitle;
        this.mentions = mentions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrendTitle() {
        return trendTitle;
    }

    public void setTrendTitle(String trendTitle) {
        this.trendTitle = trendTitle;
    }

    public String getMentions() {
        return mentions;
    }

    public void setMentions(String mentions) {
        this.mentions = mentions;
    }
}
