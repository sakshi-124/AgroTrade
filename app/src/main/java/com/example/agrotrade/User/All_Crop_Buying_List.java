package com.example.agrotrade.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Crop_Buying_Adapter;
import com.example.agrotrade.Adapters.RecyclerViewAdapterCrop;
import com.example.agrotrade.Helpers.HTTPRequestDAO;

import com.example.agrotrade.Model.Crop_Buying_Model;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_Crop_Buying_List extends AppCompatActivity {

    Crop_Buying_Adapter adapter;
//    ListView listView;
    RecyclerView recyclerView;
    ArrayList<Crop_Buying_Model> cropList;
    ProgressDialog progressDialog,pd;
    Button add_to_cart;


    String c_id;
    String cat_name, crop_name, qty, price, description;
    RecyclerViewAdapterCrop shopAdapter;
    public SqliteDatabase mDatabase;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKeyUser";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all__crop__buying__list);

        cropList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        // listView=(ListView) findViewById(R.id.list1);
//        listView=(ListView) findViewById(R.id.list1);
        recyclerView=(RecyclerView) findViewById(R.id.list3);
       //add_to_cart=(Button)findViewById(R.id.add_to_cart);


        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        c_id=sharedpreferences.getString(Name, "");

        mDatabase = new SqliteDatabase(All_Crop_Buying_List.this);




//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Crop_Buying_Model crop_buying = (Crop_Buying_Model) adapterView.getItemAtPosition(i);
//
//
//               String crop_name,qty,price,description,cat_name;
//                String farmer_id,product_id,product_f_id,product_cat_name,product_crop_name,product_qty,product_price,product_description,farmer_name;
////                cat_name = crop_selling.getCat_name();
////                crop_name = crop_selling.getCrop_name();
////                qty = crop_selling.getQty();
////                price = crop_selling.getPrice();
////                description = crop_selling.getDesc();
//
//                farmer_id=crop_buying.getFarmer_id();
//                product_id=crop_buying.getProduct_id();
//                product_f_id=crop_buying.getProduct_f_id();
//                farmer_name=crop_buying.getFarmer_name();
//                product_cat_name=crop_buying.getProduct_cat_name();
//                product_crop_name=crop_buying.getProduct_crop_name();
//                product_qty=crop_buying.getProduct_qty();
//                product_price=crop_buying.getProduct_price();
//                product_description=crop_buying.getProduct_description();
//
//
//                Toast.makeText(All_Crop_Buying_List.this, "Crop: " + product_crop_name, Toast.LENGTH_SHORT).show();
//                Intent intent1=new Intent(All_Crop_Buying_List.this, Product_DetailShow_Activity.class);
//                // intent1.putExtra("season",crop_info.getSeason());
//                intent1.putExtra("product_f_id",product_f_id);
//                intent1.putExtra("product_id",product_id);
//                intent1.putExtra("product_cat_name",product_cat_name);
//                intent1.putExtra("product_crop_name",product_crop_name);
//                intent1.putExtra("product_qty",product_qty);
//                intent1.putExtra("product_price",product_price);
//                intent1.putExtra("farmer_name",farmer_name);
//                intent1.putExtra("product_description",product_description);
//                startActivity(intent1);
//
//
//                Toast.makeText(All_Crop_Buying_List.this, "clicked" + product_cat_name, Toast.LENGTH_SHORT).show();
//
//
//
////                final Dialog builder = new Dialog(All_Crop_Buying_List .this);
////
////                builder.setContentView(R.layout.my_layout_main);
////
////                final EditText text = (EditText) builder.findViewById(R.id.enterqty);
////                Button button = (Button) builder.findViewById(R.id.adddata);
////
////
////                button.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View view) {
////
////
////                        int intqty = Integer.valueOf(text.getText().toString());
////                        if (intqty == 0) {
////
////                            Toast.makeText(All_Crop_Buying_List.this, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
////                            builder.dismiss();
////                        } else {
////
////
////                            int c = mDatabase.getSelectedStore(crop_name);
////                            //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();
////
////                            if (c == 1) {
////                                Toast.makeText(All_Crop_Buying_List.this, "Already Added in Cart!", Toast.LENGTH_LONG).show();
////                                builder.dismiss();
////                            } else {
////
////
////                                String data = text.getText().toString();
////                                Double d = Double.parseDouble(price);
////                                int myqty = Integer.valueOf(data);
////                                int myprice = Integer.valueOf(d.intValue());
////
////                                int all = myqty * myprice;
////
////
////                                String total = String.valueOf(all);
////
////
////                                //  Toast.makeText(CompareMonitorActivity.this, "" + all, Toast.LENGTH_SHORT).show();
////
////                                Toast.makeText(All_Crop_Buying_List.this, "Added To Cart!", Toast.LENGTH_SHORT).show();
////
////
////                                Farmer_ProductModel newProduct = new Farmer_ProductModel(cat_name, crop_name, total, description, data);
////                                mDatabase.addProduct(newProduct);
////                                builder.dismiss();
////
////
//////                              Intent intent = new Intent(CompareMonitorActivity.this, Farmer_ProductCartActivity.class);
//////                              startActivity(intent);
//////
////                            }
////                        }
////                    }
////                });
////
////                builder.show();
//            }
//
//
//
//
//
//
//        });

        new AsyncAllCropBuying().execute("product","farmer","f_id","id");


    }

    class AsyncAllCropBuying extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_Crop_Buying_List.this);
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
//            result = new HTTPRequestDAO().getMasterData(params[0]);
            result = new HTTPRequestDAO().getJoinData(params[0],params[1],params[2],params[3]);
           // result = new HTTPRequestDAO().getSingleData(params[0], params[1], params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {

                    JSONObject obj = new JSONObject(result);
                    Toast.makeText(All_Crop_Buying_List.this, "" + result, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                        Crop_Buying_Model crop_buying = new Crop_Buying_Model();


//                        String cat_name = jsonObject.getString("cat_name");
//                        String crop_name = jsonObject.getString("crop_name");
//                        String qty = jsonObject.getString("qty");
//                        String price = jsonObject.getString("price");
//                        String description = jsonObject.getString("description");
                        //String f_name = jsonObject.getString("fullname");

                        String product_id=jsonObject.getString("product_id");
                        String product_img=jsonObject.getString("product_img");
                        String product_cat_name=jsonObject.getString("product_cat_name");
                        String product_crop_name=jsonObject.getString("product_crop_name");
                        String product_qty=jsonObject.getString("product_qty");
                        String product_price=jsonObject.getString("product_price");
                        String product_description=jsonObject.getString("product_description");
                        String farmer_name=jsonObject.getString("farmer_name");

                        crop_buying.setProduct_cat_name(product_cat_name);
                        crop_buying.setProduct_id(product_id);
                        crop_buying.setProduct_crop_name(product_crop_name);
                        crop_buying.setProduct_qty(product_qty);
                        crop_buying.setProduct_price(product_price);
                        crop_buying.setProduct_description(product_description);
                        crop_buying.setFarmer_name(farmer_name);
                        crop_buying.setProduct_img(product_img);
                       // crop_selling.setFullname(f_name);
                        //crop_selling.setDesc(description);

                        cropList.add(crop_buying);


                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);

                shopAdapter=new RecyclerViewAdapterCrop(getApplicationContext(), cropList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(shopAdapter);
//                    adapter = new Crop_Buying_Adapter(All_Crop_Buying_List.this,
//                            R.layout.crop_buying_list, cropList);
//
//
//
//                    listView.setAdapter(adapter);


            }

            catch (JSONException e) {
                Toast.makeText(All_Crop_Buying_List.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
