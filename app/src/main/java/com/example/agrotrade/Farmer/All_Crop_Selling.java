package com.example.agrotrade.Farmer;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Crop_Selling_Adapter;
import com.example.agrotrade.Adapters.RecyclerViewAdapterProduct;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Crop_Selling;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_Crop_Selling extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    TextView textView;
    Crop_Selling_Adapter adapter;
    RecyclerView recyclerView;
//    ArrayList<Crop_Selling> cropList;

    ArrayList<Crop_Selling> cropList= new ArrayList<Crop_Selling>();
    ProgressDialog progressDialog,pd;

    RecyclerViewAdapterProduct shopAdapter;

    String f_id;
    String cat_name, crop_name, qty, price, description;


    public SqliteDatabase mDatabase;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__crop__selling);

        cropList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        // listView=(ListView) findViewById(R.id.list1);
        recyclerView=(RecyclerView) findViewById(R.id.list1);
        textView=(TextView) findViewById(R.id.txtDefault);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        f_id=sharedpreferences.getString(Name, "");
        mDatabase = new SqliteDatabase(All_Crop_Selling.this);



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Crop_Selling crop_selling = (Crop_Selling) adapterView.getItemAtPosition(i);
//
//
//                cat_name = crop_selling.getCat_name();
//                crop_name = crop_selling.getCrop_name();
//                qty = crop_selling.getQty();
//                price = crop_selling.getPrice();
//                description = crop_selling.getDesc();
//
//
//                Toast.makeText(All_Crop_Selling.this, "clicked" + cat_name, Toast.LENGTH_SHORT).show();

//                Toast.makeText(All_Crop_Selling.this, "crop name " + crop_name, Toast.LENGTH_SHORT).show();
//                Intent intent1=new Intent(All_Crop_Selling.this,Crop_Selling_Form.class);
//                // intent1.putExtra("season",crop_info.getSeason());
//                intent1.putExtra("cat_name",cat_name);
//                intent1.putExtra("crop_name",crop_name);
//                intent1.putExtra("qty",qty);
//                intent1.putExtra("price",price);
//                intent1.putExtra("desc",description);
//                startActivity(intent1);


//                final Dialog builder = new Dialog(All_Crop_Selling  .this);
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
//                            Toast.makeText(All_Crop_Selling.this, "Quantity Can't Be Zero!", Toast.LENGTH_SHORT).show();
//                            builder.dismiss();
//                        } else {
//
//
//                            int c = mDatabase.getSelectedStore(crop_name);
//                            //Toast.makeText(getActivity(), ""+c, Toast.LENGTH_LONG).show();
//
//                            if (c == 1) {
//                                Toast.makeText(All_Crop_Selling.this, "Already Added in Cart!", Toast.LENGTH_LONG).show();
//                                builder.dismiss();
//                            } else {
//
//
//                                String data = text.getText().toString();
//                                Double d = Double.parseDouble(price);
//                                int myqty = Integer.valueOf(data);
//                                int myprice = Integer.valueOf(d.intValue());
//
//                                int all = myqty * myprice;
//
//
//                                String total = String.valueOf(all);
//
//
//                                //  Toast.makeText(CompareMonitorActivity.this, "" + all, Toast.LENGTH_SHORT).show();
//
//                                Toast.makeText(All_Crop_Selling.this, "Added To Cart!", Toast.LENGTH_SHORT).show();
//
//
//                                Farmer_ProductModel newProduct = new Farmer_ProductModel(cat_name, crop_name, total, description, data);
//                                mDatabase.addProduct(newProduct);
//                                builder.dismiss();
//
//
////                              Intent intent = new Intent(CompareMonitorActivity.this, Farmer_ProductCartActivity.class);
////                              startActivity(intent);
////
//                            }
//                        }
//                    }
//                });
//
//                builder.show();
//            }
//
//
//
//
//
//
//        });

        new AsyncAllCropSelling().execute("product","f_id",f_id);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.addcrop);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(All_Crop_Selling.this, Crop_Selling_Form.class);
                startActivity(intent);
            }
        });

    }

    class AsyncAllCropSelling extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_Crop_Selling.this);
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
            result = new HTTPRequestDAO().getSingleData(params[0], params[1], params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {


                if (result.isEmpty()) {

                    Toast.makeText(All_Crop_Selling.this, "Add New Crop !", Toast.LENGTH_SHORT).show();

                } else {


                    recyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);

                    JSONObject obj = new JSONObject(result);
                    Toast.makeText(All_Crop_Selling.this, "" + result, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                        Crop_Selling crop_selling = new Crop_Selling();


                        String cat_name = jsonObject.getString("cat_name");
                        String crop_name = jsonObject.getString("crop_name");
                        String qty = jsonObject.getString("qty");
                        String price = jsonObject.getString("price");
                        String description = jsonObject.getString("description");
                        String ID = jsonObject.getString("id");

                        crop_selling.setCat_name(cat_name);
                        crop_selling.setId(ID);
                        crop_selling.setCrop_name(crop_name);
                        crop_selling.setQty(qty);
                        crop_selling.setPrice(price);
                        crop_selling.setDesc(description);

                        cropList.add(crop_selling);


                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);



                    shopAdapter=new RecyclerViewAdapterProduct(getApplicationContext(), cropList);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(shopAdapter);
//                    adapter = new Crop_Selling_Adapter(All_Crop_Selling.this, R.layout.crop_selling_list, cropList);
//
//                    listView.setAdapter(adapter);

                }
            }

            catch (JSONException e) {
                Toast.makeText(All_Crop_Selling.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
