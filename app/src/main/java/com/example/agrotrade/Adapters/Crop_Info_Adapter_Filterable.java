package com.example.agrotrade.Adapters;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrotrade.All_Crop_Info;
import com.example.agrotrade.All_FertiPesti_Buying;
import com.example.agrotrade.Model.Crop_Info;
import com.example.agrotrade.Model.Ferti_Pesti_Buying;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Crop_Info_Adapter_Filterable extends
        BaseAdapter implements Filterable {


    private All_Crop_Info activity;
    private FriendFilter friendFilter;

    private ArrayList<Crop_Info> friendList;
    private ArrayList<Crop_Info> filteredList;






    public Crop_Info_Adapter_Filterable(All_Crop_Info activity, ArrayList<Crop_Info> friendList) {
        this.activity = activity;
        this.friendList = friendList;
        this.filteredList = friendList;

        getFilter();
    }
    @Override
    public int getCount() {
        return filteredList.size();

    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        final Crop_Info crop_info = (Crop_Info) getItem(position);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.crop_list, parent, false);
            holder = new ViewHolder();
            holder.name= (TextView) view.findViewById(R.id.textViewTitle);
            holder.img= (ImageView) view.findViewById(R.id.imageViewCrop);

            // holder.feeddes= (TextView) row.findViewById(R.id.textViewAddress);
            // holder.feedrating= (TextView) row.findViewById(R.id.textViewmob);
            holder.season=(TextView)view.findViewById(R.id.textViewSeason);
            holder.irrigation=(TextView)view.findViewById(R.id.textViewIrrigation);
            holder.varieties=(TextView)view.findViewById(R.id.textViewVarieties);
            holder.soil_type=(TextView)view.findViewById(R.id.textViewSoilType);
            holder.plant_material=(TextView)view.findViewById(R.id.textViewPlantMaterial);
            holder.spacing=(TextView)view.findViewById(R.id.textViewSpacing);
            holder.harvesting=(TextView)view.findViewById(R.id.textViewHarvest);

//            holder.iconText.setTypeface(typeface, Typeface.BOLD);
//            holder.iconText.setTextColor(activity.getResources().getColor(R.color.white));
//           // holder.name.setTypeface(typeface, Typeface.NORMAL);

            view.setTag(holder);
        } else {
            // get view holder back
            holder = (ViewHolder) view.getTag();
        }

        // bind text with view holder content view for efficient use



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

//
//        holder.crop_name.setText(ferti_pesti_buying.getCrop_name());
//        holder.name.setText(ferti_pesti_buying.getName());
//        holder.pest_name.setText(ferti_pesti_buying.getPest_name());
//        holder.direction.setText(ferti_pesti_buying.getDirection());
//        holder.price.setText(ferti_pesti_buying.getPrice());
//        holder.weight.setText(ferti_pesti_buying.getWeight());
//        holder.pro_info.setText(ferti_pesti_buying.getPro_info());
//        holder.description.setText(ferti_pesti_buying.getDescription());


        return view;
    }

    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new FriendFilter();
        }

        return friendFilter;
    }


    class ViewHolder {
        TextView name,feeddes,feedrating,season,irrigation,varieties,soil_type,plant_material,spacing,harvesting;
        ImageView img;
    }



    private class FriendFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Crop_Info> tempList = new ArrayList<Crop_Info>();

                // search content in friend list
                for (Crop_Info user : friendList) {
                    if (user.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(user);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = friendList.size();
                filterResults.values = friendList;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Crop_Info>) results.values;
            notifyDataSetChanged();
        }
    }
}
