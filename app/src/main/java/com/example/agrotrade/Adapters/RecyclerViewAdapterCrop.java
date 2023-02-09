package com.example.agrotrade.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.agrotrade.Model.Crop_Buying_Model;
import com.example.agrotrade.Model.Farmer_ProductModel;
import com.example.agrotrade.Model.User_ProductModel;
import com.example.agrotrade.Product_DetailShow_Activity;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.ProductCartActivity;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterCrop extends RecyclerView.Adapter<RecyclerViewAdapterCrop.DataViewHolder> {

    Context context;
    int myid;
    public SqliteDatabase mDatabase;
    //public SqliteDatabase mDatabase;
    ArrayList<Crop_Buying_Model> data;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String intid;

    Dialog builder;
    String crop_name1, qty1, price1, description1,cat_name1,product_id;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String RESID = "resid";

    public RecyclerViewAdapterCrop(Context context, ArrayList<Crop_Buying_Model> data) {

        this.context = context;
        this.data = data;

        mDatabase = new SqliteDatabase(context);

    }

    @NonNull
    @Override
    public RecyclerViewAdapterCrop.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crop_buying_list, parent, false);


        return new DataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapterCrop.DataViewHolder holder, final int position) {


          holder.product_id.setText(data.get(position).getProduct_id());
        holder.product_crop_name.setText(data.get(position).getProduct_crop_name());
        holder.product_qty.setText(data.get(position).getProduct_qty());

        // holder.description.setText(data.get(position).getQty());
        holder.product_price.setText(data.get(position).getProduct_price());
        // holder.dis.setText(data.get(position).getDes());

        holder.farmer_name.setText(data.get(position).getFarmer_name());
        holder.product_cat_name.setText(data.get(position).getProduct_cat_name());
        holder.product_description.setText(data.get(position).getProduct_description());
        Picasso.get()
                .load(data.get(position).getProduct_img())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.product_img);
//        holder.description.setText(data.get(position).getDesc());


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            holder.basic.setText(Html.fromHtml(data.get(position).getBasic(), Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            holder.basic.setText(Html.fromHtml(data.get(position).getBasic()));
//        }

//        holder.qty.setText(String.valueOf(myMainHomeActivity.ShopList.get(position).getNumber()));
//        Glide.with(context)
//                .load(data.get(position).getImg())
//                .into(holder.img);

//

//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String pro_id =data.get(position).getPro_id();
//                        String proname =data.get(position).getP_name();
//
//                        Toast.makeText(context, "" + pro_id, Toast.LENGTH_SHORT).show();
//
//                        if(holder.description.getVisibility() == View.VISIBLE){
//                            holder.description.setVisibility(View.GONE);
//                        }
//                        else {
//                            holder.description.setVisibility(View.VISIBLE);
//                        }
//
//
//
////                 final Dialog builder = new Dialog(context);
////
////                builder.setContentView(R.layout.my_layout_main);
////
////                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
////                Button button = (Button) builder.findViewById(R.id.adddata);
////
////                builder.show();
////
////                button.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        builder.dismiss();
////                    }
////                });
//
////
////                builder.setContentView(R.layout.my_layout_main);
////
////                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
////                Button button = (Button) builder.findViewById(R.id.adddata);
//
////               AlertDialog.Builder  builder=new AlertDialog.Builder(context);
////                builder.setTitle("hello");
////                builder.setMessage("hello");
////                builder.create();
////                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////                    }
////                });
////
////               /* builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////
////                    }
////                });*/
////               builder.show();
////
//                    }
//                });

        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (holder.qtyli1.getVisibility() == View.VISIBLE) {



                    String product_id = data.get(position).getProduct_id();
                    String product_img=data.get(position).getProduct_img();
                    String product_f_id = data.get(position).getProduct_f_id();
                    String farmer_name = data.get(position).getFarmer_name();
                    String product_cat_name = data.get(position).getProduct_cat_name();
                    String product_crop_name = data.get(position).getProduct_crop_name();
                    String product_qty = data.get(position).getProduct_qty();
                    String product_price = data.get(position).getProduct_price();
                    String product_description = data.get(position).getProduct_description();


                    String myqty = holder.qty.getText().toString();











                    int intqty= Integer.valueOf(myqty);
                    if(intqty==0){

                        Toast.makeText(context, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        Double d=Double.parseDouble(product_price);
                        int price1= Integer.valueOf(d.intValue());
                        int subtotal= price1 * Integer.valueOf(myqty);
                        String total= String.valueOf(subtotal);



//
//                    int c= mDatabase.getSelectedStore(product_crop_name);
//
//                    if(c==1){
//                        Toast.makeText(context, "Product is  Already In Cart!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else {

                        Toast.makeText(context, "Added To Cart!" , Toast.LENGTH_SHORT).show();






                        User_ProductModel newProduct = new User_ProductModel(product_id, product_crop_name, total,product_img, myqty);
                        mDatabase.addProduct(newProduct);



                        Intent intent = new Intent(context, ProductCartActivity.class);
                        context.startActivity(intent);





//                        Farmer_ProductModel newProduct = new Farmer_ProductModel(product_id, crop_name1, total,data, mystrqty);
//                                mDatabase.addProduct(newProduct);
//                                builder.dismiss();
//
//
//                                Intent intent = new Intent(context, Farmer_ProductCartActivity.class);
//                                context.startActivity(intent);


//                        Farmer_ProductModel newProduct = new Farmer_ProductModel(pro_id, pro_name, total, img, myqty);
//                        mDatabase.addProduct(newProduct);
                        //}

                    }










//                 builder= new Dialog(context);
//
//                builder.setContentView(R.layout.my_layout_main);
//
//                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
//                Button button = (Button) builder.findViewById(R.id.adddata);
//
//
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//
//                        int intqty = Integer.valueOf(text.getText().toString());
//                        if (intqty == 0) {
//
//                            Toast.makeText(context, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
//                            builder.dismiss();
//                        } else {
//
//
//                            int c = mDatabase.getSelectedStore(crop_name1);
//                            //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();
//
//                            if (c == 1) {
//                                Toast.makeText(context, "Already Added in Cart!", Toast.LENGTH_LONG).show();
//                                builder.dismiss();
//                            } else {
//
//
//                                String data = text.getText().toString();
//                                Double d = Double.parseDouble(price1);
//                                int myqty = Integer.valueOf(data);
//                                int myprice = Integer.valueOf(d.intValue());
//
//                                int all = myqty * myprice;
//
//
//                                String total = String.valueOf(all);
//
//                                String mystrqty= String.valueOf(myqty);
//
//
//                                //  Toast.makeText(CompareMonitorActivity.this, "" + all, Toast.LENGTH_SHORT).show();
//
//                                Toast.makeText(context, "Added To Cart!", Toast.LENGTH_SHORT).show();
//
//
//                                Farmer_ProductModel newProduct = new Farmer_ProductModel(product_id, crop_name1, total,data, mystrqty);
//                                mDatabase.addProduct(newProduct);
//                                builder.dismiss();
//
//
//                                Intent intent = new Intent(context, Farmer_ProductCartActivity.class);
//                                context.startActivity(intent);
//
//                            }
//                        }
//                    }
//                });
//
//                builder.show();

                //   @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Deleting This", Toast.LENGTH_SHORT).show();
//            }

//            final Dialog builder = new Dialog(context);
//
//                builder.setContentView(R.layout.my_layout_main);
//
//            final EditText text = (EditText) builder.findViewById(R.id.enterqty);
//            Button button = (Button) builder.findViewById(R.id.adddata);
//
//
//                button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                    int intqty = Integer.valueOf(text.getText().toString());
//                    if (intqty == 0) {
//
//                        Toast.makeText(context, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
//                        builder.dismiss();
//                    } else {
//
//
//                        int c = mDatabase.getSelectedStore(crop_name1);
//                        //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();
//
//                        if (c == 1) {
//                            Toast.makeText(context, "Already Added in Cart!", Toast.LENGTH_LONG).show();
//                            builder.dismiss();
//                        } else {
//
//
//                            String data = text.getText().toString();
//                            Double d = Double.parseDouble(price1);
//                            int myqty = Integer.valueOf(data);
//                            int myprice = Integer.valueOf(d.intValue());
//
//                            int all = myqty * myprice;
//
//
//                            String total = String.valueOf(all);
//
//                            String mystrqty= String.valueOf(myqty);
//
//
//                            //  Toast.makeText(CompareMonitorActivity.this, "" + all, Toast.LENGTH_SHORT).show();
//
//                            Toast.makeText(context, "Added To Cart!", Toast.LENGTH_SHORT).show();
//
//
//                            Farmer_ProductModel newProduct = new Farmer_ProductModel(product_id, crop_name1, total,data, mystrqty);
//                            mDatabase.addProduct(newProduct);
//                            builder.dismiss();
//
//
//                            Intent intent = new Intent(context, Farmer_ProductCartActivity.class);
//                            context.startActivity(intent);
//
//                        }
//                    }
//                }
//            });
//
//                builder.show();
//

                } else if(holder.qtyli1.getVisibility() == View.GONE) {
                    holder.qtyli1.setVisibility(View.VISIBLE);

                }

                else {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                }


            }

        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String product_id = data.get(position).getProduct_id();
                String product_img=data.get(position).getProduct_img();
                String product_f_id = data.get(position).getProduct_f_id();
                String farmer_name = data.get(position).getFarmer_name();
                String product_cat_name = data.get(position).getProduct_cat_name();
                String product_crop_name = data.get(position).getProduct_crop_name();
                String product_qty = data.get(position).getProduct_qty();
                String product_price = data.get(position).getProduct_price();
                String product_description = data.get(position).getProduct_description();
              //  Toast.makeText(context, "Id: " + pro_id, Toast.LENGTH_SHORT).show();




//                product_id=crop_buying.getProduct_id();
//                product_f_id=crop_buying.getProduct_f_id();
//                =crop_buying.getFarmer_name();
//                product_cat_name=crop_buying.getProduct_cat_name();
//                product_crop_name=crop_buying.getProduct_crop_name();
//
//                product_qty=crop_buying.getProduct_qty();
//                product_price=crop_buying.getProduct_price();
//                product_description=crop_buying.getProduct_description();

                Intent intent1 = new Intent(context, Product_DetailShow_Activity.class);
                intent1.putExtra("product_f_id",product_f_id);
                intent1.putExtra("product_id",product_id);
                intent1.putExtra("product_img",product_img);
                intent1.putExtra("product_cat_name",product_cat_name);
                intent1.putExtra("product_crop_name",product_crop_name);
                intent1.putExtra("product_qty",product_qty);
                intent1.putExtra("product_price",product_price);
                intent1.putExtra("farmer_name",farmer_name);
                intent1.putExtra("product_description",product_description);
                context.startActivity(intent1);

//                        String pro_id =data.get(position).getPro_id();
//                        String img =data.get(position).getImg();
//                        String price =data.get(position).getPrize();
//                        String myqty = holder.qty.getText().toString();
//                        //  String res_id= sharedpreferences.getString(RESID, "");
//
//
//                        int intqty= Integer.valueOf(myqty);
//                        if(intqty==0){
//
//                    Toast.makeText(context, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//
//                            Double d=Double.parseDouble(price);
//                    int price1= Integer.valueOf(d.intValue());
//                    int subtotal= price1 * Integer.valueOf(myqty);
//                    String total= String.valueOf(subtotal);
//
//
//
//
//                    int c= mDatabase.getSelectedStore(pro_name);
//
//                    if(c==1){
//                        Toast.makeText(context, "Product is  Already In Cart!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else {
//
//                        Toast.makeText(context, "Added To Cart!" , Toast.LENGTH_SHORT).show();
//
//
//                        Farmer_ProductModel newProduct = new Farmer_ProductModel(pro_id, pro_name, total, img, myqty);
//                        mDatabase.addProduct(newProduct);
//                    }

                //}


//                Intent intent = new Intent(context, VaccinationListActivity.class);
//                intent.putExtra("cid",s);
//                context.startActivity(intent);
            }
        });
//
//        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                String s =data.get(position).getPrice();
//                Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(context, DeleteChild.class);
////                intent.putExtra("cid",s);
////
////                context.startActivity(intent);
//                return true;
//            }
//        });
//
//
//        holder.plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        LinearLayout linearLayout,qtyli1;
        ImageView product_img;
        TextView product_id,product_cat_name,product_crop_name,product_qty,product_price,product_description,farmer_name,qty;




        Button add_to_cart;
        Button plus,minus;



        public DataViewHolder(View itemView) {
            super(itemView);






            product_id=(TextView)itemView.findViewById(R.id.product_id);
            product_img=(ImageView) itemView.findViewById(R.id.product_img);
            product_crop_name = (TextView) itemView.findViewById(R.id.crop_name);
            farmer_name = (TextView) itemView.findViewById(R.id.fname);
            product_price = (TextView) itemView.findViewById(R.id.price);
            product_qty = (TextView) itemView.findViewById(R.id.qty);
            product_description = (TextView) itemView.findViewById(R.id.description);
            product_cat_name = (TextView) itemView.findViewById(R.id.cat_name);
            product_qty = (TextView) itemView.findViewById(R.id.qty);
            add_to_cart=(Button)itemView.findViewById(R.id.add_to_cart);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.li1);
            qtyli1=(LinearLayout) itemView.findViewById(R.id.qtyli1);
            sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



            qty=(TextView)itemView.findViewById(R.id.integer_number);

            plus=(Button) itemView.findViewById(R.id.increase);
            minus=(Button) itemView.findViewById(R.id.decrease);


//
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);
//            detail.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.increase){

                int number = Integer.parseInt(qty.getText().toString()) + 1;
                qty.setText(String.valueOf(number));

            }

            else if(v.getId()==R.id.decrease)
            {
                int test= Integer.parseInt(qty.getText().toString());
                if(test==0)
                {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
                else {

                    int number = Integer.parseInt(qty.getText().toString()) - 1;
                    qty.setText(String.valueOf(number));

                }
            }




        }
    }
}
