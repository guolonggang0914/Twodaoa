package com.bway.two.model.bean;

/**
 * Created by 卢程
 * 2017/8/12.
 */

public class Foods {
    private String message;
    private int imageid;

    public Foods(String message, int imageid) {
        this.message = message;
        this.imageid = imageid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
