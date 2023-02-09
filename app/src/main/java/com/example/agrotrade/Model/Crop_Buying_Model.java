package com.example.agrotrade.Model;

public class Crop_Buying_Model {
    private String farmer_id,product_id,product_img,product_f_id,product_cat_name,product_crop_name,product_qty,product_price,product_description,farmer_name;

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(String farmer_id) {
        this.farmer_id = farmer_id;
    }

    public String getProduct_f_id() {
        return product_f_id;
    }

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public void setProduct_f_id(String product_f_id) {
        this.product_f_id = product_f_id;
    }

    public String getProduct_cat_name() {
        return product_cat_name;
    }

    public void setProduct_cat_name(String product_cat_name) {
        this.product_cat_name = product_cat_name;
    }

    public String getProduct_crop_name() {
        return product_crop_name;
    }

    public void setProduct_crop_name(String product_crop_name) {
        this.product_crop_name = product_crop_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(String product_qty) {
        this.product_qty = product_qty;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }
}
