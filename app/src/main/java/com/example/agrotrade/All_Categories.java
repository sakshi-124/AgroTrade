package com.example.agrotrade;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agrotrade.Adapters.Category_Adapter;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class All_Categories extends AppCompatActivity {

    //ListView listView;
    GridView listView;
    Category_Adapter adapter;
    ImageView imageView;

    ArrayList<Categories> categoryList;
    ProgressDialog progressDialog,pd;

    String Cat_id;
    public static final String READ_PDF_URL = "http://service.techfusiontechnologies.com/agrofarm/console/service/getcategory.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__categories);

        categoryList=new ArrayList<>();


        progressDialog=new ProgressDialog(this);
       // listView=(ListView) findViewById(R.id.list1);
        listView=(GridView) findViewById(R.id.list1);

        // imageView=(ImageView)findViewById(R.id.imageViewCat);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Categories categories=(Categories) adapterView.getItemAtPosition(i);

                Cat_id=categories.getId();



                Intent intent=new Intent(All_Categories.this,All_Crop_Info.class);
                intent.putExtra("cat_id",Cat_id);
                startActivity(intent);
            }
        });

        new Asyncfeedback().execute("categories");
    }

    class Asyncfeedback extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(All_Categories.this);
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
            //result = new HTTPRequestDAO().getSingleData(params[0],params[1],params[2]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {


                JSONObject obj = new JSONObject(result);
                Toast.makeText(All_Categories.this,"" + result, Toast.LENGTH_SHORT).show();

                JSONArray jsonArray = obj.getJSONArray("response");

                for(int i=0;i<jsonArray.length();i++){

                    //Declaring a json object corresponding to every pdf object in our json Array
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Declaring a Pdf object to add it to the ArrayList  pdfList
                    Categories categories  = new Categories();
                    String name = jsonObject.getString("name");
                    String img = jsonObject.getString("img");
                    String id = jsonObject.getString("id");

                    categories.setName(name);
                    categories.setImg(img);
                    categories.setId(id);

                    categoryList.add(categories);

                }

                adapter=new Category_Adapter(All_Categories.this,R.layout.category_list, categoryList);

                listView.setAdapter(adapter);




            }
            catch (JSONException e) {
                Toast.makeText(All_Categories.this, "" + e, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
