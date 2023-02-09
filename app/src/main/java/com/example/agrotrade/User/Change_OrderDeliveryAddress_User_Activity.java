package com.example.agrotrade.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.agrotrade.PlaceOrder_Activity;
import com.example.agrotrade.R;

public class Change_OrderDeliveryAddress_User_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText delivery_address,city,town,pincode,state;
    private AwesomeValidation awesomeValidation;

    Button save;

    String all,deladd,scity,stown,spin,sstate,amount,order;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__order_delivery_address_user);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        delivery_address=(EditText)findViewById(R.id.delivery_address);
        city=(EditText)findViewById(R.id.city);
        town=(EditText)findViewById(R.id.town);
        pincode=(EditText)findViewById(R.id.pincode);
        state=(EditText)findViewById(R.id.state);
        save=(Button) findViewById(R.id.save_address);



        awesomeValidation.addValidation(this, R.id.delivery_address, "^[#.0-9a-zA-Z\\s,-]+$", R.string.aerror);
        awesomeValidation.addValidation(this, R.id.city, "^[a-zA-Z]+$", R.string.cerror);
        awesomeValidation.addValidation(this, R.id.town, "^[a-zA-Z]+$", R.string.terror);
        awesomeValidation.addValidation(this, R.id.state, "^[a-zA-Z]+$", R.string.serror);
        awesomeValidation.addValidation(this, R.id.pincode, "^[1-9][0-9]{5}$", R.string.perror);
        Intent intent=getIntent();
        amount=intent.getStringExtra("amount");
        order=intent.getStringExtra("order");

        save.setOnClickListener(this);

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               deladd=delivery_address.getText().toString();
//               scity=city.getText().toString();
//               stown=town.getText().toString();
//               spin=pincode.getText().toString();
//               sstate=state.getText().toString();
//
//               all= deladd + " " + scity + " " + stown + " " +
//                       spin + " " +sstate;
//
//
//                Toast.makeText(Change_OrderDeliveryAddress_User_Activity.this, "" + all, Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(Change_OrderDeliveryAddress_User_Activity.this, User_PlaceOrder_Activity.class);
//                intent.putExtra("address",all);
//                intent.putExtra("amount",amount);
//                intent.putExtra("order",order);
//                startActivity(intent);
//
//            }
//        });


    }




    private void submitForm(){

        if (awesomeValidation.validate()) {


            deladd = delivery_address.getText().toString();
            scity = city.getText().toString();
            stown = town.getText().toString();
            spin = pincode.getText().toString();
            sstate = state.getText().toString();

            all = deladd + " " + scity + " " + stown + " " +
                    spin + " " + sstate;


            Toast.makeText(Change_OrderDeliveryAddress_User_Activity.this, "" + all, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Change_OrderDeliveryAddress_User_Activity.this, User_PlaceOrder_Activity.class);
            intent.putExtra("address", all);
            intent.putExtra("amount", amount);
            intent.putExtra("order", order);
            startActivity(intent);
        }

    }

    public void onClick(View view) {

        if (view == save) {
            submitForm();
        }

    }
}
