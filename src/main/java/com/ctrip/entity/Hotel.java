package com.ctrip.entity;

/**
 * Created by LucasX on 2016/2/23.
 */
public class Hotel {

    private String id;
    private String name;
    private String zone;
    private String price;
    private String point;
    private String voter;
    private String distance;
    private String star;

    public Hotel(String id, String name, String zone, String price, String point, String voter, String distance, String star) {
        this.id = id;
        this.name = name;
        this.zone = zone;
        this.price = price;
        this.point = point;
        this.voter = voter;
        this.distance = distance;
        this.star = star;
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

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", zone='" + zone + '\'' +
                ", price='" + price + '\'' +
                ", point='" + point + '\'' +
                ", voter='" + voter + '\'' +
                ", distance='" + distance + '\'' +
                ", star='" + star + '\'' +
                '}';
    }
}
