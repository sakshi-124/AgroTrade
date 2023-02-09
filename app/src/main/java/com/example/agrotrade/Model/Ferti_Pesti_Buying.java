package com.example.agrotrade.Model;

public class Ferti_Pesti_Buying {

    private String id,name,price,unit,weight,description,pro_info,crop_name,pest_name,direction,img;

    String qty;

    int myid;


    public int getMyid() {
        return myid;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPro_info() {
        return pro_info;
    }

    public void setPro_info(String pro_info) {
        this.pro_info = pro_info;
    }

    public String getCrop_name() {
        return crop_name;
    }

    public void setCrop_name(String crop_name) {
        this.crop_name = crop_name;
    }

    public String getPest_name() {
        return pest_name;
    }

    public void setPest_name(String pest_name) {
        this.pest_name = pest_name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



    public Ferti_Pesti_Buying(){

    }


    public Ferti_Pesti_Buying(int myid, String id, String name,String price,String img, String qty) {
        this.myid = myid;
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.qty = qty;
    }


    public Ferti_Pesti_Buying(String id, String name,String price,String img, String qty) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.qty = qty;
    }
}
