package com.example.agrotrade.Farmer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Crop_Info;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Farmer_PlaceOrder_Activity extends AppCompatActivity {

    Button change_address, continue_order;


    RadioGroup PaymentMethod;

    ProgressDialog pd;

    private SqliteDatabase mDatabase;

    String Urrl;

    RadioButton radioButtoncod,radioButtononline;

    TextView ordertotal,deliverycharges,total,cname,address,mobno,caddress;


    String amount,order,charges;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    int t=0,c=0,eq=0;

    String MainAdd;

    String Name1,name,add,det,updatedaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order_);

        mDatabase = new SqliteDatabase(this);


        PaymentMethod=(RadioGroup) findViewById(R.id.radgrp);
       // radioButtononline=(RadioButton)findViewById(R.id.online);

        ordertotal=(TextView)findViewById(R.id.order_total);
        deliverycharges=(TextView)findViewById(R.id.delivery_charges);
        total=(TextView)findViewById(R.id.total);
        cname=(TextView)findViewById(R.id.c_name);
        address=(TextView)findViewById(R.id.address);
        caddress=(TextView)findViewById(R.id.caddress);
        mobno=(TextView)findViewById(R.id.mob_no);

        change_address=(Button)findViewById(R.id.change_addr);
        continue_order=(Button)findViewById(R.id.continue_order);


        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");



        Intent intent=getIntent();
        amount=intent.getStringExtra("amount");
        order=intent.getStringExtra("order");
        updatedaddress=intent.getStringExtra("address");


        ordertotal.setText(amount);


        charges=deliverycharges.getText().toString();


        total();





            new AsyncUserData().execute("farmer", "id", Name1);


        add();













        change_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i1= new Intent(Farmer_PlaceOrder_Activity.this, Change_OrderDeliveryAddress_Farmer_Activity.class);

                i1.putExtra("amount",amount);
                i1.putExtra("order",order);
                startActivity(i1);
            }
        });

        continue_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = PaymentMethod.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButtoncod = (RadioButton) findViewById(selectedId);


                if(radioButtoncod.getText().equals("Cash on delivery"))
                {

                    if(address.getVisibility()==View.VISIBLE){
                      MainAdd=address.getText().toString();
                    }
                    else {
                        MainAdd=caddress.getText().toString();
                    }

                   new AsyncPlaceOrder().execute(order,Name1,MainAdd,"cod");




                    Toast.makeText(Farmer_PlaceOrder_Activity.this, "COD", Toast.LENGTH_SHORT).show();
                }

                else{


                    if(address.getVisibility()==View.VISIBLE){
                        MainAdd=address.getText().toString();
                    }
                    else {
                        MainAdd=caddress.getText().toString();
                    }

                    new AsyncPlaceOrderOnline().execute(order,Name1,MainAdd,"online");



                    Toast.makeText(Farmer_PlaceOrder_Activity.this, "Online", Toast.LENGTH_SHORT).show();


                }



            }
        });
    }


    public void total(){

        t=Integer.valueOf(amount);
        c=Integer.valueOf(charges);

        eq=t+c;

        total.setText(String.valueOf(eq));

    }


    public void add(){


        if (updatedaddress != null && !updatedaddress.isEmpty() && !updatedaddress.equals("null") || !address.getText().toString().isEmpty())

        {
            address.setVisibility(View.GONE);
            caddress.setVisibility(View.VISIBLE);
            caddress.setText(updatedaddress);
        }

//
//        if(address.getText().toString().matches("")||updatedaddress.)
//        {
//
//
//            // is null or empty
//        }else {
//
//            address.setVisibility(View.GONE);
//            caddress.setVisibility(View.VISIBLE);
//            caddress.setText(updatedaddress);
//            //not null or empty
//        }




    }


    class AsyncUserData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Farmer_PlaceOrder_Activity.this);
            pd.setMessage("Please Wait....");
            pd.setTitle("SnapBridge");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_dialog_wheel));
            pd.setCancelable(true);
            pd.setCanceledOnTouchOutside(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            //result = new HTTPRequestDAO().getMasterData(params[0]);
            result = new HTTPRequestDAO().getSingleData(params[0],params[1],params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {


                JSONObject obj = new JSONObject(result);
                Toast.makeText(Farmer_PlaceOrder_Activity.this,"" + result, Toast.LENGTH_SHORT).show();

                JSONArray jsonArray = obj.getJSONArray("response");

                for(int i=0;i<jsonArray.length();i++){

                    //Declaring a json object corresponding to every pdf object in our json Array
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Declaring a Pdf object to add it to the ArrayList  pdfList
                    Crop_Info crop_info  = new Crop_Info();
                    name = jsonObject.getString("name");
                   add = jsonObject.getString("address");
                    det= jsonObject.getString("mobileno");
                    // String rating = jsonObject.getString("address");

//                    String season = jsonObject.getString("season");
//                    String irrigation = jsonObject.getString("irrigation");
//                    String varieties = jsonObject.getString("varieties");
//                    String soil_type = jsonObject.getString("soil_type");
//                    String plant_material = jsonObject.getString("plant_material");
//                    String spacing = jsonObject.getString("spacing");
//                    String harvesting = jsonObject.getString("harvesting");

                    // pdf.setUsername(regname);
                    // pdf.setMobileno(details);
                    // pdf.setAddress(rating);
//                    crop_info.setName(name);
//                    crop_info.setImg(img);
//                    crop_info.setSeason(season);
//                    crop_info.setIrrigation(irrigation);
//                    crop_info.setVarieties(varieties);
//                    crop_info.setSoil_type(soil_type);
//                    crop_info.setPlant_material(plant_material);
//                    crop_info.setSpacing(spacing);
//                    crop_info.setHarvesting(harvesting);







                }

                 cname.setText(name);
                address.setText(add);
                mobno.setText(det);

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                // adapter = new UsersAdapter(AllUsersActivity.this, productList);






            } catch (JSONException e) {
                Toast.makeText(Farmer_PlaceOrder_Activity.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


                /*JSONObject obj = new JSONObject(result);
                JSONArray jsonArray = obj.getJSONArray("product");
                for(int i=0;i<jsonArray.length();i++){
                    //Declaring a json object corresponding to every pdf object in our json Array
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Declaring a Pdf object to add it to the ArrayList  pdfList
                    Farmer_ProductModel movie  = new Farmer_ProductModel();

                    String Mob = jsonObject.getString("mobname");
                    String Imgurl = jsonObject.getString("imageUrl");
                    String StoreName = jsonObject.getString("storeName");
                    String Producturl = jsonObject.getString("productUrl");
                    String currency1 = jsonObject.getString("currency");
                    String saleprice = jsonObject.getString("salePrice");


                    movie.setProductUrl(Producturl);
                    movie.setCurrency(currency1);
                    movie.setMobname(Mob);
                    movie.setImageUrl(Imgurl);
                    movie.setStoreName(StoreName);
                    movie.setSalePrice(saleprice);

                    ShopList.add(movie)*/;
        }
//                shopAdapter=new Farmer_ProductAdapter(InteSearchAllStoreActivity.this,R.layout.productlist, ShopList);
//                showproduct.setAdapter(shopAdapter);
//                shopAdapter.notifyDataSetChanged();


    }





    class AsyncPlaceOrder extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Farmer_PlaceOrder_Activity.this);
            pd.setMessage("Please Wait....");
            pd.setTitle("AgroTreds");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_dialog_wheel));
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            result = new HTTPRequestDAO().FertiPestiOrder(params[0], params[1],params[2],params[3]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {


//                    f_id=  Integer.toString(mdm.getF_id());

                    // Toast.makeText(Farmer_login.this, "Farmer id" + f_id, Toast.LENGTH_SHORT).show();


                    Toast.makeText(Farmer_PlaceOrder_Activity.this, "Order Placed", Toast.LENGTH_LONG).show();
//                   launchHomeScreen();


                   mDatabase.delete();

                    Intent i=new Intent(Farmer_PlaceOrder_Activity.this,All_OrderShow_Farmer_Activity.class);
                    startActivity(i);


//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//
//
//
//                    editor.putString(Name, f_id);
//                    editor.apply();
//                    launchHomeScreen();

                }

                else{
                    Toast.makeText(Farmer_PlaceOrder_Activity.this, "Inavild Credentials", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Farmer_PlaceOrder_Activity.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }

    class AsyncPlaceOrderOnline extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Farmer_PlaceOrder_Activity.this);
            pd.setMessage("Please Wait....");
            pd.setTitle("AgroTreds");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progress_dialog_wheel));
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            result = new HTTPRequestDAO().FertiPestiOrder(params[0], params[1],params[2],params[3]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    Urrl=mdm.getUrl();




                    final AlertDialog.Builder dialog = new AlertDialog.Builder(Farmer_PlaceOrder_Activity.this).setTitle("Payment").setMessage("Please Confirm Your Payment?");
                    dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            mDatabase.delete();

                            Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                            httpIntent.setData(Uri.parse(Urrl));

                            startActivity(httpIntent);

                        }
                    });
                    final AlertDialog alert = dialog.create();
                    alert.show();

// Hide after some seconds
                    final Handler handler  = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (alert.isShowing()) {
                                alert.dismiss();
                                Intent  intent=new Intent(Farmer_PlaceOrder_Activity.this, Farmer_login.class);
//                    intent.putExtra("url",Urrl);
                    startActivity(intent);

                            }
                        }
                    };

                    alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            handler.removeCallbacks(runnable);
                            Intent  intent=new Intent(Farmer_PlaceOrder_Activity.this, Farmer_login.class);
//                    intent.putExtra("url",Urrl);
                            startActivity(intent);
                        }
                    });

                    handler.postDelayed(runnable, 3000);





//                    f_id=  Integer.toString(mdm.getF_id());

                    // Toast.makeText(Farmer_login.this, "Farmer id" + f_id, Toast.LENGTH_SHORT).show();









//                    Intent  intent=new Intent(PlaceOrder_Activity.this, FinalPayment.class);
//                    intent.putExtra("url",Urrl);
//                    startActivity(intent);

                    Toast.makeText(Farmer_PlaceOrder_Activity.this, "Order Placed" + Urrl, Toast.LENGTH_LONG).show();
//                   launchHomeScreen();






//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//
//
//
//
//                    editor.putString(Name, f_id);
//                    editor.apply();
//                    launchHomeScreen();

                }

                else{
                    Toast.makeText(Farmer_PlaceOrder_Activity.this, "Inavild Credentials", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Farmer_PlaceOrder_Activity.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
