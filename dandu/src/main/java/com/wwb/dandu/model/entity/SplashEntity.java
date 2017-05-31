package com.wwb.dandu.model.entity;

import java.util.List;

/**
 * Created by wwb on 2017/1/19.
 */

public class SplashEntity {
    /**
     * status : ok
     * time : 1468154610
     * images : [ "http://img.owspace.com/Public/uploads/Picture/2017-01-16/587c4320e9c3f.jpg",
     * "http://img.owspace.com/Public/uploads/Picture/2017-01-11/5875c3572ee12.jpg",
     * "http://img.owspace.com/Public/uploads/Picture/2016-11-22/5834344f67f81.jpeg"]
     * count : 3
     */
    private String status;
    private int time;
    private int count;
    private List<String> images;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
