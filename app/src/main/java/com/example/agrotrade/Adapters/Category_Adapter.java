package com.example.agrotrade.Adapters;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.Html;

import com.example.agrotrade.Model.Categories;
import com.example.agrotrade.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Category_Adapter extends ArrayAdapter<Categories> {

    Activity activity;
    int layoutResourceId;
    ArrayList<Categories> data=new ArrayList<Categories>();
    Categories categories;
    // int numberOfColumns=2;

    public Category_Adapter(Activity activity, int layoutResourceId, ArrayList<Categories> data) {
        super(activity, layoutResourceId, data);
        this.activity=activity;
        this.layoutResourceId=layoutResourceId;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        CategoryHolder holder=null;
        if(row==null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new CategoryHolder();
            holder.name = (TextView) row.findViewById(R.id.textViewName);
            holder.id = (TextView) row.findViewById(R.id.textViewId);
            holder.img=(ImageView)row.findViewById(R.id.imageViewCat);
            row.setTag(holder);
        }
        else
        {
            holder= (CategoryHolder) row.getTag();
        }

        categories = data.get(position);
        holder.name.setText(categories.getName());
        holder.id.setText(categories.getId());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.id.setText(Html.fromHtml(categories.getId(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.id.setText(Html.fromHtml(categories.getId()));
        }

        Picasso.get()
                .load(categories.getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(100,100)
                .into(holder.img);
        return row;

        // .error(R.mipmap.ic_launcher)
    }
    class CategoryHolder
    {
        TextView name,id;
        ImageView img;
    }
}
