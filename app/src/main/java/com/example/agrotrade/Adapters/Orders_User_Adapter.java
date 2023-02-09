package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.agrotrade.Model.Orders_User_Model;
import com.example.agrotrade.R;

import java.util.ArrayList;

public class Orders_User_Adapter extends ArrayAdapter<Orders_User_Model> {

    Activity activity;
    int layoutResourceId;
    ArrayList<Orders_User_Model> data = new ArrayList<Orders_User_Model>();
    Orders_User_Model orders_user_model;

    public Orders_User_Adapter(Activity activity, int layoutResourceId, ArrayList<Orders_User_Model> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Order_User_Holder holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new Order_User_Holder();
            holder.product_crop_name = (TextView) row.findViewById(R.id.product_name);
            holder.farmer_name = (TextView) row.findViewById(R.id.farmer_name);
            // holder.feeddes= (TextView) row.findViewById(R.id.textViewAddress);
            // holder.feedrating= (TextView) row.findViewById(R.id.textViewmob);
            holder.salemaster_crop_order_date = (TextView) row.findViewById(R.id.del_date);
            holder.saledetail_crop_total_amt = (TextView) row.findViewById(R.id.total_amt);
            holder.saledetail_crop_qty = (TextView) row.findViewById(R.id.qty);
            holder.salemaster_crop_pay_type=(TextView)row.findViewById(R.id.pay_type);
//            holder.product_qty = (TextView) row.findViewById(R.id.qty);
//            holder.product_description = (TextView) row.findViewById(R.id.description);
//            holder.farmer_name = (TextView) row.findViewById(R.id.fname);


            row.setTag(holder);
        } else {
            holder = (Orders_User_Adapter.Order_User_Holder) row.getTag();
        }

        orders_user_model = data.get(position);
        holder.product_crop_name.setText(orders_user_model.getProduct_crop_name());
        holder.farmer_name.setText(orders_user_model.getFarmer_name());
        holder.salemaster_crop_order_date.setText(orders_user_model.getSalemaster_crop_order_date());
        holder.saledetail_crop_total_amt.setText(orders_user_model.getSaledetail_crop_total_amt());
        holder.saledetail_crop_qty.setText(orders_user_model.getSaledetail_crop_qty());
        holder.salemaster_crop_pay_type.setText(orders_user_model.getSalemaster_crop_pay_type());
//        holder.product_qty.setText(crop_buying_model.getProduct_qty());
//        holder.product_description.setText(crop_buying_model.getProduct_description());
//        holder.farmer_name.setText(crop_buying_model.getFarmer_name());
       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */



       /* Picasso.get()
                .load(crop_info.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img); */

        return row;
    }

    class Order_User_Holder {
        TextView salemaster_crop_cust_id,saledetail_crop_total_amt,saledetail_crop_qty,salemaster_crop_order_date,salemaster_crop_pay_type,farmer_name,product_crop_name,product_img;
    }
}