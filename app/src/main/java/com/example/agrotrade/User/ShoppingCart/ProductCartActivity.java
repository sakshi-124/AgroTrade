package com.example.agrotrade.User.ShoppingCart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.agrotrade.Model.Farmer_ProductModel;
import com.example.agrotrade.Model.User_ProductModel;
import com.example.agrotrade.PlaceOrder_Activity;
import com.example.agrotrade.R;
import com.example.agrotrade.User.All_Crop_Buying_List;
import com.example.agrotrade.User.User_PlaceOrder_Activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductCartActivity extends AppCompatActivity implements AdapterView.OnClickListener{

    /*private static final String TAG = MainActivity.class.getSimpleName();*/
    private SqliteDatabase mDatabase;
    ProductAdapter mAdapter;
    Double amount;
    Button place_order;
    int mytotal;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKeyUser";
    SharedPreferences sharedpreferences;

    String Name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart_user);
        FrameLayout fLayout = (FrameLayout) findViewById(R.id.activity_main1);
        RecyclerView productView = (RecyclerView)findViewById(R.id.product_list);
        TextView mytext = (TextView) findViewById(R.id.txt1);
         place_order=(Button)findViewById(R.id.place_order);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        productView.setLayoutManager(linearLayoutManager);
        productView.setHasFixedSize(true);
        mDatabase = new SqliteDatabase(this);


        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");

        final List<User_ProductModel> allProducts = mDatabase.listProducts();
        if(allProducts.size() > 0){
            mytext.setVisibility(View.INVISIBLE);
            productView.setVisibility(View.VISIBLE);
            place_order.setVisibility(View.VISIBLE);
          mAdapter = new ProductAdapter(this, allProducts);
            productView.setAdapter(mAdapter);
        }else {
            productView.setVisibility(View.GONE);
            place_order.setVisibility(View.GONE);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton order = (FloatingActionButton) findViewById(R.id.order);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new quick task
                Intent intent=new Intent(ProductCartActivity.this, All_Crop_Buying_List.class);
                startActivity(intent);

            }
        });

//        place_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(Farmer_ProductCartActivity.this, PlaceOrder_Activity.class);
//                startActivity(i);
//            }
//        });

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(Farmer_ProductCartActivity.this, "Hello" + allProducts, Toast.LENGTH_LONG).show();
                JSONObject obj = null;

                JSONObject finalobject = new JSONObject();
                JSONArray jsonArray = new JSONArray();

                int amount1=0;
                int subtotal=0;

                for (User_ProductModel value : allProducts)
                {
                    //Toast.makeText(Farmer_ProductCartActivity.this, "" +value.getPro_name(), Toast.LENGTH_SHORT).show();
                   // Toast.makeText(Farmer_ProductCartActivity.this, "" +value.getPrice(), Toast.LENGTH_SHORT).show();





                        for (int i = 0; i < allProducts.size(); i++) {
                            obj = new JSONObject();



                            try {

                               subtotal=   Integer.valueOf(value.getPrize());


                                obj.put("price", value.getPrize());
                                //obj.put("name", value.getP_name());
                                obj.put("qty", value.getQty());

                                obj.put("pro_id", value.getPro_id());
                                obj.put("product_name", value.getP_name());
                                obj.put("user_id", Name1);






//                                obj.put("res_id", value.get());
//                                obj.put("qty", value.getQty());
                                //amount+=Integer.parseInt(value.getPrize());\


                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                Toast.makeText(ProductCartActivity.this, "" + e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    amount1=amount1 + subtotal;

                    jsonArray.put(obj);





//                        String url = "http://www.test.com?response="+finalobject.toString();










                }

                try {
                    finalobject.put("order", jsonArray);


                   Toast.makeText(ProductCartActivity.this, "success" + String.valueOf(amount1), Toast.LENGTH_SHORT).show();
                   //Toast.makeText(Farmer_ProductCartActivity.this, "success" + String.valueOf(amount1), Toast.LENGTH_SHORT).show();
                   //Toast.makeText(Farmer_ProductCartActivity.this, "success" + finalobject.toString(), Toast.LENGTH_SHORT).show();


                    Log.d("response",finalobject.toString());
                    Intent i = new Intent(ProductCartActivity.this, User_PlaceOrder_Activity.class);
                  i.putExtra("amount",String.valueOf(amount1));
                   i.putExtra("order",finalobject.toString());
                    startActivity(i);
                    //Toast.makeText(Farmer_ProductCartActivity.this, "" + finalobject.toString(), Toast.LENGTH_SHORT).show();




                }catch (JSONException e)
                {
                    Toast.makeText(ProductCartActivity.this, "" + e, Toast.LENGTH_SHORT).show();
                }
                // add new quick task
//                Intent intent=new Intent(Farmer_ProductCartActivity.this,MyHomeActivity.class);
//                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View view) {

    }
}
