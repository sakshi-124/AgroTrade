package com.example.agrotrade.Model;

public class Farmer_ProductModel {
    String pro_id;
    String pro_cat_id;
    String p_name;
    String prize;
    String p_code;
    String stock;
    String basic;
    String dis;
    String actualprize;
    String unit;

    String qty;




    int id;

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getActualprize() {
        return actualprize;
    }

    public void setActualprize(String actualprize) {
        this.actualprize = actualprize;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    String img;

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_cat_id() {
        return pro_cat_id;
    }

    public void setPro_cat_id(String pro_cat_id) {
        this.pro_cat_id = pro_cat_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Farmer_ProductModel() {
    }

    public Farmer_ProductModel(int id, String pro_id, String p_name, String prize, String img, String qty) {
        this.pro_id = pro_id;
        this.id = id;
        this.p_name = p_name;
        this.prize = prize;
        this.img = img;
        this.qty = qty;
        this.unit = unit;
    }
    public Farmer_ProductModel(String pro_id, String p_name, String prize, String img, String qty) {
        this.pro_id = pro_id;
        this.p_name = p_name;
        this.prize = prize;
        this.img = img;
        this.qty = qty;
        this.unit = unit;
    }
}
