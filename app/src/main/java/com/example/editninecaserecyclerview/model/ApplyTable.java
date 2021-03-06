package com.example.editninecaserecyclerview.model;

import java.io.Serializable;

/**
 * 应用描述
 *
 * create by bthvi  2018/06/01
 */
public class ApplyTable implements Serializable {
    private String name;//显示的文字，应用的名称
    private String id;
    private String type;
    private String url;//web地址
    private boolean fixed;//点击是否可以进行增删
    private int index;//控件的初始位置，可以用于判断长按是否可以拖拽
    private int imgRes;//显示的图片
    private int state;//是否处于头部的状态,0：在头部，1：不在头部

    public ApplyTable() {
    }

    public ApplyTable(String name) {
        this.name = name;
    }

    public ApplyTable(String name, String id, String type, String url,
                      int index, boolean fixed, int imgRes, int state) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.url = url;
        this.fixed = fixed;
        this.index = index;
        this.imgRes = imgRes;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ApplyTable{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", fixed=" + fixed +
                ", index=" + index +
                ", imgRes=" + imgRes +
                ", state=" + state +
                '}';
    }
}
