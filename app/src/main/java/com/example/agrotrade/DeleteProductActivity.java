package com.example.agrotrade;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.agrotrade.Adapters.PrefManager;
import com.example.agrotrade.Farmer.All_Crop_Selling;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.User.HomeActivity_User;
import com.example.agrotrade.User.User_registration;
import com.google.gson.Gson;

public class DeleteProductActivity extends AppCompatActivity{
    EditText uname,upass;
    Button usign;
    Spanned Text;
    TextView textView1;
    String c_id;
    ProgressDialog pd;
    private PrefManager prefManager;

    String suname,spass;

    private AwesomeValidation awesomeValidation;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKeyUser";
    SharedPreferences sharedpreferences;


    String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteproduct);


       Intent intent=getIntent();

       pid=intent.getStringExtra("id");


       new AsyncDelete().execute(pid);



    }







    class AsyncDelete extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(DeleteProductActivity.this);
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
            result = new HTTPRequestDAO().DeleteProduct(params[0]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();
            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    c_id=  Integer.toString(mdm.getC_id());

                    // Toast.makeText(Farmer_login.this, "Farmer id" + f_id, Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(DeleteProductActivity.this, All_Crop_Selling.class);
                    startActivity(intent);



                }

                else{
                    Toast.makeText(DeleteProductActivity.this, "Inavild Credentials", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(DeleteProductActivity.this, "invalid server", Toast.LENGTH_LONG).show();
            }



        }



    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(DeleteProductActivity.this, HomeActivity_User.class));
        finish();
    }

}
