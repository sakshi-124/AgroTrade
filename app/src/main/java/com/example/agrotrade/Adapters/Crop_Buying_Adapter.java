package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.agrotrade.Model.Crop_Buying_Model;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Crop_Buying_Adapter  extends ArrayAdapter<Crop_Buying_Model> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Crop_Buying_Model> data=new ArrayList<Crop_Buying_Model>();
    Crop_Buying_Model crop_buying_model;
    public Crop_Buying_Adapter(Activity activity, int layoutResourceId, ArrayList<Crop_Buying_Model> data) {
        super(activity, layoutResourceId, data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        CropSellingHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater= LayoutInflater.from(activity);
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new CropSellingHolder();
            holder.product_crop_name= (TextView) row.findViewById(R.id.crop_name);
            // holder.feeddes= (TextView) row.findViewById(R.id.textViewAddress);
            // holder.feedrating= (TextView) row.findViewById(R.id.textViewmob);
            holder.product_cat_name=(TextView)row.findViewById(R.id.cat_name);
            holder.product_id=(TextView)row.findViewById(R.id.product_id);
            holder.product_price=(TextView)row.findViewById(R.id.price);
            holder.product_qty=(TextView)row.findViewById(R.id.qty);
            holder.product_img=(ImageView)row.findViewById(R.id.product_img);
            holder.product_description=(TextView)row.findViewById(R.id.description);
            holder.farmer_name=(TextView)row.findViewById(R.id.fname);


            row.setTag(holder);
        }
        else
        {
            holder= (Crop_Buying_Adapter.CropSellingHolder) row.getTag();
        }

        crop_buying_model = data.get(position);
        holder.product_crop_name.setText(crop_buying_model.getProduct_crop_name());
        holder.product_id.setText(crop_buying_model.getProduct_id());
        holder.product_cat_name.setText(crop_buying_model.getProduct_cat_name());
        holder.product_price.setText(crop_buying_model.getProduct_price());
        holder.product_qty.setText(crop_buying_model.getProduct_qty());
        holder.product_description.setText(crop_buying_model.getProduct_description());
        holder.farmer_name.setText(crop_buying_model.getFarmer_name());

       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */



        Picasso.get()
                .load(crop_buying_model.getProduct_img())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.product_img);

        return row;
    }

    class CropSellingHolder{
        ImageView product_img;
        TextView product_id,product_cat_name,product_crop_name,product_qty,product_price,product_description,farmer_name;
    }
}
