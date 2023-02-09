package com.example.agrotrade;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotrade.Farmer.ShoppingCart.Farmer_ProductCartActivity;
import com.example.agrotrade.Farmer.ShoppingCart.SqliteDatabase;
import com.example.agrotrade.Model.Farmer_ProductModel;

import com.squareup.picasso.Picasso;

public class FertiPesti_DetailShow_Activity extends AppCompatActivity {

    TextView textViewname,textViewprice,textViewunit,textViewweight,textViewdescription,textViewpro_info,textViewcrop_name,textViewpest_name,textViewdirection;
    ImageView imgViewimg;
    Button add_to_cart;
    public SqliteDatabase mDatabase;


    String fertpesti_name1, qty1, price1, description1,cat_name1,product_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferti_pesti__detail_show_);

        textViewname=(TextView)findViewById(R.id.name);
        textViewprice=(TextView)findViewById(R.id.price);
        textViewweight=(TextView)findViewById(R.id.weight);
        textViewdescription=(TextView)findViewById(R.id.description);
        textViewpro_info=(TextView)findViewById(R.id.pro_info);
        textViewcrop_name=(TextView)findViewById(R.id.crop_name);
        textViewpest_name=(TextView)findViewById(R.id.pest_name);
        textViewdirection=(TextView)findViewById(R.id.direction);
        textViewunit=(TextView)findViewById(R.id.unit);
        imgViewimg=(ImageView)findViewById(R.id.img);
        add_to_cart=(Button)findViewById(R.id.add_to_cart);
        Intent intent=getIntent();

        String name=intent.getStringExtra("name");
        String price=intent.getStringExtra("price");
        String weight=intent.getStringExtra("weight");
        String unit=intent.getStringExtra("unit");
        String description=intent.getStringExtra("description");

        Toast.makeText(this, "Name of Ferti/Pesti" + name, Toast.LENGTH_SHORT).show();
        String pro_info=intent.getStringExtra("pro_info");
        String crop_name=intent.getStringExtra("crop_name");
        String pest_name=intent.getStringExtra("pest_name");
        String direction=intent.getStringExtra("direction");
        String img=intent.getStringExtra("img");
        //textViewName.setText(a);
        //textViewCity.setText(c);
        //textViewMob.setText(b);

        textViewname.setText(name);
        textViewprice.setText(price);
        textViewunit.setText(unit);
        textViewweight.setText(weight);
        textViewdescription.setText(description);
        textViewpro_info.setText(pro_info);
        textViewcrop_name.setText(crop_name);
        textViewpest_name.setText(pest_name);
        textViewdirection.setText(direction);

        Picasso.get()
                .load(img)
                .placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(imgViewimg);

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                cat_name1=

                final Dialog builder = new Dialog(FertiPesti_DetailShow_Activity.this);

                builder.setContentView(R.layout.my_layout_main);

                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
                Button button = (Button) builder.findViewById(R.id.adddata);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        int intqty = Integer.valueOf(text.getText().toString());
                        if (intqty == 0) {

                            Toast.makeText(FertiPesti_DetailShow_Activity.this, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
                            builder.dismiss();
                        } else {


                            int c = mDatabase.getSelectedStore(fertpesti_name1);
                            //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();

                            if (c == 1) {
                                Toast.makeText(FertiPesti_DetailShow_Activity.this, "Already Added in Cart!", Toast.LENGTH_LONG).show();
                                builder.dismiss();
                            } else {


                                String data = text.getText().toString();
                                Double d = Double.parseDouble(price1);
                                int myqty = Integer.valueOf(data);
                                int myprice = Integer.valueOf(d.intValue());

                                int all = myqty * myprice;


                                String total = String.valueOf(all);

                                String mystrqty= String.valueOf(myqty);


                                //  Toast.makeText(CompareMonitorActivity.this, "" + all, Toast.LENGTH_SHORT).show();

                                Toast.makeText(FertiPesti_DetailShow_Activity.this, "Added To Cart!", Toast.LENGTH_SHORT).show();


                                Farmer_ProductModel newProduct = new Farmer_ProductModel(product_id, fertpesti_name1, total,data, mystrqty);
                                mDatabase.addProduct(newProduct);
                                builder.dismiss();


                                Intent intent = new Intent(FertiPesti_DetailShow_Activity.this, Farmer_ProductCartActivity.class);
                                startActivity(intent);

                            }
                        }
                    }
                });

                builder.show();
            }


        });

    }
}
