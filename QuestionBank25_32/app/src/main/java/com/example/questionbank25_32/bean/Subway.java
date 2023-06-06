package com.example.questionbank25_32.bean;

import java.util.List;

/**
 * @ClassName Subway
 * @Author 史正龙
 * @date 2021.08.07 15:56
 */
public class Subway {
    private String name;
    private int id;
    private List<String> site;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getSite() {
        return site;
    }

    public void setSite(List<String> site) {
        this.site = site;
    }
}
