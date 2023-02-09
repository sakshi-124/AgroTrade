package com.example.agrotrade.User;

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

import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;

public class User_OtpVerify extends AppCompatActivity {
    EditText uotp;
    TextView uresendotp;
    Button uotpbtn;
    Spanned Text;
    TextView HyperLink;

    String smob,sotp;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__otp_verify);

        uotp=(EditText)findViewById(R.id.uotp);

        uotpbtn=(Button) findViewById(R.id.uotpbtn);


        HyperLink = (TextView)findViewById(R.id.uresendotp);

        Text = Html.fromHtml("<br />" +
                "<a href='https://www.android-examples.com//'>Resend OTP</a>");

        HyperLink.setMovementMethod(LinkMovementMethod.getInstance());
        HyperLink.setText(Text);


        smob=getIntent().getStringExtra("mob");

        // Toast.makeText(this, "" + smob, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "" + smob, Toast.LENGTH_SHORT).show();

        uotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sotp=uotp.getText().toString();

                new AsyncOTP().execute(smob,sotp);

//                Intent intent=new Intent(User_OtpVerify.this,User_registration.class);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("If you press 'OK' you are not able to register from this number next time. \n Are you sure you want to exit?")
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
            pd = new ProgressDialog(User_OtpVerify.this);
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
            result = new HTTPRequestDAO().Customerotp(params[0], params[1]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    Toast.makeText(User_OtpVerify.this, "Verification Successful!", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(User_OtpVerify.this, User_login.class);
                    startActivity(i);

                }

                else{
                    Toast.makeText(User_OtpVerify.this, "Inavild OTP", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(User_OtpVerify.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
