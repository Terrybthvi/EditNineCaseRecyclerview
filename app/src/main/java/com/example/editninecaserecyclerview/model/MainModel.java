package com.example.editninecaserecyclerview.model;
/***
 * 首页列表数据结构
 *
 * create by bthvi  2018/06/01
 */
public class MainModel {
    private int typ;
    private String name;

    public MainModel(int typ, String name) {
        this.typ = typ;
        this.name = name;
    }

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "typ=" + typ +
                ", name='" + name + '\'' +
                '}';
    }
}
