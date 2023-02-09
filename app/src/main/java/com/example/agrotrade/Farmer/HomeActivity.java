package com.example.agrotrade.Farmer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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
import com.example.agrotrade.All_FertiPesti_Buying;
import com.example.agrotrade.Farmer.ShoppingCart.Farmer_ProductCartActivity;
import com.example.agrotrade.MaintainenceActivity;
import com.example.agrotrade.R;
import com.example.agrotrade.User.ShoppingCart.ProductCartActivity;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PrefManager session;


    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;

    //Integer[] imageId = {R.drawable.ic_launcher_background,R.drawable.saputara1,R.drawable.ahmedabad1, R.drawable.vadodara1, R.drawable.tithalbeach1,R.drawable.giradhodh,R.drawable.giradhodh1,R.drawable.homy2};
    String[] imagesName = {"imag","image1","image2","image3","image4","image5","img6","img7"};

    GridView grid;

    String Name1;
    String[] web = {
            "Sell",
            "Buy",
            "Information",
            "Manage Expenses",


    } ;
    int[] imageId = {
            R.drawable.sell_homepage,
            R.drawable.farmer_buy_homepage,
            R.drawable.information_homepage,
            R.drawable.expense_homepage

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homec);
        session=new PrefManager(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);


        Name1=sharedpreferences.getString(Name, "");




        Toast.makeText(this, "" + Name1, Toast.LENGTH_SHORT).show();


    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_slideshow);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);

        toggle.syncState();

        CustomGrid adapter = new CustomGrid(HomeActivity.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(HomeActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();


                if (web[+ position].equals("Information")){

                    Intent intent=new Intent(HomeActivity.this, All_Categories.class);
                    startActivity(intent);


                }
                 if (web[+ position].equals("Sell")){

                    Intent intent=new Intent(HomeActivity.this,All_Crop_Selling.class);
                    startActivity(intent);


                }
                 if (web[+ position].equals("Buy")){

                    Intent intent=new Intent(HomeActivity.this, All_FertiPesti_Buying.class);
                    startActivity(intent);


                }
                if (web[+ position].equals("Manage Expenses")){

                    Intent intent=new Intent(HomeActivity.this, AllMasterExpensesActivity.class);
                    startActivity(intent);


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
                Intent i=new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(i);
            }
        else if(id==R.id.about)
        {
            Intent i=new Intent(HomeActivity.this,HomeActivity.class);
            startActivity(i);
        }

            else if(id==R.id.cart)
            {
                Intent i=new Intent(HomeActivity.this, Farmer_ProductCartActivity.class);
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


            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
          /*  fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, new activity_searchcity()).commit();
*/
            // Handle the camera action
        } else if (id == R.id.crop) {

            Intent i=new Intent(this, MaintainenceActivity.class);
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
        else if (id == R.id.expenses) {
           // Intent intent=new Intent(this,ExpenseManager.class);
            Intent intent=new Intent(this, AllMasterExpensesActivity.class);
            startActivity(intent);
        }


        else if (id == R.id.Cart) {
            Intent intent=new Intent(this, ProductCartActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.OrdersList) {
            Intent intent=new Intent(this, All_OrderShow_Farmer_Activity.class);
            startActivity(intent);
        }
        else if (id == R.id.ReceivedOrdersList) {
            Intent intent=new Intent(this, All_Received_OrderShow_Farmer_Activity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

