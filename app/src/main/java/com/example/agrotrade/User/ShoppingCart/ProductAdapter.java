package com.example.agrotrade.User.ShoppingCart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrotrade.Model.Farmer_ProductModel;
import com.example.agrotrade.Model.User_ProductModel;
import com.example.agrotrade.R;


import java.util.List;

/**
 * Created by HP-pc on 12-Apr-17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private Context context;
    private List<User_ProductModel> listProducts;
    private SqliteDatabase mDatabase;
    public ProductAdapter(Context context, List<User_ProductModel> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
        mDatabase = new SqliteDatabase(context);
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_layout, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final User_ProductModel singleProduct = listProducts.get(position);
        holder.name.setText(singleProduct.getP_name());
        holder.storeid.setText(singleProduct.getQty());

        holder.about.setText(singleProduct.getPrize());


        Glide.with(context)
                .load(singleProduct.getImg())
                .into(holder.product_img);

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database
                mDatabase.deleteProduct(singleProduct.getId());
                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });


     /*   holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=singleProduct.getAbout();
                Toast.makeText(view.getContext(), "position = " + s, Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.product_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show();
              /*  String about=singleProduct.getAbout();
                String storeId=singleProduct.getStoreId();
                String logo=singleProduct.getLogo();
                String storeName=singleProduct.getStoreName();
                String cat_id="IN";

                Intent intent=new Intent(view.getContext(), MainActivity.class);

                intent.putExtra("id",storeId);
                intent.putExtra("name",storeName);
                intent.putExtra("url",logo);
                intent.putExtra("about",about);
                intent.putExtra("shipping",website);
                intent.putExtra("code",cat_id);

                context.startActivity(intent);

*/

            }
        });
    }
    @Override
    public int getItemCount() {
        return listProducts.size();
    }



}
