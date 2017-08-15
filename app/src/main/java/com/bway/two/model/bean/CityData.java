package com.bway.two.model.bean;

/**
 * Created by 卢程
 * 2017/8/14.
 */

public class CityData {
    private int id;
    private String areaname;
    private String shortname;
    private String lng;
    private String lat;

    public CityData() {
    }

    public CityData(int id, String areaname, String shortname, String lng, String lat) {
        this.id = id;
        this.areaname = areaname;
        this.shortname = shortname;
        this.lng = lng;
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
