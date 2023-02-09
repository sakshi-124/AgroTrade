package com.example.agrotrade;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Ferti_Pesti_Buying_Adapter;
import com.example.agrotrade.Adapters.RecyclerViewAdapterFertiPesti;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Ferti_Pesti_Buying;
import com.example.agrotrade.User.ShoppingCart.SqliteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_FertiPesti_Buying extends AppCompatActivity {

    Ferti_Pesti_Buying_Adapter adapter;
    RecyclerViewAdapterFertiPesti shopAdapter;
   // ListView listView;
    RecyclerView recyclerView;
    ArrayList<Ferti_Pesti_Buying> cropList;
    ProgressDialog progressDialog,pd;
    Button add_to_cart;

    String c_id;
    public SqliteDatabase mDatabase;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__ferti_pesti__selling);

        cropList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        // listView=(ListView) findViewById(R.id.list1);
       // listView=(ListView) findViewById(R.id.list2);
        recyclerView=(RecyclerView) findViewById(R.id.list4);
        add_to_cart=(Button)findViewById(R.id.add_to_cart);


        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        c_id=sharedpreferences.getString(Name, "");

        mDatabase = new SqliteDatabase(All_FertiPesti_Buying.this);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Ferti_Pesti_Buying ferti_pesti_buying = (Ferti_Pesti_Buying) adapterView.getItemAtPosition(i);
//
//
//                String id,name,price,weight,description,pro_info,crop_name,pest_name,direction,img;
//                name = ferti_pesti_buying.getName();
//                price=ferti_pesti_buying.getPrice();
//                weight=ferti_pesti_buying.getWeight();
//                description=ferti_pesti_buying.getDescription();
//                pro_info=ferti_pesti_buying.getPro_info();
//                crop_name=ferti_pesti_buying.getCrop_name();
//                pest_name=ferti_pesti_buying.getPest_name();
//                direction=ferti_pesti_buying.getDirection();
//                img=ferti_pesti_buying.getImg();
//
//
//                Toast.makeText(All_FertiPesti_Buying.this, "Ferti/Pesti Name: " + pro_info, Toast.LENGTH_SHORT).show();
//                Intent intent1=new Intent(All_FertiPesti_Buying.this, FertiPesti_DetailShow_Activity.class);
//                // intent1.putExtra("season",crop_info.getSeason());
//                intent1.putExtra("name",name);
//                intent1.putExtra("price",price);
//                intent1.putExtra("weight",weight);
//                intent1.putExtra("pro_info",pro_info);
//                intent1.putExtra("crop_name",crop_name);
//                intent1.putExtra("description",description);
//                intent1.putExtra("pest_name",pest_name);
//                intent1.putExtra("direction",direction);
//                intent1.putExtra("img",img);
//                startActivity(intent1);
//
//
//                Toast.makeText(All_FertiPesti_Buying.this, "clicked" + name, Toast.LENGTH_SHORT).show();
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

        new AsyncAllFertiPestiBuying().execute("ferti_pesti_info");
    }

    class AsyncAllFertiPestiBuying extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_FertiPesti_Buying.this);
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
                result = new HTTPRequestDAO().getMasterData(params[0]);
           // result = new HTTPRequestDAO().getJoinData(params[0],params[1],params[2],params[3]);
            // result = new HTTPRequestDAO().getSingleData(params[0], params[1], params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {

                JSONObject obj = new JSONObject(result);
                Toast.makeText(All_FertiPesti_Buying.this, "" + result, Toast.LENGTH_SHORT).show();

                JSONArray jsonArray = obj.getJSONArray("response");

                for (int i = 0; i < jsonArray.length(); i++) {

                    //Declaring a json object corresponding to every pdf object in our json Array
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Declaring a Pdf object to add it to the ArrayList  pdfList
                    Ferti_Pesti_Buying ferti_pesti_buying = new Ferti_Pesti_Buying();


//                        String cat_name = jsonObject.getString("cat_name");
//                        String crop_name = jsonObject.getString("crop_name");
//                        String qty = jsonObject.getString("qty");
//                        String price = jsonObject.getString("price");
//                        String description = jsonObject.getString("description");
                    //String f_name = jsonObject.getString("fullname");

                    String name=jsonObject.getString("name");
                    String id=jsonObject.getString("id");
                    String crop_name=jsonObject.getString("crop_name");
                    String weight=jsonObject.getString("weight");
                    String unit=jsonObject.getString("unit");
                    String price=jsonObject.getString("price");
                    String pro_info=jsonObject.getString("pro_info");
                    String description=jsonObject.getString("description");
                    String pest_name=jsonObject.getString("pest_name");
                    String direction=jsonObject.getString("direction");
                    String img = jsonObject.getString("img");

                    ferti_pesti_buying.setName(name);
                    ferti_pesti_buying.setPro_info(pro_info);
                    ferti_pesti_buying.setImg(img);
                    ferti_pesti_buying.setCrop_name(crop_name);
                    ferti_pesti_buying.setDescription(description);
                    ferti_pesti_buying.setDirection(direction);
                    ferti_pesti_buying.setWeight(weight);
                    ferti_pesti_buying.setUnit(unit);
                    ferti_pesti_buying.setPrice(price);
                    ferti_pesti_buying.setId(id);
                    ferti_pesti_buying.setPest_name(pest_name);

                    // crop_selling.setFullname(f_name);
                    //crop_selling.setDesc(description);

                    cropList.add(ferti_pesti_buying);


                }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                // adapter = new UsersAdapter(AllUsersActivity.this, productList);


//                adapter = new Ferti_Pesti_Buying_Adapter(All_FertiPesti_Buying.this,
//                        R.layout.ferti_pesti_buying_list, cropList);
//
//
//
//                listView.setAdapter(adapter);

                shopAdapter=new RecyclerViewAdapterFertiPesti(getApplicationContext(), cropList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(shopAdapter);


            }

            catch (JSONException e) {
                Toast.makeText(All_FertiPesti_Buying.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
            }


        }
    }
}
