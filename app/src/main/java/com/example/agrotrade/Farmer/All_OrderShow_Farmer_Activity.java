package com.example.agrotrade.Farmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Orders_Farmer_Adapter;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Orders_Farmer_Model;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_OrderShow_Farmer_Activity extends AppCompatActivity {
    TextView mytext;
    ListView listView;
    ArrayList<Orders_Farmer_Model> orderList;
    Orders_Farmer_Adapter adapter;
    private SqliteDatabase mDatabase;
    ProgressDialog pd;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    String Name1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__order_show__farmer_);
        orderList=new ArrayList<>();
        mytext = (TextView) findViewById(R.id.txt2);
        listView= (ListView) findViewById(R.id.order_list_farmer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        orderView.setLayoutManager(linearLayoutManager);
//        orderView.setHasFixedSize(true);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");
        mDatabase = new SqliteDatabase(All_OrderShow_Farmer_Activity.this);

        new  AsyncAllOrderFarmerListing().execute("f_id",Name1);


    }


    class AsyncAllOrderFarmerListing extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_OrderShow_Farmer_Activity.this);
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
            result = new HTTPRequestDAO().getJoinData_Ferti(params[0],params[1]);
            // result = new HTTPRequestDAO().getSingleData(params[0], params[1], params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {

                if (result.isEmpty()) {

                    Toast.makeText(All_OrderShow_Farmer_Activity.this, "Add New product !", Toast.LENGTH_SHORT).show();

                } else {
                    listView.setVisibility(View.VISIBLE);
                    mytext.setVisibility(View.GONE);

                    JSONObject obj = new JSONObject(result);
                    Toast.makeText(All_OrderShow_Farmer_Activity.this, "" + result, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                        Orders_Farmer_Model orders = new Orders_Farmer_Model();


//                        String cat_name = jsonObject.getString("cat_name");
//                        String crop_name = jsonObject.getString("crop_name");
//                        String qty = jsonObject.getString("qty");
//                        String price = jsonObject.getString("price");
//                        String description = jsonObject.getString("description");
                        //String f_name = jsonObject.getString("fullname");
//                         String salemaster_fp_id,salemaster_fp_pay_type,salemaster_fp_pay_status,salemaster_fp_address,salemaster_fp_order_date,saledetail_fp_qty,saledetail_fp_total_amt,farmer_id,ferti_pesti_info_id,ferti_pesti_info_name,ferti_pesti_info_img;
                       // String salemaster_fp_id = jsonObject.getString("salemaster_fp_id");
                        String salemaster_fp_pay_type = jsonObject.getString("salemaster_fp_pay_type");
                        String salemaster_fp_pay_status = jsonObject.getString("salemaster_fp_pay_status");
                      //  String salemaster_fp_address = jsonObject.getString("salemaster_fp_address");
                        String salemaster_fp_order_date = jsonObject.getString("salemaster_fp_order_date");
                        String saledetail_fp_qty = jsonObject.getString("saledetail_fp_qty");
                        String saledetail_fp_total_amt = jsonObject.getString("saledetail_fp_total_amt");
                        String ferti_pesti_info_id = jsonObject.getString("ferti_pesti_info_id");

                        String ferti_pesti_info_name = jsonObject.getString("ferti_pesti_info_name");
                        String ferti_pesti_info_img = jsonObject.getString("ferti_pesti_info_img");

//                    String product_price=jsonObject.getString("product_price");
//                    String product_description=jsonObject.getString("product_description");
//                    String farmer_name=jsonObject.getString("farmer_name");

                        orders.setSalemaster_fp_pay_type(salemaster_fp_pay_type);
                        orders.setSalemaster_fp_pay_status(salemaster_fp_pay_status);
                        orders.setSalemaster_fp_order_date(salemaster_fp_order_date);
                        orders.setSaledetail_fp_qty(saledetail_fp_qty);
                        orders.setSaledetail_fp_total_amt(saledetail_fp_total_amt);
                        orders.setFerti_pesti_info_id(ferti_pesti_info_id);
                        orders.setFerti_pesti_info_name(ferti_pesti_info_name);
                        orders.setFerti_pesti_info_img(ferti_pesti_info_img);
                        // crop_selling.setFullname(f_name);
                        //crop_selling.setDesc(description);

                        orderList.add(orders);


                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);


                    adapter = new Orders_Farmer_Adapter(All_OrderShow_Farmer_Activity.this,
                            R.layout.order_list_farmer_layout, orderList);


                    listView.setAdapter(adapter);

                }
            }

            catch (JSONException e) {
                Toast.makeText(All_OrderShow_Farmer_Activity.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
