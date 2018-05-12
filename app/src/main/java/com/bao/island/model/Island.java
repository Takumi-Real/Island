package com.bao.island.model;

/**
 * Created by BAO on 2018/4/29.
 */

public class Island {

    private int id;
    private String title;
    private String cover;
    private String content;
    private String pics;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



}
