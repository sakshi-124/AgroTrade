package com.example.agrotrade.Farmer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;

public class Farmer_OtpVerify extends AppCompatActivity implements View.OnClickListener {
    EditText fotp;
    TextView fresendotp;
    Button fotpbtn;
    Spanned Text;
    TextView HyperLink;

    String smob,sotp;

    ProgressDialog pd;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer__otp_verify);

        fotp=(EditText)findViewById(R.id.fotp);

        fotpbtn=(Button) findViewById(R.id.fotpbtn);


        HyperLink = (TextView)findViewById(R.id.fresendotp);



        smob=getIntent().getStringExtra("mob");

       // Toast.makeText(this, "" + smob, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "" + smob, Toast.LENGTH_SHORT).show();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        Text = Html.fromHtml("<br />" +
                "<a href='https://www.android-examples.com//'>Resend OTP</a>");

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);


        fotpbtn.setOnClickListener(this);

       /* fotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Farmer_OtpVerify.this, Farmer_login.class);
                startActivity(intent);
            }
        });*/

    }

    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {



            new AsyncOTP().execute(smob,sotp);


           // Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();

            //process the data further
        }
    }

    @Override
    public void onClick(View view) {
        if (view == fotpbtn) {
            //submitForm();
            sotp= fotp.getText().toString();
            new AsyncOTP().execute(smob,sotp);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).create().show();

    }




    class AsyncOTP extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Farmer_OtpVerify.this);
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
            result = new HTTPRequestDAO().Farmerotp(params[0], params[1]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    Toast.makeText(Farmer_OtpVerify.this, "Verification Successful!  ", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(Farmer_OtpVerify.this,Farmer_login.class);
                    startActivity(i);

                }

                else{
                    Toast.makeText(Farmer_OtpVerify.this, "Inavild OTP", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Farmer_OtpVerify.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
