package com.example.agrotrade.User;

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

public class User_registration extends AppCompatActivity implements View.OnClickListener  {
    EditText uuname,uadd,upass,umob,ucpass;
    Button ureg;


    ProgressDialog pd;

    String sname,sadd,smob,spass,susername;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        //setContentView(R.layout.activity_user_registration);


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        uuname = (EditText) findViewById(R.id.uuname);
        upass = (EditText) findViewById(R.id.upass);
        uadd = (EditText) findViewById(R.id.uadd);
        ucpass=(EditText)findViewById(R.id.ucpass);
        umob=(EditText)findViewById(R.id.umob);
        ureg = (Button) findViewById(R.id.ureg);


        awesomeValidation.addValidation(this, R.id.uuname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.upass, "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", R.string.passerror);
        awesomeValidation.addValidation(this, R.id.ucpass, "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", R.string.passerror);
        awesomeValidation.addValidation(this, R.id.uadd, "^[#.0-9a-zA-Z\\s,-]+$", R.string.address);
        awesomeValidation.addValidation(this, R.id.umob, "^[0-9]{2}[0-9]{8}$", R.string.moberror);



        ureg.setOnClickListener(this);
        /*    @Override
            public void onClick(View view) {

                Intent intent=new Intent(User_registration.this,User_OtpVerify.class);
                startActivity(intent);
            }
        });*/

    }



    private void submitForm() {

        if (awesomeValidation.validate() & upass.getText().toString().equals(ucpass.getText().toString())) {
            //Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();
            //upass.getText().toString().equals(ucpass.getText().toString())
                sadd=uadd.getText().toString();
                smob=umob.getText().toString();
                sname=uuname.getText().toString();
                spass=upass.getText().toString();

                new  AsyncRegistration().execute(sadd,smob,spass,sname,"20.199","34.876");
            //process the data further
        }

        else {
            Toast.makeText(this, "Password Does Not Match!", Toast.LENGTH_SHORT).show();
        }


        //first validate the form then move ahead
    }

    @Override
    public void onClick(View view) {

        if (view == ureg) {
            submitForm();
        }

    }



//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        finish();
//                    }
//                }).create().show();
//
//    }

        class AsyncRegistration extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(User_registration.this);
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
            result = new HTTPRequestDAO().CustomerRegistration(params[0], params[1],params[2],params[3],params[4],params[5]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    Toast.makeText(User_registration.this, "Successful Registration", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(User_registration.this, User_OtpVerify.class);
                    i.putExtra("mob",smob);

                    startActivity(i);

                }
                else if (mdm.getData() == 500) {

                    Toast.makeText(User_registration.this, "Mobile no already exist!", Toast.LENGTH_LONG).show();


                }
                else if(mdm.getData() == 100){
                    Toast.makeText(User_registration.this, "Something went wrong", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(User_registration.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
