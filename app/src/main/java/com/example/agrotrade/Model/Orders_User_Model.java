package com.example.agrotrade.Model;

public class Orders_User_Model {
//    private String id,f_id,cust_id,prod_id,receipt_no,saledetail,order_status,pay_type,pay_status,del_add,del_date,qty,t_cost,amt,total_amt,date,month,yr,t_id,img;
   private String salemaster_crop_cust_id,saledetail_crop_total_amt,saledetail_crop_qty,salemaster_crop_order_date,salemaster_crop_pay_type,farmer_name,product_crop_name,product_img;

    public String getFarmer_name() {
        return farmer_name;
    }

    public void setFarmer_name(String farmer_name) {
        this.farmer_name = farmer_name;
    }

    public String getProduct_crop_name() {
        return product_crop_name;
    }

    public void setProduct_crop_name(String product_crop_name) {
        this.product_crop_name = product_crop_name;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getSalemaster_crop_pay_type() {
        return salemaster_crop_pay_type;
    }

    public void setSalemaster_crop_pay_type(String salemaster_crop_pay_type) {
        this.salemaster_crop_pay_type = salemaster_crop_pay_type;
    }

    public String getSalemaster_crop_cust_id() {
        return salemaster_crop_cust_id;
    }

    public void setSalemaster_crop_cust_id(String salemaster_crop_cust_id) {
        this.salemaster_crop_cust_id = salemaster_crop_cust_id;
    }

    public String getSaledetail_crop_total_amt() {
        return saledetail_crop_total_amt;
    }

    public void setSaledetail_crop_total_amt(String saledetail_crop_total_amt) {
        this.saledetail_crop_total_amt = saledetail_crop_total_amt;
    }

    public String getSaledetail_crop_qty() {
        return saledetail_crop_qty;
    }

    public void setSaledetail_crop_qty(String saledetail_crop_qty) {
        this.saledetail_crop_qty = saledetail_crop_qty;
    }

    public String getSalemaster_crop_order_date() {
        return salemaster_crop_order_date;
    }

    public void setSalemaster_crop_order_date(String salemaster_crop_order_date) {
        this.salemaster_crop_order_date = salemaster_crop_order_date;
    }
}
