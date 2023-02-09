package com.example.agrotrade.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Orders_User_Adapter;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Orders_User_Model;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class All_OrdersShow_Activity extends AppCompatActivity {
    TextView mytext;
    ListView listView;
    ArrayList<Orders_User_Model> orderList;
    Orders_User_Adapter adapter;
    private SqliteDatabase mDatabase;
    ProgressDialog pd;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKeyUser";
    SharedPreferences sharedpreferences;

    String Name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__orders_show_);
        orderList=new ArrayList<>();
         mytext = (TextView) findViewById(R.id.txt2);
        listView= (ListView) findViewById(R.id.order_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        orderView.setLayoutManager(linearLayoutManager);
//        orderView.setHasFixedSize(true);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");
        mDatabase = new SqliteDatabase(All_OrdersShow_Activity.this);
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Orders_User_Model crop_buying = (Orders_User_Model) adapterView.getItemAtPosition(i);
//
//                 String salemaster_crop_cust_id,salemaster_crop_total_amt,salemaster_crop_qty,salemaster_crop_order_date;
//
//                String farmer_id,product_id,product_f_id,product_cat_name,product_crop_name,product_qty,product_price,product_description,farmer_name;
////                cat_name = crop_selling.getCat_name();
////                crop_name = crop_selling.getCrop_name();
////                qty = crop_selling.getQty();
////                price = crop_selling.getPrice();
////                description = crop_selling.getDesc();
//
//                salemaster_crop_cust_id=crop_buying.getSalemaster_crop_cust_id();
//                salemaster_crop_order_date=crop_buying.getSalemaster_crop_order_date();
//                salemaster_crop_qty=crop_buying.getSalemaster_crop_qty();
//                salemaster_crop_total_amt=crop_buying.getSalemaster_crop_total_amt();
//
//
//
//                Toast.makeText(All_OrdersShow_Activity.this, "Ordered DATE: " + salemaster_crop_order_date, Toast.LENGTH_SHORT).show();
//
//
//                Toast.makeText(All_OrdersShow_Activity.this, "clicked" + salemaster_crop_cust_id, Toast.LENGTH_SHORT).show();
////
////
////
//////                final Dialog builder = new Dialog(All_Crop_Buying_List .this);
//////
//////                builder.setContentView(R.layout.my_layout_main);
//////
//////                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
//////                Button button = (Button) builder.findViewById(R.id.adddata);
//////
//////
//////                button.setOnClickListener(new View.OnClickListener() {
//////                    @Override
//////                    public void onClick(View view) {
//////
//////
//////                        int intqty = Integer.valueOf(text.getText().toString());
//////                        if (intqty == 0) {
//////
//////                            Toast.makeText(All_Crop_Buying_List.this, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
//////                            builder.dismiss();
//////                        } else {
//////
//////
//////                            int c = mDatabase.getSelectedStore(crop_name);
//////                            //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();
//////
//////                            if (c == 1) {
//////                                Toast.makeText(All_Crop_Buying_List.this, "Already Added in Cart!", Toast.LENGTH_LONG).show();
//////                                builder.dismiss();
//////                            } else {
//////
//////
//////                                String data = text.getText().toString();
//////                                Double d = Double.parseDouble(price);
//////                                int myqty = Integer.valueOf(data);
//////                                int myprice = Integer.valueOf(d.intValue());
//////
//////                                int all = myqty * myprice;
//////
//////
//////                                String total = String.valueOf(all);
//////
//////
//////                                //  Toast.makeText(CompareMonitorActivity.this, "" + all, Toast.LENGTH_SHORT).show();
//////
//////                                Toast.makeText(All_Crop_Buying_List.this, "Added To Cart!", Toast.LENGTH_SHORT).show();
//////
//////
//////                                Farmer_ProductModel newProduct = new Farmer_ProductModel(cat_name, crop_name, total, description, data);
//////                                mDatabase.addProduct(newProduct);
//////                                builder.dismiss();
//////
//////
////////                              Intent intent = new Intent(CompareMonitorActivity.this, Farmer_ProductCartActivity.class);
////////                              startActivity(intent);
////////
//////                            }
//////                        }
//////                    }
//////                });
//////
//////                builder.show();
//           }
////
////
////
////
////
////
//        });

       // new AsyncAllOrderListing.execute("customer","salemaster_crop","id","cust_id");

        new  AsyncAllOrderListing().execute("cust_id",Name1);
    }




    class AsyncAllOrderListing extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_OrdersShow_Activity.this);
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
           // result = new HTTPRequestDAO().getMasterData(params[0]);
            result = new HTTPRequestDAO().getJoinData_cond(params[0],params[1]);
            // result = new HTTPRequestDAO().getSingleData(params[0], params[1], params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();

            Toast.makeText(All_OrdersShow_Activity.this, "" + result, Toast.LENGTH_SHORT).show();


            try {

//                if (result.isEmpty()) {
//
//                    Toast.makeText(All_OrdersShow_Activity.this, "Add New product !", Toast.LENGTH_SHORT).show();
//
//                } else {
                    listView.setVisibility(View.VISIBLE);
                    mytext.setVisibility(View.GONE);

                    JSONObject obj = new JSONObject(result);

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                        Orders_User_Model orders = new Orders_User_Model();


//                        String cat_name = jsonObject.getString("cat_name");
//                        String crop_name = jsonObject.getString("crop_name");
//                        String qty = jsonObject.getString("qty");
//                        String price = jsonObject.getString("price");
//                        String description = jsonObject.getString("description");
                        //String f_name = jsonObject.getString("fullname");
                        String farmer_name = jsonObject.getString("farmer_name");
                        String product_crop_name = jsonObject.getString("product_crop_name");
                      //  String product_img = jsonObject.getString("product_img");
                      //  String salemaster_crop_cust_id = jsonObject.getString("salemaster_crop_cust_id");
                        String saledetail_crop_total_amt = jsonObject.getString("saledetail_crop_total_amt");
                        String saledetail_crop_qty = jsonObject.getString("saledetail_crop_qty");
                        String salemaster_crop_order_date = jsonObject.getString("salemaster_crop_order_date");
                        String salemaster_crop_pay_type = jsonObject.getString("salemaster_crop_pay_type");
//                    String product_price=jsonObject.getString("product_price");
//                    String product_description=jsonObject.getString("product_description");
//                    String farmer_name=jsonObject.getString("farmer_name");


                        orders.setProduct_crop_name(product_crop_name);
                        orders.setFarmer_name(farmer_name);
                      //  orders.setProduct_img(product_img);
                     //   orders.setSalemaster_crop_cust_id(salemaster_crop_cust_id);
                        orders.setSaledetail_crop_total_amt(saledetail_crop_total_amt);
                        orders.setSaledetail_crop_qty(saledetail_crop_qty);
                        orders.setSalemaster_crop_order_date(salemaster_crop_order_date);
                        orders.setSalemaster_crop_pay_type(salemaster_crop_pay_type);
                        // crop_selling.setFullname(f_name);
                        //crop_selling.setDesc(description);

                        orderList.add(orders);


                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);


                    adapter = new Orders_User_Adapter(All_OrdersShow_Activity.this,
                            R.layout.order_list_layout, orderList);


                listView.setAdapter(adapter);


            }

            catch (JSONException e) {
                Toast.makeText(All_OrdersShow_Activity.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
