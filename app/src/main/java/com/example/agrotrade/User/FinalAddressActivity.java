package com.example.agrotrade.User;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotrade.LocationData.LocationAddress;
import com.example.agrotrade.LocationData.LocationService;
import com.example.agrotrade.R;

public class FinalAddressActivity extends AppCompatActivity {

    RadioButton rb1, rb2, rb3;
    LinearLayout l1,l2,l3;

    TextView defaultaddress,addressfromlocation;
    Button Clickme,submit;

    EditText newadd;

    LocationService appLocationService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_address);


        appLocationService= new LocationService(FinalAddressActivity.this);

        rb1 = (RadioButton) findViewById(R.id.rg1_rb1);
        rb2 = (RadioButton) findViewById(R.id.rg1_rb2);
        rb3 = (RadioButton) findViewById(R.id.rg1_rb3);
        l1 = (LinearLayout) findViewById(R.id.li1);
        l2 = (LinearLayout) findViewById(R.id.li2);
        l3 = (LinearLayout) findViewById(R.id.li3);

        Clickme = (Button) findViewById(R.id.locationfinder);
        submit = (Button) findViewById(R.id.Submit);
        addressfromlocation = (TextView) findViewById(R.id.locationaddress);
        defaultaddress = (TextView) findViewById(R.id.defaulttext);


        rb1.setOnClickListener(optionOnClickListener);
        rb2.setOnClickListener(optionOnClickListener);
        rb3.setOnClickListener(optionOnClickListener);



//


        Clickme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submit.setVisibility(View.VISIBLE);
                Location networkLocation= appLocationService.getLocation (LocationManager.NETWORK_PROVIDER);
                if (networkLocation!= null)
                {
                    double latitude = networkLocation.getLatitude();
                    double longitude = networkLocation.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude , longitude ,
                            getApplicationContext(), new FinalAddressActivity.GeocoderHandler());
                }

            }
        });


    }
        View.OnClickListener optionOnClickListener
                = new View.OnClickListener() {

            public void onClick(View v) {
               // TextView tv = (TextView) findViewById(R.id.textview);
                String str = null;

                // you can simply copy the string of clicked button.
                str = ((RadioButton)v).getText().toString();
//                tv.setText(str);

                // to go further with check state you can manually check each radiobutton and find which one is checked.
                if(rb1.isChecked()) {

                    l1.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);

                    // do something
                }
                if(rb2.isChecked()) {
                    l2.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                    // do something

                    l1.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);

                }
                if(rb3.isChecked()) {
                    l3.setVisibility(View.VISIBLE);
                    // do something
                    l2.setVisibility(View.GONE);
                    l1.setVisibility(View.GONE);

                }
            }
        };



    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress= bundle.getString("address");
                    break;
                default:
                    locationAddress= null;
            }
           addressfromlocation.setText(locationAddress);

           // Toast.makeText(HomeActivity_User.this, "Address is: " + locationAddress, Toast.LENGTH_SHORT).show();

            //Toast.makeText(appLocationService, "Address is: " + locationAddress, Toast.LENGTH_SHORT).show();
        }
    }



}
