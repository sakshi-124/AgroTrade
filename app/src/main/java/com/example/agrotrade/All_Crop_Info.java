package com.example.agrotrade;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Crop_Info_Adapter;
import com.example.agrotrade.Adapters.Crop_Info_Adapter_Filterable;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Crop_Info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_Crop_Info extends AppCompatActivity implements SearchView.OnQueryTextListener {

    GridView listView;
    //Crop_Info_Adapter adapter;
    Crop_Info_Adapter_Filterable adapter;

    ArrayList<Crop_Info> cropList;
    ProgressDialog progressDialog,pd;

    SearchView  searchView;

    String Cat_id;
   // public static final String READ_PDF_URL = "http://service.techfusiontechnologies.com/agrofarm/console/service/getcropinfo1.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__crop__info);

        cropList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        listView = (GridView) findViewById(R.id.list1);




        searchView=(SearchView) findViewById(R.id.searchview);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);


        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        final Intent intent=getIntent();


        Cat_id=intent.getStringExtra("cat_id");

        Toast.makeText(this, "" + Cat_id, Toast.LENGTH_SHORT).show();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Crop_Info crop_info  =(Crop_Info) adapterView.getItemAtPosition(i);

               String name,season,irrigation,varieties,soil_type,plant_material,spacing,harvesting,img;
                season= crop_info.getSeason();
                name=crop_info.getName();
                irrigation=crop_info.getIrrigation();
                varieties=crop_info.getVarieties();
                soil_type=crop_info.getSoil_type();
                plant_material=crop_info.getPlant_material();
                spacing=crop_info.getSpacing();
                harvesting=crop_info.getHarvesting();
                img=crop_info.getImg();

                Toast.makeText(All_Crop_Info.this, "Season: " + season, Toast.LENGTH_SHORT).show();
               Intent intent1=new Intent(All_Crop_Info.this,All_Crop_Info_Show.class);
               // intent1.putExtra("season",crop_info.getSeason());
                intent1.putExtra("season",season);
                intent1.putExtra("name",name);
                intent1.putExtra("irrigation",irrigation);
                intent1.putExtra("varieties",varieties);
                intent1.putExtra("soil_type",soil_type);
                intent1.putExtra("plant_material",plant_material);
                intent1.putExtra("spacing",spacing);
                intent1.putExtra("harvesting",harvesting);
                intent1.putExtra("img",img);
                startActivity(intent1);

            }
        });


        new AsyncAllCropInfo().execute("crop_info","cat_id",Cat_id);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        adapter.getFilter().filter(s);
        return true;
    }


    class AsyncAllCropInfo extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd = new ProgressDialog(All_Crop_Info.this);
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
                    Toast.makeText(All_Crop_Info.this,"" + result, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for(int i=0;i<jsonArray.length();i++){

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                        Crop_Info crop_info  = new Crop_Info();
                        String name = jsonObject.getString("name");
                        String img = jsonObject.getString("img");
                        // String details = jsonObject.getString("mobileno");
                        // String rating = jsonObject.getString("address");

                               String season = jsonObject.getString("season");
                                String irrigation = jsonObject.getString("irrigation");
                                String varieties = jsonObject.getString("varieties");
                                String soil_type = jsonObject.getString("soil_type");
                                String plant_material = jsonObject.getString("plant_material");
                                String spacing = jsonObject.getString("spacing");
                                String harvesting = jsonObject.getString("harvesting");

                        // pdf.setUsername(regname);
                        // pdf.setMobileno(details);
                        // pdf.setAddress(rating);
                                crop_info.setName(name);
                                crop_info.setImg(img);
                                crop_info.setSeason(season);
                                crop_info.setIrrigation(irrigation);
                                crop_info.setVarieties(varieties);
                                crop_info.setSoil_type(soil_type);
                                crop_info.setPlant_material(plant_material);
                                crop_info.setSpacing(spacing);
                                crop_info.setHarvesting(harvesting);

                        cropList.add(crop_info);





                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);


                  //  adapter=new Crop_Info_Adapter(All_Crop_Info.this,R.layout.crop_list,cropList);
adapter=new Crop_Info_Adapter_Filterable(All_Crop_Info.this,cropList);
                    listView.setAdapter(adapter);
                    listView.setTextFilterEnabled(false);




                } catch (JSONException e) {
                    Toast.makeText(All_Crop_Info.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
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
    }

