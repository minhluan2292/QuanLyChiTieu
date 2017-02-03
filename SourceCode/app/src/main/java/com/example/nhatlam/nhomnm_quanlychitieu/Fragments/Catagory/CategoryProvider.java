package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;

/**
 * Created by NHATLAM on 2/3/2017.
 */

public class CategoryProvider {
    private int img_res;
    private String name;

    public CategoryProvider(int img_res, String name) {
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
