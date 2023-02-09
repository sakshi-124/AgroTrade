package com.example.agrotrade.Helpers;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HTTPRequestDAO {



    public String FarmerLogin(String mobileno, String pass) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("mobileno", mobileno));
        param.add(new BasicNameValuePair("pass", pass));



        String json = new HttpRequestHelper().postRequest("/farmer_login.php", param);
        return json;

    }

    public String CustomerLogin(String username, String password) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("name", username));
        param.add(new BasicNameValuePair("password", password));



        String json = new HttpRequestHelper().postRequest("/customer_login.php", param);
        return json;

    }

    public String DeleteProduct(String pid) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("id", pid));




        String json = new HttpRequestHelper().postRequest("/delete_product.php", param);
        return json;

    }

    public String Customerotp(String mobileno,  String otp) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("mobileno", mobileno));
        param.add(new BasicNameValuePair("otp", otp));



        String json = new HttpRequestHelper().postRequest("/customer_otp_verify.php", param);
        return json;

    }

    public String Farmerotp(String mobileno,  String otp) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("mobileno", mobileno));
        param.add(new BasicNameValuePair("otp", otp));



        String json = new HttpRequestHelper().postRequest("/farmer_otp_verify.php", param);
        return json;

    }

    public String Feedback(String user_id,String msg) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("customerid", user_id));
        param.add(new BasicNameValuePair("msg", msg));




        String json = new HttpRequestHelper().postRequest("/feedback.php", param);
        return json;

    }

    public String order(String order,String receiver,String mobile,String address,String user_id) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("order", order));
        param.add(new BasicNameValuePair("receiveby", receiver));
        param.add(new BasicNameValuePair("mobile", mobile));
        param.add(new BasicNameValuePair("address", address));
        param.add(new BasicNameValuePair("user_id", user_id));




        String json = new HttpRequestHelper().postRequest("/order.php", param);
        return json;

    }

    public String orderonline(String order,String receiver,String mobile,String address,String user_id) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("order", order));
        param.add(new BasicNameValuePair("receiveby", receiver));
        param.add(new BasicNameValuePair("mobile", mobile));
        param.add(new BasicNameValuePair("address", address));
        param.add(new BasicNameValuePair("user_id", user_id));




        String json = new HttpRequestHelper().postRequest("/orderonline.php", param);
        return json;

    }


    public String FarmerRegistration(String name, String address, String mobileno, String password, String lat, String lgt) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("name", name));
        param.add(new BasicNameValuePair("address", address));
        param.add(new BasicNameValuePair("mobileno", mobileno));
        param.add(new BasicNameValuePair("pass", password));
        param.add(new BasicNameValuePair("lat", lat));
        param.add(new BasicNameValuePair("lgt", lgt));

        String json = new HttpRequestHelper().postRequest("/farmer_reg.php", param);
        return json;

    }

    public String CustomerRegistration( String address, String mobileno, String password,String username, String lat, String lgt) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("address", address));
        param.add(new BasicNameValuePair("mobileno", mobileno));
        param.add(new BasicNameValuePair("password", password));
        param.add(new BasicNameValuePair("name", username));
        param.add(new BasicNameValuePair("lat", lat));
        param.add(new BasicNameValuePair("lgt", lgt));





        String json = new HttpRequestHelper().postRequest("/customer_reg.php", param);
        return json;

    }


    public String getMasterData(String table) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("table", table));





        String json = new HttpRequestHelper().postRequest("/getmasterdata.php", param);
        return json;

    }

    public String getSingleData(String table , String db_filed_name , String db_field_value) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("table", table));
        param.add(new BasicNameValuePair("db_field_name", db_filed_name));
        param.add(new BasicNameValuePair("db_field_value", db_field_value));

        String json = new HttpRequestHelper().postRequest("/getsingledata.php", param);
        return json;

    }

    public String getJoinData(String ltable , String rtable ,String lid , String rid) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("ltable", ltable));
        param.add(new BasicNameValuePair("rtable", rtable));
        param.add(new BasicNameValuePair("lid", lid));
        param.add(new BasicNameValuePair("rid", rid));
        String json = new HttpRequestHelper().postRequest("/getjoindata.php", param);
        return json;

    }

    public String getSingleExpense(String f_id , String pro_id) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("f_id", f_id));
        param.add(new BasicNameValuePair("pro_id", pro_id));
        String json = new HttpRequestHelper().postRequest("/get_expense_single.php", param);
        return json;

    }

    public String getMasterExpense(String f_id) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("f_id", f_id));
        String json = new HttpRequestHelper().postRequest("/get_expense_master.php", param);
        return json;

    }

    public String getJoinData_cond(String fname,String fvalue) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("fld_name", fname));
        param.add(new BasicNameValuePair("fld_val", fvalue));
        String json = new HttpRequestHelper().postRequest("/getjoindata_cond.php", param);
        return json;

    }


    public String getJoinData_Ferti(String fname,String fvalue) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("fld_name", fname));
        param.add(new BasicNameValuePair("fld_val", fvalue));
        String json = new HttpRequestHelper().postRequest("/getjoindata_ferti.php", param);
        return json;

    }
    public String getFarmer_rec_order(String fname,String fvalue) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("fld_name", fname));
        param.add(new BasicNameValuePair("fld_val", fvalue));
        String json = new HttpRequestHelper().postRequest("/farmer_rec_order.php", param);
        return json;

    }


    public String getItem_list(String fname1,String fvalue1,String fname2,String fvalue2) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("fld_name1", fname1));
        param.add(new BasicNameValuePair("fld_val1", fvalue1));
        param.add(new BasicNameValuePair("fld_name2", fname2));
        param.add(new BasicNameValuePair("fld_val2", fvalue2));
        String json = new HttpRequestHelper().postRequest("/getitemlist_trans.php", param);
        return json;

    }


    public String CropOrder(String jsond , String userid ,String address , String pay) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("json", jsond));
        param.add(new BasicNameValuePair("user_id", userid));
        param.add(new BasicNameValuePair("address", address));
        param.add(new BasicNameValuePair("pay_type", pay));
        String json = new HttpRequestHelper().postRequest("/crop_place_order.php", param);
        return json;

    }

    public String FertiPestiOrder(String jsond , String userid ,String address , String pay) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("json", jsond));
        param.add(new BasicNameValuePair("user_id", userid));
        param.add(new BasicNameValuePair("address", address));
        param.add(new BasicNameValuePair("pay_type", pay));
        String json = new HttpRequestHelper().postRequest("/ferti_place_order.php", param);
        return json;

    }

    public String  AddExpense(String f_cost, String p_cost, String half_no_worker, String half_sal, String full_no_worker, String full_sal, String extra_cost, String extra_note, String total_income, String date,String f_id,String pro_id) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("f_cost", f_cost));
        param.add(new BasicNameValuePair("p_cost", p_cost));
        param.add(new BasicNameValuePair("half_no_worker", half_no_worker));
        param.add(new BasicNameValuePair("half_sal", half_sal));
        param.add(new BasicNameValuePair("full_no_worker", full_no_worker));
        param.add(new BasicNameValuePair("full_sal", full_sal));
        param.add(new BasicNameValuePair("extra_cost", extra_cost));
        param.add(new BasicNameValuePair("extra_note", extra_note));
        param.add(new BasicNameValuePair("total_income", total_income));
        param.add(new BasicNameValuePair("date", date));
        param.add(new BasicNameValuePair("f_id", f_id));
        param.add(new BasicNameValuePair("pro_id", pro_id));

        String json = new HttpRequestHelper().postRequest("/add_expense.php", param);
        return json;

    }

    public String AddCropSelling(String f_id, String cat_name, String crop_name, String qty, String price, String description,String img) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("cat_name", cat_name));
        param.add(new BasicNameValuePair("crop_name", crop_name));
        param.add(new BasicNameValuePair("qty", qty));
        param.add(new BasicNameValuePair("price", price));
        param.add(new BasicNameValuePair("description", description));
        param.add(new BasicNameValuePair("f_id", f_id));
        param.add(new BasicNameValuePair("image", img));

        String json = new HttpRequestHelper().postRequest("/add_product.php", param);
        return json;

    }


    public String UpdateProduct(String json1, String tablename, String fieldname, String fieldvalue) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("table", tablename));
        param.add(new BasicNameValuePair("fldname", fieldname));
        param.add(new BasicNameValuePair("fldval", fieldvalue));
        param.add(new BasicNameValuePair("json", json1));


        String json = new HttpRequestHelper().postRequest("/updatedata.php", param);
        return json;

    }

    public String insertData(String table , String data) {
        List<NameValuePair> param = new ArrayList<NameValuePair>();
        param.add(new BasicNameValuePair("table", table));
        param.add(new BasicNameValuePair("json", data));

        String json = new HttpRequestHelper().postRequest("/insertdata.php", param);
        return json;

    }





}

