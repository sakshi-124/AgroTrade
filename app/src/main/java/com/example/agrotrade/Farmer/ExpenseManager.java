package com.example.agrotrade.Farmer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Calendar;

public class ExpenseManager extends AppCompatActivity implements View.OnClickListener {

    EditText ddate, ff_cost, pp_cost, hhalf_no_worker, hhalf_sal, ffull_no_worker, ffull_sal, eextra_cost, eextra_note, ttotal_income;
    TextView result;
    Button generate,save;
    private AwesomeValidation awesomeValidation;
    ProgressDialog pd;

    AlertDialog alertDialog;

    DatePickerDialog datePickerDialog;
    String f_id, date, f_cost, p_cost, half_no_worker, half_sal, full_no_worker, full_sal, extra_cost, extra_note, total_income;
    double half_day, full_day, ans;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    String data,s;

    JSONObject obj;

    String pro_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_manager);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Intent intent=getIntent();
        pro_id=intent.getStringExtra("id");



        f_id=sharedpreferences.getString(Name, "");

        ddate = (EditText) findViewById(R.id.date);
        ff_cost = (EditText) findViewById(R.id.f_cost);
        pp_cost = (EditText) findViewById(R.id.p_cost);
        hhalf_no_worker = (EditText) findViewById(R.id.half_no_worker);
        ffull_no_worker = (EditText) findViewById(R.id.full_no_worker);
        hhalf_sal = (EditText) findViewById(R.id.half_sal);
        ffull_sal = (EditText) findViewById(R.id.full_sal);
        eextra_cost = (EditText) findViewById(R.id.extra_cost);
        eextra_note = (EditText) findViewById(R.id.extra_note);
        ttotal_income = (EditText) findViewById(R.id.total_income);
        generate = (Button) findViewById(R.id.report);
        save = (Button) findViewById(R.id.save);
        result = (TextView) findViewById(R.id.result);

        generate.setOnClickListener(this);
        save.setOnClickListener(this);
        ddate.setOnClickListener(this);

       /* generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCalculations();
            }
        });*/
        awesomeValidation.addValidation(this, R.id.f_cost, "^.*[0-9]+$", R.string.experror);
        awesomeValidation.addValidation(this, R.id.p_cost, "^.*[0-9]+$", R.string.experror);
        awesomeValidation.addValidation(this, R.id.half_no_worker, "^.*[0-9]+$", R.string.experror);
        awesomeValidation.addValidation(this, R.id.half_sal, "^.*[0-9]+$", R.string.experror);
        awesomeValidation.addValidation(this, R.id.full_no_worker, "^.*[0-9]+$", R.string.experror);//awesomeValidation.addValidation(this, R.id.f_cost, "^[0-9]\\d{0,7}(\\.\\d{1,4})?$", R.string.experror);
        awesomeValidation.addValidation(this, R.id.full_sal, "^.*[0-9]+$", R.string.experror);
        awesomeValidation.addValidation(this, R.id.total_income, "^.*[0-9]+$", R.string.experror);
    }

    private void TotalEXpense() {
        // I'm assuming you're getting numbers.

        //ff_cost.setText("0");
        /* p_cost.setText("0");
        half_no_worker.setText("0");
        full_no_worker.setText("0");
        half_sal.setText("0");
        full_sal.setText("0");
        extra_cost.setText("0");
        total_income.setText("0"); */

        double f_c = Double.valueOf(ff_cost.getText().toString());
        double p_c = Double.valueOf(pp_cost.getText().toString());
        double half_no_w = Double.valueOf(hhalf_no_worker.getText().toString());
        double full_no_w = Double.valueOf(ffull_no_worker.getText().toString());
        double half_s = Double.valueOf(hhalf_sal.getText().toString());
        double full_s = Double.valueOf(ffull_sal.getText().toString());
        double extra_c = Double.valueOf(eextra_cost.getText().toString());



        ans= ((half_no_w*half_s) + (full_no_w*full_s )+f_c+p_c+extra_c);

        result.setText("The result is: " + ans);

        Toast.makeText(this, "" + ans, Toast.LENGTH_SHORT).show();


        date=ddate.getText().toString();
        f_cost=ff_cost.getText().toString();
        p_cost=pp_cost.getText().toString();
        half_no_worker=hhalf_no_worker.getText().toString();
        half_sal=hhalf_sal.getText().toString();
        full_no_worker=ffull_no_worker.getText().toString();
        full_sal=ffull_sal.getText().toString();
        extra_cost=eextra_cost.getText().toString();
        extra_note=eextra_note.getText().toString();
        total_income=ttotal_income.getText().toString();

        Toast.makeText(this, "pesti: "+p_cost, Toast.LENGTH_SHORT).show();




            // data = "{ 'f_id' : '"+f_id+"' , 'f_cost' : '"+f_cost+"' , 'p_cost' : '"+p_cost+"' , 'half_sal' : '"+half_sal+"' }";

        new AsyncExpense().execute(f_cost,p_cost,half_no_worker,half_sal,full_no_worker,full_sal,extra_cost,extra_note,total_income,date,f_id,pro_id);
//        try {
//
//           obj= new JSONObject(data);
//
//
//           s=obj.toString();
//
//            Toast.makeText(this, "" + s, Toast.LENGTH_SHORT).show();
//
//        } catch (Throwable t) {
//            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
//
//        }
//        new AsyncExpense().execute("expense",s);
//

    }

    private void makeCalculations() {
        // I'm assuming you're getting numbers.

       //ff_cost.setText("0");
        /* p_cost.setText("0");
        half_no_worker.setText("0");
        full_no_worker.setText("0");
        half_sal.setText("0");
        full_sal.setText("0");
        extra_cost.setText("0");
        total_income.setText("0"); */

        double f_c = Double.valueOf(ff_cost.getText().toString());
        double p_c = Double.valueOf(pp_cost.getText().toString());
        double half_no_w = Double.valueOf(hhalf_no_worker.getText().toString());
        double full_no_w = Double.valueOf(ffull_no_worker.getText().toString());
        double half_s = Double.valueOf(hhalf_sal.getText().toString());
        double full_s = Double.valueOf(ffull_sal.getText().toString());
        double extra_c = Double.valueOf(eextra_cost.getText().toString());
        double total_in = Double.valueOf(ttotal_income.getText().toString());


        ans= total_in-((half_no_w*half_s) + (full_no_w*full_s )+f_c+p_c+extra_c);

        result.setText("The result is: " + ans);

        Toast.makeText(this, "" + ans, Toast.LENGTH_SHORT).show();

        //& fpass.getText().toString().equals(fcpass.getText().toString())
        date=ddate.getText().toString();
        f_cost=ff_cost.getText().toString();
        p_cost=pp_cost.getText().toString();
        half_no_worker=hhalf_no_worker.getText().toString();
        half_sal=hhalf_sal.getText().toString();
        full_no_worker=ffull_no_worker.getText().toString();
        full_sal=ffull_sal.getText().toString();
        extra_cost=eextra_cost.getText().toString();
        extra_note=eextra_note.getText().toString();
        total_income=ttotal_income.getText().toString();





       // new AsyncExpense().execute(date,f_cost,p_cost,half_no_worker,half_sal,full_no_worker,full_sal,extra_cost,extra_note,total_income);




        // Do your calculation here.
        // I'm assuming you have inserted the result on a variable called 'result'. Like: double result

    }
    private void submitForm() {

        if (awesomeValidation.validate()) {

            if(ttotal_income.getText().toString().equals("0")){
                alertDialog= new AlertDialog.Builder(this)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Get Expense!")
//set message
                        .setMessage("You're getting total expense only")
//set positive button
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                Toast.makeText(ExpenseManager.this, "calling expense!", Toast.LENGTH_SHORT).show();
                                TotalEXpense();

                            }
                        })
//set negative button
                        .show();

            }
            else {

                makeCalculations();

            }




            //process the data further
        }
        else {
            Toast.makeText(this, "Check All the fields", Toast.LENGTH_SHORT).show();
        }

        //first validate the form then move ahead
    }

    @Override
    public void onClick(View view) {
        if (view == generate ) {
            submitForm();
            //makeCalculations();
        }
        if(view == save){

            TotalEXpense();
            //Comment it as per
            Intent intent1=new Intent(this, AllMasterExpensesActivity.class);
            intent1.putExtra("f_cost",f_cost);
                intent1.putExtra("p_cost",p_cost);
                intent1.putExtra("half_no_worker",half_no_worker);
                intent1.putExtra("half_sal",half_sal);
                intent1.putExtra("full_no_worker",full_no_worker);
                intent1.putExtra("full_sal",full_sal);
                intent1.putExtra("extra_cost",extra_cost);
                intent1.putExtra("extra_note",extra_note);
                intent1.putExtra("total_income",total_income);
                startActivity(intent1);
           // Intent i=new Intent(this,AllMasterExpensesActivity.class);
          //  startActivity(i);
        }
        if(view==ddate){
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

            datePickerDialog= new DatePickerDialog(ExpenseManager.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    ddate.setText(i2 + "/" + (i1 + 1) + "/" + i);

                }
            },mYear,mMonth,mDay);
            datePickerDialog.getDatePicker().setSpinnersShown(true);
            datePickerDialog.getDatePicker().setLayoutMode(1);
            datePickerDialog.getDatePicker().setCalendarViewShown(false);
            datePickerDialog.show();
        }

    }

    class AsyncExpense extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(ExpenseManager.this);
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
            result = new HTTPRequestDAO().AddExpense(params[0], params[1],params[2],params[3],params[4],params[5],params[6],params[7],params[8],params[9],params[10],params[11]);
           // result = new HTTPRequestDAO().insertData(params[0], params[1]);
            return result;
        }

        protected void onPostExecute(String result) {
            pd.dismiss();

            Log.d("result", "" + result);
            Gson gson = new Gson();
            try {

                LoginModel mdm = gson.fromJson(result, LoginModel.class);

                if (mdm.getData() == 200) {

                    Toast.makeText(ExpenseManager.this, "Successfully data sent", Toast.LENGTH_LONG).show();
                    /*Intent i= new Intent(ExpenseManager.this,Farmer_OtpVerify.class);
                    i.putExtra("mob",smob);


                     Intent i=new Intent(this,AllMasterExpensesActivity.class);
//            startActivity(i);

                    startActivity(i);*/

                    Intent i=new Intent(ExpenseManager.this, AllMasterExpensesActivity.class);
           startActivity(i);

                }
               /* else if (mdm.getData() == 500) {

                    Toast.makeText(Farmer_registration.this, "Mobile no already exist!", Toast.LENGTH_LONG).show();


                }*/
                else{
                    Toast.makeText(ExpenseManager.this, "Something went wrong", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(ExpenseManager.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
