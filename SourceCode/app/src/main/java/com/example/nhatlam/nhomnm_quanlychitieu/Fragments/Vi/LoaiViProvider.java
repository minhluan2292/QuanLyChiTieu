package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;

/**
 * Created by MinhLuan on 2/4/2017.
 */

public class LoaiViProvider {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int img_res;
    private String name;

    public LoaiViProvider(int id,int img_res, String name) {
        this.id = id;
        this.img_res = img_res;
        this.name = name;
    }

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}