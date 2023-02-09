package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrotrade.Model.Crop_Info;
import com.example.agrotrade.Model.Crop_Info;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Crop_Info_Adapter extends ArrayAdapter<Crop_Info> {
    Activity activity;
    int layoutResourceId;
    ArrayList<Crop_Info> data=new ArrayList<Crop_Info>();
    Crop_Info crop_info;

    public Crop_Info_Adapter(Activity activity, int layoutResourceId, ArrayList<Crop_Info> data) {
        super(activity, layoutResourceId, data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        CropInfoHolder holder=null;
        if(row==null)
        {
            LayoutInflater inflater= LayoutInflater.from(activity);
            row=inflater.inflate(layoutResourceId,parent,false);
            holder=new CropInfoHolder();
            holder.name= (TextView) row.findViewById(R.id.textViewTitle);
            holder.img= (ImageView) row.findViewById(R.id.imageViewCrop);

            // holder.feeddes= (TextView) row.findViewById(R.id.textViewAddress);
            // holder.feedrating= (TextView) row.findViewById(R.id.textViewmob);
            holder.season=(TextView)row.findViewById(R.id.textViewSeason);
            holder.irrigation=(TextView)row.findViewById(R.id.textViewIrrigation);
            holder.varieties=(TextView)row.findViewById(R.id.textViewVarieties);
            holder.soil_type=(TextView)row.findViewById(R.id.textViewSoilType);
            holder.plant_material=(TextView)row.findViewById(R.id.textViewPlantMaterial);
            holder.spacing=(TextView)row.findViewById(R.id.textViewSpacing);
            holder.harvesting=(TextView)row.findViewById(R.id.textViewHarvest);


            row.setTag(holder);
        }
        else
        {
            holder= (CropInfoHolder) row.getTag();
        }

        crop_info = data.get(position);
        holder.name.setText(crop_info.getName());
       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */
        holder.season.setText(crop_info.getSeason());
        holder.irrigation.setText(crop_info.getIrrigation());
        holder.varieties.setText(crop_info.getVarieties());
        holder.soil_type.setText(crop_info.getSoil_type());
        holder.plant_material.setText(crop_info.getPlant_material());
        holder.spacing.setText(crop_info.getSpacing());
        holder.harvesting.setText(crop_info.getHarvesting());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.varieties.setText(Html.fromHtml(crop_info.getVarieties(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.varieties.setText(Html.fromHtml(crop_info.getVarieties()));
        }
        Picasso.get()
                .load(crop_info.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img);

        return row;
    }


    class CropInfoHolder
    {
        TextView name,feeddes,feedrating,season,irrigation,varieties,soil_type,plant_material,spacing,harvesting;
        ImageView img;
    }
}
