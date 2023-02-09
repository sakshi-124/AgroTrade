package com.example.agrotrade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agrotrade.Farmer.ExpenseManager;
import com.example.agrotrade.Farmer.Farmer_login;
import com.example.agrotrade.Farmer.HomeActivity;
import com.example.agrotrade.User.HomeActivity_User;
import com.example.agrotrade.User.User_login;


public class select_user_type extends AppCompatActivity {
    RadioGroup radGrp;
    RadioButton farmer,cust;
    Button next;

    String Nameuser,Namefarmer;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Name1 = "nameKeyUser";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        radGrp=(RadioGroup)findViewById(R.id.radgrp);
        farmer=(RadioButton)findViewById(R.id.farmer);
        cust=(RadioButton)findViewById(R.id.cust);
        next=(Button)findViewById(R.id.next);





        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Nameuser=sharedpreferences.getString(Name, "");
        Namefarmer=sharedpreferences.getString(Name1, "");

        if(!Nameuser.isEmpty()){

            Intent intent=new Intent(select_user_type.this, HomeActivity.class);
            startActivity(intent);

        }else if(!Namefarmer.isEmpty()) {
            Intent intent=new Intent(select_user_type.this, HomeActivity_User.class);
            startActivity(intent);
        }
        else {

            Toast.makeText(this, "Select User", Toast.LENGTH_SHORT).show();

        }



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (farmer.isChecked())
                {
                    Toast.makeText(select_user_type.this, "Farmer Selected", Toast.LENGTH_SHORT).show();
                    Intent i1=new Intent(select_user_type.this, Farmer_login.class);
                    startActivity(i1);
                }
                else if(cust.isChecked())
                {
                    Intent i2=new Intent(select_user_type.this, User_login.class);
                    startActivity(i2);

                }

                else {
                    Toast.makeText(select_user_type.this, "Please Select ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
