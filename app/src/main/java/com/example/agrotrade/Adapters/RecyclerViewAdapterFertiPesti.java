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

import com.example.agrotrade.Farmer.ShoppingCart.Farmer_ProductCartActivity;
import com.example.agrotrade.Farmer.ShoppingCart.SqliteDatabase;
import com.example.agrotrade.FertiPesti_DetailShow_Activity;
import com.example.agrotrade.Model.Farmer_ProductModel;
import com.example.agrotrade.Model.Ferti_Pesti_Buying;
import com.example.agrotrade.R;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterFertiPesti extends RecyclerView.Adapter<RecyclerViewAdapterFertiPesti.DataViewHolder>{


    Context context;
    int myid;
    public SqliteDatabase mDatabase;
    //public SqliteDatabase mDatabase;
    ArrayList<Ferti_Pesti_Buying> data;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String intid;

    Dialog builder;
    String crop_name1, qty1, price1, description1,cat_name1,product_id;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String RESID = "resid";

    public RecyclerViewAdapterFertiPesti(Context context, ArrayList<Ferti_Pesti_Buying> data) {

        this.context = context;
        this.data = data;

        mDatabase = new SqliteDatabase(context);

    }

    @NonNull
    @Override
    public RecyclerViewAdapterFertiPesti.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.ferti_pesti_buying_list, parent, false);


        return new DataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapterFertiPesti.DataViewHolder holder, final int position) {


        holder.id.setText(data.get(position).getId());
        holder.name.setText(data.get(position).getName());
        holder.weight.setText(data.get(position).getWeight());

        // holder.description.setText(data.get(position).getQty());
        holder.price.setText(data.get(position).getPrice());
        // holder.dis.setText(data.get(position).getDes());

        holder.pro_info.setText(data.get(position).getPro_info());
        holder.pest_name.setText(data.get(position).getPest_name());
        holder.description.setText(data.get(position).getDescription());
        holder.direction.setText(data.get(position).getDirection());
        holder.crop_name.setText(data.get(position).getCrop_name());
        holder.unit.setText(data.get(position).getUnit());
        Picasso.get()
                .load(data.get(position).getImg())
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(holder.img);
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



                    String id = data.get(position).getId();
                    String name = data.get(position).getName();
                    String direction = data.get(position).getDirection();
                    String weight = data.get(position).getWeight();
                    String pro_info = data.get(position).getPro_info();
                    String pest_name = data.get(position).getPest_name();
                    String price = data.get(position).getPrice();
                    String description = data.get(position).getDescription();
                    String img=data.get(position).getImg();
                    String crop_name=data.get(position).getCrop_name();
                    String unit=data.get(position).getUnit();


                    String myqty = holder.qty.getText().toString();











                    int intqty= Integer.valueOf(myqty);
                    if(intqty==0){

                        Toast.makeText(context, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        Double d=Double.parseDouble(price);
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






                        Ferti_Pesti_Buying newProduct = new Ferti_Pesti_Buying(id, name, total,img, myqty);
                        mDatabase.addProductFerti(newProduct);



                        Intent intent = new Intent(context, Farmer_ProductCartActivity.class);
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



                String id = data.get(position).getId();
                String name = data.get(position).getName();
                String direction = data.get(position).getDirection();
                String weight = data.get(position).getWeight();
                String pro_info = data.get(position).getPro_info();
                String pest_name = data.get(position).getPest_name();
                String price = data.get(position).getPrice();
                String description = data.get(position).getDescription();
                String img=data.get(position).getImg();
                String crop_name=data.get(position).getCrop_name();
                String unit=data.get(position).getUnit();
                //  Toast.makeText(context, "Id: " + pro_id, Toast.LENGTH_SHORT).show();



                Intent intent1 = new Intent(context, FertiPesti_DetailShow_Activity.class);
                intent1.putExtra("id",id);
                intent1.putExtra("name",name);
                intent1.putExtra("direction",direction);
                intent1.putExtra("weight",weight);
                intent1.putExtra("pro_info",pro_info);
                intent1.putExtra("price",price);
                intent1.putExtra("description",description);
                intent1.putExtra("pest_name",pest_name);
                intent1.putExtra("crop_name",crop_name);
                intent1.putExtra("unit",unit);
                intent1.putExtra("img",img);
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
        ImageView img;
        TextView id,name, price, unit,qty, weight, description, pro_info, crop_name, pest_name, direction;




        Button add_to_cart;
        Button plus,minus;



        public DataViewHolder(View itemView) {
            super(itemView);






            id=(TextView)itemView.findViewById(R.id.fp_id);
            name = (TextView) itemView.findViewById(R.id.name);
            pro_info = (TextView) itemView.findViewById(R.id.pro_info);
            crop_name = (TextView) itemView.findViewById(R.id.crop_name);
            pest_name = (TextView) itemView.findViewById(R.id.pest_name);
            price = (TextView) itemView.findViewById(R.id.price);
            qty = (TextView) itemView.findViewById(R.id.qty);
            description = (TextView) itemView.findViewById(R.id.description);
            direction = (TextView) itemView.findViewById(R.id.direction);
            weight = (TextView) itemView.findViewById(R.id.weight);
            unit = (TextView) itemView.findViewById(R.id.unit);
            img=(ImageView)itemView.findViewById(R.id.img);
            add_to_cart=(Button)itemView.findViewById(R.id.add_to_cart);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.lin2);
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
