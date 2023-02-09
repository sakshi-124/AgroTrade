package com.example.agrotrade.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agrotrade.DeleteProductActivity;
import com.example.agrotrade.Farmer.Crop_Details_Update_Form;
import com.example.agrotrade.Farmer.ExpenseManager;
import com.example.agrotrade.Model.Crop_Selling;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;


import java.util.ArrayList;

public class RecyclerViewAdapterProduct extends RecyclerView.Adapter<RecyclerViewAdapterProduct.DataViewHolder> {

    Context context;
    int myid;
    //public SqliteDatabase mDatabase;
    ArrayList<Crop_Selling> data;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    String intid;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String RESID = "resid";

    public RecyclerViewAdapterProduct(Context context, ArrayList<Crop_Selling> data) {

        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterProduct.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crop_selling_list,parent,false);


        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapterProduct.DataViewHolder holder, final int position) {







     //  holder.pro_id.setText(data.get(position).getId());
        holder.pro_name.setText(data.get(position).getCrop_name());
       // holder.description.setText(data.get(position).getQty());
        holder.price.setText(data.get(position).getPrice());
       // holder.dis.setText(data.get(position).getDes());
       holder.qty.setText(data.get(position).getQty());
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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Deleting This", Toast.LENGTH_SHORT).show();

                String pro_id=data.get(position).getId();

                Intent  intent=new Intent(context, DeleteProductActivity.class);
                intent.putExtra("id",pro_id);
                context.startActivity(intent);




                //trying for deleteing item

               // new AsyncCropSelling().execute("product","id",intid);

//                if(holder.basic.getVisibility() == View.VISIBLE)
//                {
//                    holder.detail.setText("Show Detail");
//                    holder.basic.setVisibility(View.GONE);
//                }
//                else
//                {
//                    holder.detail.setText("Hide Detail");
//                    holder.basic.setVisibility(View.VISIBLE);
//                }


            }
        });


//
//        holder.expense.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Deleting This", Toast.LENGTH_SHORT).show();
//
//                String pro_id=data.get(position).getId();
//
//                Intent  intent=new Intent(context, ExpenseManager.class);
//                intent.putExtra("id",pro_id);
//                context.startActivity(intent);
//
//
//            }
//        });
//
//                //trying for deleteing item
//
//                // new AsyncCropSelling().execute("product","id",intid);
//
////                if(holder.basic.getVisibility() == View.VISIBLE)
////                {
////                    holder.detail.setText("Show Detail");
////                    holder.basic.setVisibility(View.GONE);
////                }
////                else
////                {
////                    holder.detail.setText("Hide Detail");
////                    holder.basic.setVisibility(View.VISIBLE);
////                }
//
//


        holder.manage_expense.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Manage Expense", Toast.LENGTH_SHORT).show();

                String pro_id=data.get(position).getId();

                Intent  intent=new Intent(context, ExpenseManager.class);
                intent.putExtra("id",pro_id);
                context.startActivity(intent);
            }

        });


                holder.detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String pro_id=data.get(position).getId();
                       String pro_name =data.get(position).getCrop_name();
                       String pro_qty=data.get(position).getQty();
                       String pro_price=data.get(position).getPrice();
                       String pro_desc=data.get(position).getDesc();
                       Toast.makeText(context, "Id: " + pro_id, Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(context, Crop_Details_Update_Form.class);
                       intent.putExtra("id",pro_id);
//                       intent.putExtra("pro_name",pro_name);
                        intent.putExtra("name",pro_name);
                       intent.putExtra("qty",pro_qty);
                       intent.putExtra("price",pro_price);
                       intent.putExtra("description",pro_desc);
                context.startActivity(intent);

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

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName,txtEnroll;
        LinearLayout linearLayout;

        TextView pro_id;
        TextView pro_name;
        TextView price;
        TextView description,basic;
        TextView res_id;
        TextView dis;
        TextView actualprize;

        Button plus,minus;
        TextView qty;

        TextView sub_cat_id;
        ImageButton imageButton;


        ImageView img;
        Button manage_expense;
        ImageButton detail,delete;

        public DataViewHolder(View itemView) {
            super(itemView);

            //mDatabase = new SqliteDatabase(context);

//
//
//
//            holder.crop_name= (TextView) row.findViewById(R.id.crop_name);
//            // holder.feeddes= (TextView) row.findViewById(R.id.textViewAddress);
//            // holder.feedrating= (TextView) row.findViewById(R.id.textViewmob);
//            holder.qty=(TextView)row.findViewById(R.id.qty);
//            holder.price=(TextView)row.findViewById(R.id.price);
//            holder.edit=(ImageButton) row.findViewById(R.id.edit_product);
//
//
//


            //img=(ImageView)itemView.findViewById(R.id.img);
//            pro_id=(TextView)itemView.findViewById(R.id.pro_id);
            //res_id=(TextView)itemView.findViewById(R.id.res_id1);
            /*holder.sub_cat_id=(TextView)itemView.findViewById(R.id.sub_cat_id);*/
            pro_name=(TextView)itemView.findViewById(R.id.crop_name);
            pro_id=(TextView)itemView.findViewById(R.id.product_id);
            price=(TextView)itemView.findViewById(R.id.price);
            qty=(TextView)itemView.findViewById(R.id.qty);
//            dis=(TextView)itemView.findViewById(R.id.dis);
//            actualprize=(TextView)itemView.findViewById(R.id.actualprize);
//            basic=(TextView)itemView.findViewById(R.id.basic);
          detail=(ImageButton) itemView.findViewById(R.id.edit_product);
          delete=(ImageButton) itemView.findViewById(R.id.delete_product);
        //  expense=(ImageButton) itemView.findViewById(R.id.add_expense);
          manage_expense=(Button)itemView.findViewById(R.id.manage_expense);
//        holder.plus=(Button) itemView.findViewById(R.id.increase);
//        holder.minus=(Button) itemView.findViewById(R.id.decrease);
            sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//
//
//            plus=(Button) itemView.findViewById(R.id.increase);
//            minus=(Button) itemView.findViewById(R.id.decrease);
//            imageButton=(ImageButton) itemView.findViewById(R.id.myaddtocartbtn);

//
//            plus.setOnClickListener(this);
//            minus.setOnClickListener(this);
//            detail.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

        }

//        @Override
//        public void onClick(View v) {
//            if(v.getId() == R.id.increase){
//
//                int number = Integer.parseInt(qty.getText().toString()) + 1;
//                qty.setText(String.valueOf(number));
//
//            }
//
//            else if(v.getId()==R.id.decrease)
//            {
//                int test= Integer.parseInt(qty.getText().toString());
//                if(test==0)
//                {
//                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//                }
//                else {
//
//                    int number = Integer.parseInt(qty.getText().toString()) - 1;
//                    qty.setText(String.valueOf(number));
//
//                }
//            }
//
//
//
//
//        }
    }



}
