package com.example.agrotrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotrade.Model.Crop_Info;
import com.squareup.picasso.Picasso;

public class All_Crop_Info_Show extends AppCompatActivity {

    TextView textViewSeason,textViewName,textViewIrrigation,textViewVarieties,textViewSoliType,textViewPlantMaterial,textViewSpacing,textViewHarvest ;
    ImageView imgViewimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__crop__info__show);

        textViewSeason=(TextView)findViewById(R.id.season);
        textViewName=(TextView)findViewById(R.id.name);
        textViewIrrigation=(TextView)findViewById(R.id.irrigation);
        textViewVarieties=(TextView)findViewById(R.id.varieties);
        textViewSoliType=(TextView)findViewById(R.id.soil_type);
        textViewPlantMaterial=(TextView)findViewById(R.id.plant_material);
        textViewSpacing=(TextView)findViewById(R.id.spacing);
        textViewHarvest=(TextView)findViewById(R.id.harvesting);
        imgViewimg=(ImageView)findViewById(R.id.img);
        Intent intent=getIntent();

        String name=intent.getStringExtra("name");
        String season=intent.getStringExtra("season");
        String irrigation=intent.getStringExtra("irrigation");
        String varieties=intent.getStringExtra("varieties");
        String soil_type=intent.getStringExtra("soil_type");

        Toast.makeText(this, "" + soil_type, Toast.LENGTH_SHORT).show();
        String plant_material=intent.getStringExtra("plant_material");
        String spacing=intent.getStringExtra("spacing");
        String harvesting=intent.getStringExtra("harvesting");
        String img=intent.getStringExtra("img");
        //textViewName.setText(a);
        //textViewCity.setText(c);
        //textViewMob.setText(b);
        textViewName.setText(name);
        textViewSeason.setText(season);
        textViewIrrigation.setText(irrigation);
        textViewVarieties.setText(varieties);
        textViewSoliType.setText(soil_type);
        textViewPlantMaterial.setText(plant_material);
        textViewSpacing.setText(spacing);
        textViewHarvest.setText(harvesting);

        Picasso.get().load(img).placeholder(R.mipmap.ic_launcher)
                .resize(200,200)
                .into(imgViewimg);
       // Crop_Info crop_info  =(Crop_Info) adapterView.getItemAtPosition(i);


    }
}
