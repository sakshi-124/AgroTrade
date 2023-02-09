package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.agrotrade.Model.Crop_Selling;
import com.example.agrotrade.R;
import com.example.agrotrade.User.All_Crop_Buying_List;

import java.util.ArrayList;

public class Crop_Selling_Adapter extends ArrayAdapter<Crop_Selling> {

    //Added from online for edit button
    customButtonListener customListner;

    public interface customButtonListener {
        public void onButtonClickListner(int position,String value);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }
    //Ending

    private Context mcon;
    Activity activity;
    int layoutResourceId;
    ArrayList<Crop_Selling> data=new ArrayList<Crop_Selling>();
    Crop_Selling crop_selling;
    public Crop_Selling_Adapter(Activity activity, int layoutResourceId, ArrayList<Crop_Selling> data) {
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
            holder.crop_name= (TextView) row.findViewById(R.id.crop_name);
            // holder.feeddes= (TextView) row.findViewById(R.id.textViewAddress);
            // holder.feedrating= (TextView) row.findViewById(R.id.textViewmob);
            holder.qty=(TextView)row.findViewById(R.id.qty);
            holder.price=(TextView)row.findViewById(R.id.price);
            holder.edit=(ImageButton) row.findViewById(R.id.edit_product);

            row.setTag(holder);
        }
        else
        {
            holder= (Crop_Selling_Adapter.CropSellingHolder) row.getTag();
        }

        crop_selling = data.get(position);
        holder.crop_name.setText(crop_selling.getCrop_name());
       /*
        holder.feeddes.setText(pdf.getAddress());
        holder.feedrating.setText(pdf.getMobileno());
       */
        holder.qty.setText(crop_selling.getQty());
        holder.price.setText(crop_selling.getPrice());

        //For editing informtion

//        holder.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                if (customListner != null) {
////                    customListner.onButtonClickListner(position,temp);
////                }
//                mcon.startActivity(new Intent(mcon, All_Crop_Buying_List.class));
//            }
//        });

       /* Picasso.get()
                .load(crop_info.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img); */

        return row;
    }

    class CropSellingHolder{
        TextView crop_name,qty,price;
        ImageButton edit;
    }
}
