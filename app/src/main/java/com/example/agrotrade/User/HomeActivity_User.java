package com.example.agrotrade.User;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.agrotrade.Adapters.PrefManager;
import com.example.agrotrade.All_Categories;
import com.example.agrotrade.Farmer.CustomGrid;
import com.example.agrotrade.LocationData.LocationAddress;
import com.example.agrotrade.LocationData.LocationService;
import com.example.agrotrade.MaintainenceActivity;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.ProductCartActivity;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity_User extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PrefManager session;

    LocationService appLocationService;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKeyUser";
    SharedPreferences sharedpreferences;

    //Integer[] imageId = {R.drawable.ic_launcher_background,R.drawable.saputara1,R.drawable.ahmedabad1, R.drawable.vadodara1, R.drawable.tithalbeach1,R.drawable.giradhodh,R.drawable.giradhodh1,R.drawable.homy2};
    String[] imagesName = {"imag","image1","image2","image3","image4","image5","img6","img7"};

    GridView grid;

    String Name1;
    String[] web = {
            "Buy Crop",
            "Buy",
            "Information",



    } ;
    int[] imageId = {
            R.drawable.sell_homepage,
            R.drawable.farmer_buy_homepage,
            R.drawable.information_homepage,

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homec_user);
        session=new PrefManager(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");


        Toast.makeText(this, "" + Name1, Toast.LENGTH_SHORT).show();


        appLocationService= new LocationService(HomeActivity_User.this);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_slideshow);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);

        toggle.syncState();








        CustomGrid adapter = new CustomGrid(HomeActivity_User.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(HomeActivity_User.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                if (web[+ position].equals("Buy")){

                    Location networkLocation= appLocationService.getLocation (LocationManager.NETWORK_PROVIDER);
                    if (networkLocation!= null)
                    {
                        double latitude = networkLocation.getLatitude();
                        double longitude = networkLocation.getLongitude();
                        LocationAddress locationAddress = new LocationAddress();
                        locationAddress.getAddressFromLocation(latitude , longitude ,
                                getApplicationContext(), new GeocoderHandler());
                    }
//
//                    Intent intent=new Intent(HomeActivity_User.this, Product_DetailShow_Activity.class);
//                    startActivity(intent);


                }

                if (web[+ position].equals("Information")){

                    Intent intent=new Intent(HomeActivity_User.this, All_Categories.class);
                    startActivity(intent);


                }

                 if (web[+ position].equals("Buy Crop")){

                    Intent intent=new Intent(HomeActivity_User.this, All_Crop_Buying_List.class);
                    startActivity(intent);

//                     Intent intent=new Intent(HomeActivity_User.this, All_FertiPesti_Buying.class);
//                     startActivity(intent);


                }




            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();


            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                            finishAffinity();
                        }
                    }).create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement


            if(id==R.id.contact)
            {
                Intent i=new Intent(HomeActivity_User.this, HomeActivity_User.class);
                startActivity(i);
            }
        else if(id==R.id.about)
        {
            Intent i=new Intent(HomeActivity_User.this, HomeActivity_User.class);
            startActivity(i);
        }

            else if(id==R.id.cart)
            {
                Intent i=new Intent(HomeActivity_User.this,ProductCartActivity.class);
                startActivity(i);
            }

        else if(id==R.id.Logout)
            {

                Toast.makeText(this, "Logout Clicked!", Toast.LENGTH_SHORT).show();
                if (!session.isFirstTimeLaunch()){
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.clear().apply();
                    session.setFirstTimeLaunch(true);
                    finish();
                }

            }





        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.home) {


            Intent intent=new Intent(this, HomeActivity_User.class);
            startActivity(intent);
          /*  fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new activity_searchcity()).commit();
*/
            // Handle the camera action
        } else if (id == R.id.crop) {

            Intent i=new Intent(this, All_Crop_Buying_List.class);
            startActivity(i);
            /*fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new GallaryActivity()).commit();*/

        } else if (id == R.id.search) {
            Intent intent=new Intent(this,All_Categories.class);
            startActivity(intent);

        }  else if (id == R.id.account) {
            Intent intent=new Intent(this,MaintainenceActivity.class);
            startActivity(intent);
    }

        else if (id == R.id.Cart) {
            Intent intent=new Intent(this, ProductCartActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.Orders) {
            Intent intent=new Intent(this, All_OrdersShow_Activity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }



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
//            tvAddress.setText(locationAddress);

            Toast.makeText(HomeActivity_User.this, "Address is: " + locationAddress, Toast.LENGTH_SHORT).show();

            //Toast.makeText(appLocationService, "Address is: " + locationAddress, Toast.LENGTH_SHORT).show();
        }
    }



    }

