package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrotrade.Model.Ferti_Pesti_Buying;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Ferti_Pesti_Buying_Adapter extends ArrayAdapter<Ferti_Pesti_Buying> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Ferti_Pesti_Buying> data = new ArrayList<Ferti_Pesti_Buying>();
    Ferti_Pesti_Buying ferti_pesti_buying;

    public Ferti_Pesti_Buying_Adapter(Activity activity, int layoutResourceId, ArrayList<Ferti_Pesti_Buying> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FertiPestiHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new FertiPestiHolder();
            holder.name = (TextView) row.findViewById(R.id.name);
            holder.crop_name = (TextView) row.findViewById(R.id.crop_name);
            holder.price = (TextView) row.findViewById(R.id.price);
            holder.weight = (TextView) row.findViewById(R.id.weight);
            holder.description = (TextView) row.findViewById(R.id.description);
            holder.pest_name = (TextView) row.findViewById(R.id.pest_name);
            holder.pro_info = (TextView) row.findViewById(R.id.pro_info);
            holder.direction = (TextView) row.findViewById(R.id.direction);
            holder.img= (ImageView) row.findViewById(R.id.img);

            row.setTag(holder);
        } else {
            holder = (Ferti_Pesti_Buying_Adapter.FertiPestiHolder) row.getTag();
        }

        ferti_pesti_buying = data.get(position);
        holder.crop_name.setText(ferti_pesti_buying.getCrop_name());
        holder.name.setText(ferti_pesti_buying.getName());
        holder.pest_name.setText(ferti_pesti_buying.getPest_name());
        holder.direction.setText(ferti_pesti_buying.getDirection());
        holder.price.setText(ferti_pesti_buying.getPrice());
        holder.weight.setText(ferti_pesti_buying.getWeight());
        holder.pro_info.setText(ferti_pesti_buying.getPro_info());
        holder.description.setText(ferti_pesti_buying.getDescription());

       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */



       Picasso.get()
                .load(ferti_pesti_buying.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img);

        return row;
    }

    class FertiPestiHolder {
        TextView name, price, weight, description, pro_info, crop_name, pest_name, direction;
        ImageView img;
    }
}