package com.ctrip.entity;

import java.util.List;

/**
 * Created by LucasX on 2016/2/26.
 */
public class Csv {

    private String name;
    private List<Hotel> list;

    public Csv(String name, List<Hotel> list) {
        this.name = name;
        this.list = list;
    }

    public Csv() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Hotel> getList() {
        return list;
    }

    public void setList(List<Hotel> list) {
        this.list = list;
    }
}
