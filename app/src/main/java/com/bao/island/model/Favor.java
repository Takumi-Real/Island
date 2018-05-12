package com.bao.island.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.litepal.crud.DataSupport;

/**
 * Created by BAO on 2018/5/10.
 */

public class Favor extends DataSupport implements MultiItemEntity{
    private String title;
    private String url;
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
