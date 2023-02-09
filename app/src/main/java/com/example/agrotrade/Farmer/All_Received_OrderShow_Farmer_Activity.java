package com.example.agrotrade.Farmer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.agrotrade.Adapters.Orders_Farmer_Adapter;
import com.example.agrotrade.Adapters.Received_Orders_Farmer_Adapter;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Order_Received_Farmer_Model;
import com.example.agrotrade.Model.Orders_Farmer_Model;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_Received_OrderShow_Farmer_Activity extends AppCompatActivity {
    TextView mytext;
    ListView listView;
    ArrayList<Order_Received_Farmer_Model> orderList;
    Received_Orders_Farmer_Adapter adapter;
    private SqliteDatabase mDatabase;
    ProgressDialog pd;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    String Name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__received__order_show_);
        orderList=new ArrayList<>();
        mytext = (TextView) findViewById(R.id.txt2);
        listView= (ListView) findViewById(R.id.order_received_list_farmer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        orderView.setLayoutManager(linearLayoutManager);
//        orderView.setHasFixedSize(true);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");
        mDatabase = new SqliteDatabase(All_Received_OrderShow_Farmer_Activity.this);

        new  AsyncReceivedOrderFarmerListing().execute("id",Name1);


    }


    class AsyncReceivedOrderFarmerListing extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_Received_OrderShow_Farmer_Activity.this);
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
            result = new HTTPRequestDAO().getFarmer_rec_order(params[0],params[1]);
            // result = new HTTPRequestDAO().getSingleData(params[0], params[1], params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {

                if (result.isEmpty()) {

                    Toast.makeText(All_Received_OrderShow_Farmer_Activity.this, "Add New product !", Toast.LENGTH_SHORT).show();

                } else {
                    listView.setVisibility(View.VISIBLE);
                    mytext.setVisibility(View.GONE);

                    JSONObject obj = new JSONObject(result);
                    Toast.makeText(All_Received_OrderShow_Farmer_Activity.this, "" + result, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                       Order_Received_Farmer_Model orders = new Order_Received_Farmer_Model();


//                        String cat_name = jsonObject.getString("cat_name");
//                        String crop_name = jsonObject.getString("crop_name");
//                        String qty = jsonObject.getString("qty");
//                        String price = jsonObject.getString("price");
//                        String description = jsonObject.getString("description");
                        //String f_name = jsonObject.getString("fullname");
//                         String salemaster_fp_id,salemaster_fp_pay_type,salemaster_fp_pay_status,salemaster_fp_address,salemaster_fp_order_date,saledetail_fp_qty,saledetail_fp_total_amt,farmer_id,ferti_pesti_info_id,ferti_pesti_info_name,ferti_pesti_info_img;
                       // String salemaster_fp_id = jsonObject.getString("salemaster_fp_id");

                        String farmer_id = jsonObject.getString("farmer_id");
                        String saledetail_crop_qty = jsonObject.getString("saledetail_crop_qty");
                      //  String salemaster_fp_address = jsonObject.getString("salemaster_fp_address");
                        String saledetail_crop_total_amt = jsonObject.getString("saledetail_crop_total_amt");
                        String salemaster_crop_order_status = jsonObject.getString("salemaster_crop_order_status");
//                        String salemaster_crop_pay_status = jsonObject.getString("salemaster_crop_pay_status");
                        String salemaster_crop_order_date = jsonObject.getString("salemaster_crop_order_date");

                        String product_crop_name = jsonObject.getString("product_crop_name");
                        String product_img = jsonObject.getString("product_img");
                        String customer_name = jsonObject.getString("customer_name");
                        String customer_mobileno = jsonObject.getString("customer_mobileno");

//                    String product_price=jsonObject.getString("product_price");
//                    String product_description=jsonObject.getString("product_description");
//                    String farmer_name=jsonObject.getString("farmer_name");

                        orders.setFarmer_id(farmer_id);
                        orders.setSaledetail_crop_qty(saledetail_crop_qty);
                        orders.setSaledetail_crop_total_amt(saledetail_crop_total_amt);
                        orders.setSalemaster_crop_order_status(salemaster_crop_order_status);
                        orders.setSalemaster_crop_order_date(salemaster_crop_order_date);
                        orders.setProduct_crop_name(product_crop_name);
                        orders.setProduct_img(product_img);
                        orders.setCustomer_name(customer_name);
                        orders.setCustomer_mobileno(customer_mobileno);
                        // crop_selling.setFullname(f_name);
                        //crop_selling.setDesc(description);

                        orderList.add(orders);


                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);


                    adapter = new Received_Orders_Farmer_Adapter(All_Received_OrderShow_Farmer_Activity.this,
                            R.layout.received_farmer_orders_list_farmer_layout, orderList);


                    listView.setAdapter(adapter);

                }
            }

            catch (JSONException e) {
                Toast.makeText(All_Received_OrderShow_Farmer_Activity.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
