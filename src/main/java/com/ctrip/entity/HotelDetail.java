package com.ctrip.entity;

/**
 * Created by LucasX on 2016/2/29.
 */
public class HotelDetail {

    private int hotelId;
    private String provname;
    private String name;
    private String shrtName;
    private String addr;
    private String zone;
    private String star;
    private String open;
    private String fitment;
    private String phe;
    private String brief;
    private String desc;
    private String vote;
    private String point;
    private String rat;
    private String raAt;
    private String serv;
    private String facl;
    private String cname;
    private String around;
    private String brefast;

    public HotelDetail(int hotelId, String provname, String name, String shrtName, String addr, String zone, String star, String open, String fitment, String phe, String brief, String desc, String vote, String point, String rat, String raAt, String serv, String facl, String cname, String around, String brefast) {
        this.hotelId = hotelId;
        this.provname = provname;
        this.name = name;
        this.shrtName = shrtName;
        this.addr = addr;
        this.zone = zone;
        this.star = star;
        this.open = open;
        this.fitment = fitment;
        this.phe = phe;
        this.brief = brief;
        this.desc = desc;
        this.vote = vote;
        this.point = point;
        this.rat = rat;
        this.raAt = raAt;
        this.serv = serv;
        this.facl = facl;
        this.cname = cname;
        this.around = around;
        this.brefast = brefast;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getProvname() {
        return provname;
    }

    public void setProvname(String provname) {
        this.provname = provname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShrtName() {
        return shrtName;
    }

    public void setShrtName(String shrtName) {
        this.shrtName = shrtName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getFitment() {
        return fitment;
    }

    public void setFitment(String fitment) {
        this.fitment = fitment;
    }

    public String getPhe() {
        return phe;
    }

    public void setPhe(String phe) {
        this.phe = phe;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRat() {
        return rat;
    }

    public void setRat(String rat) {
        this.rat = rat;
    }

    public String getRaAt() {
        return raAt;
    }

    public void setRaAt(String raAt) {
        this.raAt = raAt;
    }

    public String getServ() {
        return serv;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public String getFacl() {
        return facl;
    }

    public void setFacl(String facl) {
        this.facl = facl;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAround() {
        return around;
    }

    public void setAround(String around) {
        this.around = around;
    }

    public String getBrefast() {
        return brefast;
    }

    public void setBrefast(String brefast) {
        this.brefast = brefast;
    }

    @Override
    public String toString() {
        return "HotelDetail{" +
                "hotelId='" + hotelId + '\'' +
                ", provname='" + provname + '\'' +
                ", name='" + name + '\'' +
                ", shrtName='" + shrtName + '\'' +
                ", addr='" + addr + '\'' +
                ", zone='" + zone + '\'' +
                ", star='" + star + '\'' +
                ", open='" + open + '\'' +
                ", fitment='" + fitment + '\'' +
                ", phe='" + phe + '\'' +
                ", brief='" + brief + '\'' +
                ", desc='" + desc + '\'' +
                ", vote='" + vote + '\'' +
                ", point='" + point + '\'' +
                ", rat='" + rat + '\'' +
                ", raAt='" + raAt + '\'' +
                ", serv='" + serv + '\'' +
                ", facl='" + facl + '\'' +
                ", cname='" + cname + '\'' +
                ", around='" + around + '\'' +
                ", brefast='" + brefast + '\'' +
                '}';
    }
}
