package com.example.agrotrade.Farmer.ShoppingCart;



import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agrotrade.R;


/**
 * Created by HP-pc on 12-Apr-17.
 */

public class Farmer_ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView name;
    public TextView storeid;

    public TextView shipping;
    public TextView about;

    //public ImageView deleteProduct;
    public Button deleteProduct;
    public ImageView editProduct;
    public ImageView myimagelogo;
    public Farmer_ProductViewHolder(View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.product_name);
        storeid = (TextView)itemView.findViewById(R.id.mystoreid);
        shipping = (TextView)itemView.findViewById(R.id.mywebsite);
        about = (TextView)itemView.findViewById(R.id.myabout);
        myimagelogo = (ImageView)itemView.findViewById(R.id.myimagelogo);
       // deleteProduct = (ImageView)itemView.findViewById(R.id.delete_product);
        deleteProduct = (Button) itemView.findViewById(R.id.remove);

        name.setOnClickListener(this);
        storeid.setOnClickListener(this);
        shipping.setOnClickListener(this);
        about.setOnClickListener(this);
        myimagelogo.setOnClickListener(this);
        deleteProduct.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Item Clicked:" + getItemId(), Toast.LENGTH_SHORT).show();


    }
}
