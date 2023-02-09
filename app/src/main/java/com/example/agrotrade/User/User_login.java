package com.example.agrotrade.User;

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
import com.example.agrotrade.Farmer.Farmer_login;
import com.example.agrotrade.Farmer.HomeActivity;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.MainActivity;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;

public class User_login extends AppCompatActivity implements View.OnClickListener{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        setContentView(R.layout.activity_user_login);


        uname=(EditText)findViewById(R.id.uname);
        upass=(EditText)findViewById(R.id.upass);
        usign=(Button) findViewById(R.id.usign);
        textView1 = (TextView)findViewById(R.id.textView1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this, R.id.uname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.upass, "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", R.string.passerror);


        usign.setOnClickListener(this);/*new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_login.this, User_login.class);
                startActivity(i);

            }
        });*/

        textView1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(User_login.this, User_registration.class);
                startActivity(intent);
            }
        });
    }



    private void submitForm() {

        if (awesomeValidation.validate()) {
           // Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();

            suname=uname.getText().toString();
            spass=upass.getText().toString();

            new AsyncMyLogin().execute(suname,spass);

            //process the data further
        }


        //first validate the form then move ahead
    }


    @Override
    public void onClick(View view) {
        if (view == usign) {
            submitForm();
        }

    }


    class AsyncMyLogin extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(User_login.this);
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
            result = new HTTPRequestDAO().CustomerLogin(params[0], params[1]);
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


                    Toast.makeText(User_login.this, "Log in Successful! " + c_id, Toast.LENGTH_LONG).show();
//                   launchHomeScreen();


                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(Name, c_id);
                    editor.apply();
                    launchHomeScreen();



                }

                else{
                    Toast.makeText(User_login.this, "Inavild Credentials", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(User_login.this, "invalid server", Toast.LENGTH_LONG).show();
            }



        }



    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(User_login.this, HomeActivity_User.class));
        finish();
    }

}
