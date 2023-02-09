package com.example.agrotrade.Farmer;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.agrotrade.Adapters.Expense_Master_Adapter;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.Expense_MasterList_Model;
import com.example.agrotrade.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AllMasterExpensesActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    TextView textView;
    Expense_Master_Adapter adapter;
    ListView listView;
    ArrayList<Expense_MasterList_Model> expenseList;
    ProgressDialog progressDialog,pd;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    String f_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_expenses);

        expenseList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        // listView=(ListView) findViewById(R.id.list1);
        listView=(ListView) findViewById(R.id.expense_list);
        textView=(TextView) findViewById(R.id.txtDefault);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        Intent i=getIntent();


        f_id=sharedpreferences.getString(Name, "");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Expense_MasterList_Model expense  =(Expense_MasterList_Model) adapterView.getItemAtPosition(i);

//                String date,f_cost,p_cost,half_no_worker, half_sal, full_no_worker, full_sal, extra_cost, extra_note, total_income;
//
//                date= expense.getDate();
//                f_cost=expense.getF_cost();
//                p_cost=expense.getP_cost();
//                half_no_worker=expense.getHalf_no_worker();
//                half_sal=expense.getHalf_sal();
//                full_no_worker=expense.getFull_no_worker();
//                full_sal=expense.getFull_sal();
//                extra_cost=expense.getExtra_cost();
//                extra_note=expense.getExtra_note();
//                total_income=expense.getTotal_income();
                String expense_id,expense_date,expense_f_id,expense_pro_id,product_id,product_crop_name,total_ferti_cost,total_p_cost,total_half_no_worker, total_half_sal,total_full_no_worker,total_full_sal,total_extra_cost,total_total_income;

                expense_date=expense.getExpense_date();
                expense_pro_id=expense.getExpense_pro_id();
                product_id=expense.getProduct_id();
                product_crop_name=expense.getProduct_crop_name();
                total_ferti_cost=expense.getTotal_ferti_cost();
                total_p_cost=expense.getTotal_p_cost();
                total_half_no_worker=expense.getTotal_half_no_worker();
                total_half_sal=expense.getTotal_half_sal();
                total_full_no_worker=expense.getTotal_full_no_worker();
                total_full_sal=expense.getTotal_full_sal();
                total_extra_cost=expense.getTotal_extra_cost();
                total_total_income=expense.getTotal_total_income();

                Toast.makeText(AllMasterExpensesActivity.this, "date: " + expense_date, Toast.LENGTH_SHORT).show();
//                Toast.makeText(AllMasterExpensesActivity.this, "f_cost"+f_cost, Toast.LENGTH_SHORT).show();
//                Toast.makeText(AllMasterExpensesActivity.this, "p_cost" + p_cost, Toast.LENGTH_SHORT).show();
//                Toast.makeText(AllMasterExpensesActivity.this, "half_no_worker"+half_no_worker, Toast.LENGTH_SHORT).show();
                Intent i1=new Intent(AllMasterExpensesActivity.this,AllSingleExpensesActivity.class);
                 i1.putExtra("expense_pro_id",expense.getExpense_pro_id());
                startActivity(i1);
//                intent1.putExtra("f_cost",f_cost);
//                intent1.putExtra("p_cost",p_cost);
//                intent1.putExtra("half_no_worker",half_no_worker);
//                intent1.putExtra("half_sal",half_sal);
//                intent1.putExtra("full_no_worker",full_no_worker);
//                intent1.putExtra("full_sal",full_sal);
//                intent1.putExtra("extra_cost",extra_cost);
//                intent1.putExtra("extra_note",extra_note);
//                intent1.putExtra("total_income",total_income);


            }
        });

//        new AsyncAllCropInfo().execute("f_id",f_id);
        new AsyncAllCropInfo().execute(f_id);

//        floatingActionButton=(FloatingActionButton)findViewById(R.id.addexpense);
//
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(AllMasterExpensesActivity.this, ExpenseManager.class);
//                startActivity(intent);
//            }
//        });


    }

    class AsyncAllCropInfo extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(AllMasterExpensesActivity.this);
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
            result = new HTTPRequestDAO().getMasterExpense(params[0]);
            return result;
        }

        protected void onPostExecute(String result) {

            pd.dismiss();


            try {


                if(result.isEmpty()){

                    Toast.makeText(AllMasterExpensesActivity.this, "Add New Expense!", Toast.LENGTH_SHORT).show();

                }
                else {


                    listView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);

                    JSONObject obj = new JSONObject(result);
                    Toast.makeText(AllMasterExpensesActivity.this, "" + result, Toast.LENGTH_SHORT).show();

                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        //Declaring a json object corresponding to every pdf object in our json Array
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Declaring a Pdf object to add it to the ArrayList  pdfList
                        Expense_MasterList_Model expense = new Expense_MasterList_Model();


                        String expense_date = jsonObject.getString("expense_date");
                        String expense_pro_id = jsonObject.getString("expense_pro_id");
                        String product_crop_name = jsonObject.getString("product_crop_name");
                        String total_ferti_cost = jsonObject.getString("total_ferti_cost");
                        String total_p_cost = jsonObject.getString("total_p_cost");
                        String total_half_no_worker = jsonObject.getString("total_half_no_worker");
                        String total_half_sal = jsonObject.getString("total_half_sal");
                        String total_full_no_worker = jsonObject.getString("total_full_no_worker");
                        String total_full_sal = jsonObject.getString("total_full_sal");
                        String total_extra_cost = jsonObject.getString("total_extra_cost");
                        String total_total_income = jsonObject.getString("total_total_income");

                        expense.setExpense_date(expense_date);
                        expense.setExpense_pro_id(expense_pro_id);
                        expense.setProduct_crop_name(product_crop_name);
                        expense.setTotal_ferti_cost(total_ferti_cost);
                        expense.setTotal_p_cost(total_p_cost);
                        expense.setTotal_half_no_worker(total_half_no_worker);
                        expense.setTotal_half_sal(total_half_sal);
                        expense.setTotal_full_no_worker(total_full_no_worker);
                        expense.setTotal_full_sal(total_full_sal);
                        expense.setTotal_extra_cost(total_extra_cost);
                        expense.setTotal_total_income(total_total_income);

                        expenseList.add(expense);


                    }

                          /*  feedbackAdapter=new FeedbackAdapter(AllFeedback.this,R.layout.feedback_layout, pdfList);

                            listView.setAdapter(feedbackAdapter);

                            feedbackAdapter.notifyDataSetChanged();*/

                    // adapter = new UsersAdapter(AllUsersActivity.this, productList);


                    adapter = new Expense_Master_Adapter(AllMasterExpensesActivity.this, R.layout.expense_master_list, expenseList);

                    listView.setAdapter(adapter);

                }


            } catch (JSONException e) {
                Toast.makeText(AllMasterExpensesActivity.this, "Something went wrong :(" + e, Toast.LENGTH_SHORT).show();
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
