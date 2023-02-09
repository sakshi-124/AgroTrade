package com.example.agrotrade.Model;

public class LoginModel {
    int data;
    int f_id;
    int c_id;

    String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


    public int getF_id() {
        return f_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }
}
