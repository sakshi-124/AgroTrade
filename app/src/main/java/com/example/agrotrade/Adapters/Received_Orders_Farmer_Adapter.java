package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrotrade.Model.Order_Received_Farmer_Model;
import com.example.agrotrade.Model.Orders_Farmer_Model;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Received_Orders_Farmer_Adapter extends ArrayAdapter<Order_Received_Farmer_Model> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Order_Received_Farmer_Model> data = new ArrayList<Order_Received_Farmer_Model>();
    Order_Received_Farmer_Model orders_user_model;

    public Received_Orders_Farmer_Adapter(Activity activity, int layoutResourceId, ArrayList<Order_Received_Farmer_Model> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Received_Order_Farmer_Holder holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new Received_Order_Farmer_Holder();


            holder.saledetail_crop_qty=(TextView)row.findViewById(R.id.saledetail_crop_qty);
            holder.saledetail_crop_total_amt = (TextView) row.findViewById(R.id.saledetail_crop_total_amt);
            holder.salemaster_crop_order_status = (TextView) row.findViewById(R.id.salemaster_crop_order_status);
//            holder.salemaster_crop_pay_status = (TextView) row.findViewById(R.id.salemaster_crop_pay_status);
            holder.salemaster_crop_order_date = (TextView) row.findViewById(R.id.salemaster_crop_order_date);
            holder.product_crop_name=(TextView)row.findViewById(R.id.product_crop_name);
            holder.customer_name=(TextView)row.findViewById(R.id.customer_name);
            holder.customer_mobileno=(TextView)row.findViewById(R.id.customer_mobileno);
            holder.product_img=(ImageView)row.findViewById(R.id.product_img);
            row.setTag(holder);
        } else {
            holder = (Received_Orders_Farmer_Adapter.Received_Order_Farmer_Holder) row.getTag();
        }

        ImageView product_img;
        orders_user_model = data.get(position);
        holder.saledetail_crop_qty.setText(orders_user_model.getSaledetail_crop_qty());
        holder.salemaster_crop_order_status.setText(orders_user_model.getSalemaster_crop_order_status());
        holder.saledetail_crop_total_amt.setText(orders_user_model.getSaledetail_crop_total_amt());
        holder.salemaster_crop_order_date.setText(orders_user_model.getSalemaster_crop_order_date());
        holder.product_crop_name.setText(orders_user_model.getProduct_crop_name());
        holder.customer_name.setText(orders_user_model.getCustomer_name());
        holder.customer_mobileno.setText(orders_user_model.getCustomer_mobileno());

//        holder.product_qty.setText(crop_buying_model.getProduct_qty());
//        holder.product_description.setText(crop_buying_model.getProduct_description());
//        holder.farmer_name.setText(crop_buying_model.getFarmer_name());
       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */



        Picasso.get()
                .load(orders_user_model.getProduct_img())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.product_img);



//        Glide.with(context)
//                .load(singleProduct.getImg())
//                .into(holder.myimagelogo);


//        implementation 'com.github.bumptech.glide:glide:3.8.0'

        return row;
    }

    class Received_Order_Farmer_Holder {
        TextView farmer_id,saledetail_crop_qty,saledetail_crop_total_amt,salemaster_crop_order_status,salemaster_crop_pay_status,salemaster_crop_order_date,product_crop_name,customer_name,customer_mobileno;
        ImageView product_img;
    }
}
