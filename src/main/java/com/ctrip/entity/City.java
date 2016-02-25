package com.ctrip.entity;

/**
 * Created by LucasX on 2016/2/23.
 */
public class City {
    private String pinyin;
    private String id;
    private String name;

    public City(String pinyin, String id, String name) {
        this.pinyin = pinyin;
        this.id = id;
        this.name = name;
    }

    public City() {
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "pinyin='" + pinyin + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
