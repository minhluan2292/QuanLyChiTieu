package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;

/**
 * Created by NHATLAM on 2/3/2017.
 */

public class CategoryProvider {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int img_res;
    private String name;

    public CategoryProvider(int id,int img_res, String name) {
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
