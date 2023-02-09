package com.example.agrotrade.Farmer;

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
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.MainActivity;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.example.agrotrade.User.HomeActivity_User;
import com.google.gson.Gson;

public class Farmer_login extends AppCompatActivity implements View.OnClickListener {
    EditText fname,fpass;
    Button fsign;
    Spanned Text;
    TextView textView1;
    ProgressDialog pd;
    private PrefManager prefManager;

    String smob,spass;

    String f_id;
    private AwesomeValidation awesomeValidation;


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    String mydata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        setContentView(R.layout.activity_farmer_login);

        fname=(EditText)findViewById(R.id.fname);
        fpass=(EditText)findViewById(R.id.fpass);
        fsign=(Button) findViewById(R.id.fsign);
        textView1 = (TextView)findViewById(R.id.textView1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        mydata=sharedpreferences.getString(Name, "");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        //awesomeValidation.addValidation(this, R.id.fname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        //awesomeValidation.addValidation(this, R.id.fpass, "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})", R.string.passerror);
        awesomeValidation.addValidation(this, R.id.fname, "^[0-9]{2}[0-9]{8}$", R.string.moberror);
        awesomeValidation.addValidation(this, R.id.fpass, "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", R.string.passerror);



        fsign.setOnClickListener(this);

      /*  fsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Farmer_login.this, Farmer_login.class);
                startActivity(i);

            }
        });*/

        textView1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Farmer_login.this, Farmer_registration.class);
                startActivity(intent);
            }
        });
    }

    private void submitForm() {
        //first validate the form then move ahead

        if (awesomeValidation.validate()) {
            //Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            smob=fname.getText().toString();
            spass=fpass.getText().toString();
            new AsyncLogin().execute(smob,spass);
            //process the data further
        }
    }

    @Override
    public void onClick(View view) {
        if (view == fsign) {
            submitForm();
        }

    }


    class AsyncLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Farmer_login.this);
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
            result = new HTTPRequestDAO().FarmerLogin(params[0], params[1]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {


                  f_id=  Integer.toString(mdm.getF_id());

                   // Toast.makeText(Farmer_login.this, "Farmer id" + f_id, Toast.LENGTH_SHORT).show();


                    Toast.makeText(Farmer_login.this, "Log in Successful!", Toast.LENGTH_LONG).show();
//                   launchHomeScreen();





                    SharedPreferences.Editor editor = sharedpreferences.edit();




                    editor.putString(Name, f_id);
                    editor.apply();
                    launchHomeScreen();

                }

                else{
                    Toast.makeText(Farmer_login.this, "Inavild Credentials", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Farmer_login.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void launchHomeScreen() {

        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(Farmer_login.this, HomeActivity.class));
        finish();
    }
}
