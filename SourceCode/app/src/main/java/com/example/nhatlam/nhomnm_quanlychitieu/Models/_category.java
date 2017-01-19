package com.example.nhatlam.nhomnm_quanlychitieu.Models;

/**
 * Created by NHATLAM on 1/18/2017.
 */

public class _category {
    private int category_id;
    private String category_name;
    private int parent;

    public _category(int category_id, String category_name, int parent) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.parent = parent;
    }

    public _category(String category_name, int parent) {
        this.category_name = category_name;
        this.parent = parent;
    }

    public _category(int category_id, String category_name) {
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public _category(String category_name) {
        this.category_name = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
