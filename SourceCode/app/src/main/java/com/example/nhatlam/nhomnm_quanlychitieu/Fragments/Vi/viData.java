package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;

/**
 * Created by NHATLAM on 2/8/2017.
 */

public class viData {
    int id;
    String viname;

    public viData(){
        this.id = 0;
        this.viname= "";
    }
    public viData(int id,String text){
        this.id = id;
        this.viname=text;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViname() {
        return viname;
    }

    public void setViname(String viname) {
        this.viname = viname;
    }
}
