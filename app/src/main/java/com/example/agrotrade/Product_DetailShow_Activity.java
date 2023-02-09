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


import com.example.agrotrade.Model.User_ProductModel;
import com.example.agrotrade.User.ShoppingCart.ProductCartActivity;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;

public class Product_DetailShow_Activity extends AppCompatActivity {

    TextView crop_name, qty, price, description, f_name;
    ImageView img;
    Button add_to_cart;
    public SqliteDatabase mDatabase;

    String unit="Kg";
    String crop_name1, qty1, price1, description1,cat_name1,product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__detail_show_);

        crop_name = (TextView) findViewById(R.id.crop_name);
        qty = (TextView) findViewById(R.id.qty);
        price = (TextView) findViewById(R.id.price);
        description = (TextView) findViewById(R.id.description);
        f_name = (TextView) findViewById(R.id.f_name);
        img = (ImageView) findViewById(R.id.img);
        add_to_cart = (Button) findViewById(R.id.add_to_cart);

        mDatabase = new SqliteDatabase(Product_DetailShow_Activity.this);



        Intent intent = getIntent();

//        String crop_name=intent.getStringExtra("crop_name");
//        String qty=intent.getStringExtra("qty");
//        String price=intent.getStringExtra("price");
//        String description=intent.getStringExtra("description");
//        String f_name=intent.getStringExtra("fullname");

        Toast.makeText(this, "" + crop_name, Toast.LENGTH_SHORT).show();

        crop_name.setText(intent.getStringExtra("product_crop_name"));
        qty.setText(intent.getStringExtra("product_qty"));
        price.setText(intent.getStringExtra("product_price"));
        description.setText(intent.getStringExtra("product_description"));
        f_name.setText(intent.getStringExtra("farmer_name"));

        crop_name1=intent.getStringExtra("product_crop_name");
        price1=intent.getStringExtra("product_price");
        product_id=intent.getStringExtra("product_id");



        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                cat_name1=

                final Dialog builder = new Dialog(Product_DetailShow_Activity.this);

                builder.setContentView(R.layout.my_layout_main);

                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
                Button button = (Button) builder.findViewById(R.id.adddata);


                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        int intqty = Integer.valueOf(text.getText().toString());
                        if (intqty == 0) {

                            Toast.makeText(Product_DetailShow_Activity.this, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
                            builder.dismiss();
                        } else {


                            int c = mDatabase.getSelectedStore(crop_name1);
                            //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();

                            if (c == 1) {
                                Toast.makeText(Product_DetailShow_Activity.this, "Already Added in Cart!", Toast.LENGTH_LONG).show();
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

                                Toast.makeText(Product_DetailShow_Activity.this, "Added To Cart!", Toast.LENGTH_SHORT).show();


                                User_ProductModel newProduct = new User_ProductModel(product_id, crop_name1, total,data, mystrqty);
                                mDatabase.addProduct(newProduct);
                                builder.dismiss();


                              Intent intent = new Intent(Product_DetailShow_Activity.this, ProductCartActivity.class);
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

