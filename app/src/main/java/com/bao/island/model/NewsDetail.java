package com.bao.island.model;

import java.util.List;

/**
 * Created by BAO on 2018/5/9.
 */

public class NewsDetail {
    private String title;
    private String desc;
    private String content;
    private List<String> imgs;


    public NewsDetail() {
    }

    public NewsDetail(String title, String desc, String content, List<String> imgs) {
        this.title = title;
        this.desc = desc;
        this.content = content;
        this.imgs = imgs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
