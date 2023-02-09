package com.example.agrotrade.Farmer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;

public class Farmer_registration extends AppCompatActivity implements View.OnClickListener {
    EditText fname,fadd,fpass,fmob,fcpass;
    Button freg;
    private AwesomeValidation awesomeValidation;

    ProgressDialog pd;

    String sname,sadd,smob,spass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        fname = (EditText) findViewById(R.id.funame);
        fpass = (EditText) findViewById(R.id.fpass);
        fadd = (EditText) findViewById(R.id.fadd);
        fcpass=(EditText)findViewById(R.id.fcpass);
        fmob=(EditText)findViewById(R.id.fmob);
        freg = (Button) findViewById(R.id.freg);

        freg.setOnClickListener(this);

        awesomeValidation.addValidation(this, R.id.funame, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.fpass, "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", R.string.passerror);
        awesomeValidation.addValidation(this, R.id.fcpass, "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", R.string.passerror);
        awesomeValidation.addValidation(this, R.id.fadd, "^[#.0-9a-zA-Z\\s,-]+$", R.string.address);
        awesomeValidation.addValidation(this, R.id.fmob, "^[0-9]{2}[0-9]{8}$", R.string.moberror);


        /*freg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Farmer_registration.this, Farmer_OtpVerify.class);
                startActivity(intent);
            }
        });
*/
    }

     private void submitForm() {

        if (awesomeValidation.validate() & fpass.getText().toString().equals(fcpass.getText().toString())) {

            //& fpass.getText().toString().equals(fcpass.getText().toString())

            sname=fname.getText().toString();
            sadd=fadd.getText().toString();
            smob=fmob.getText().toString();
            spass=fpass.getText().toString();

            new AsyncRegistration().execute(sname,sadd,smob,spass,"20.949910","72.919580");

            //process the data further
        }
        else {
            Toast.makeText(this, "Password Does not match", Toast.LENGTH_SHORT).show();
        }

        //first validate the form then move ahead
    }

    @Override
    public void onClick(View view) {
        if (view == freg) {
            submitForm();
        }

    }


    class AsyncRegistration extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Farmer_registration.this);
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
            result = new HTTPRequestDAO().FarmerRegistration(params[0], params[1],params[2],params[3],params[4],params[5]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    Toast.makeText(Farmer_registration.this, "Successful Registration", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(Farmer_registration.this,Farmer_OtpVerify.class);
                    i.putExtra("mob",smob);

                    startActivity(i);

                }
                else if (mdm.getData() == 500) {

                    Toast.makeText(Farmer_registration.this, "Mobile no already exist!", Toast.LENGTH_LONG).show();


                }
                else{
                    Toast.makeText(Farmer_registration.this, "Something went wrong", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Farmer_registration.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
