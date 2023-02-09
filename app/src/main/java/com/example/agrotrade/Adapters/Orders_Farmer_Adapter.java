package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.agrotrade.Model.Orders_Farmer_Model;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Orders_Farmer_Adapter extends ArrayAdapter<Orders_Farmer_Model> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Orders_Farmer_Model> data = new ArrayList<Orders_Farmer_Model>();
    Orders_Farmer_Model orders_user_model;

    public Orders_Farmer_Adapter(Activity activity, int layoutResourceId, ArrayList<Orders_Farmer_Model> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Order_Farmer_Holder holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new Order_Farmer_Holder();


            holder.ferti_pesti_info_id=(TextView)row.findViewById(R.id.fp_id);
            holder.ferti_pesti_info_name = (TextView) row.findViewById(R.id.fp_name);
            holder.salemaster_fp_order_date = (TextView) row.findViewById(R.id.fp_del_date);
            holder.saledetail_fp_total_amt = (TextView) row.findViewById(R.id.fp_total_amt);
            holder.saledetail_fp_qty = (TextView) row.findViewById(R.id.fp_qty);
            holder.salemaster_fp_pay_type=(TextView)row.findViewById(R.id.fp_pay_type);
            holder.ferti_pesti_info_img=(ImageView)row.findViewById(R.id.myimagelogo);
            row.setTag(holder);
        } else {
            holder = (Orders_Farmer_Adapter.Order_Farmer_Holder) row.getTag();
        }

        orders_user_model = data.get(position);
        holder.ferti_pesti_info_id.setText(orders_user_model.getFerti_pesti_info_id());
        holder.ferti_pesti_info_name.setText(orders_user_model.getFerti_pesti_info_name());
        holder.salemaster_fp_order_date.setText(orders_user_model.getSalemaster_fp_order_date());
        holder.saledetail_fp_total_amt.setText(orders_user_model.getSaledetail_fp_total_amt());
        holder.saledetail_fp_qty.setText(orders_user_model.getSaledetail_fp_qty());
        holder.salemaster_fp_pay_type.setText(orders_user_model.getSalemaster_fp_pay_type());

//        holder.product_qty.setText(crop_buying_model.getProduct_qty());
//        holder.product_description.setText(crop_buying_model.getProduct_description());
//        holder.farmer_name.setText(crop_buying_model.getFarmer_name());
       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */



        Picasso.get()
                .load(orders_user_model.getFerti_pesti_info_img())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.ferti_pesti_info_img);



//        Glide.with(context)
//                .load(singleProduct.getImg())
//                .into(holder.myimagelogo);


//        implementation 'com.github.bumptech.glide:glide:3.8.0'

        return row;
    }

    class Order_Farmer_Holder {
       // TextView salemaster_crop_cust_id,salemaster_crop_total_amt,salemaster_crop_qty,salemaster_crop_order_date,salemaster_crop_pay_type;
        TextView salemaster_fp_id,salemaster_fp_pay_type,salemaster_fp_pay_status,salemaster_fp_address,salemaster_fp_order_date,saledetail_fp_qty,saledetail_fp_total_amt,farmer_id,ferti_pesti_info_id,ferti_pesti_info_name;
        ImageView ferti_pesti_info_img;
    }
}
