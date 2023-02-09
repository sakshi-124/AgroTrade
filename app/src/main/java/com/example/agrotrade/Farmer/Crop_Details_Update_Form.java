package com.example.agrotrade.Farmer;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


public class Crop_Details_Update_Form extends AppCompatActivity implements View.OnClickListener {

    EditText cname,quantity,price_kg,descr;
    TextView id;
    Spinner category;
    Button add,selectimg;
    private AwesomeValidation awesomeValidation;
    ProgressDialog pd;

    AlertDialog alertDialog;
    String f_id,crop_name,cat_name,qty,price,description;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String NameFam = "nameKey";
    SharedPreferences sharedpreferences;

    ImageView imageView;




    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;
    JSONObject object;


    //Uri to store the image uri
    private Uri filePath;

    String intname,intid,intprice,intqty,intdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop__selling__form);



        object= new JSONObject();




        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        f_id=sharedpreferences.getString(NameFam, "");

        category=(Spinner)findViewById(R.id.cat_name);
        cname = (EditText)findViewById(R.id.crop_name);
        quantity=(EditText)findViewById(R.id.qty);
        price_kg=(EditText)findViewById(R.id.price);
        descr=(EditText)findViewById(R.id.description);
        add=(Button)findViewById(R.id.add);
        selectimg=(Button)findViewById(R.id.selectimg);
        imageView=(ImageView) findViewById(R.id.img);
        //id=(TextView)findViewById(R.id.product_id);


        Intent  intent=getIntent();
        intname=intent.getStringExtra("name");
        intid=intent.getStringExtra("id");
        intdesc=intent.getStringExtra("description");
        intprice=intent.getStringExtra("price");
        intqty=intent.getStringExtra("qty");

        cname.setText(intname);
        quantity.setText(intqty);
      //  id.setText(intid);
        price_kg.setText(intprice);
        descr.setText(intdesc);


        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Vegetable");
        arrayList.add("Fruit");
        arrayList.add("Oil Seed");
        arrayList.add("Grain");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(arrayAdapter);


        add.setOnClickListener(this);
        selectimg.setOnClickListener(this);

        awesomeValidation.addValidation(this, R.id.crop_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.qty, "^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)$", R.string.editqtyerr);
        awesomeValidation.addValidation(this, R.id.price, "^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)$", R.string.editpriceerr);


    }
    private void submitForm() throws JSONException {

        if (awesomeValidation.validate() ) {

            //& fpass.getText().toString().equals(fcpass.getText().toString())

            cat_name=category.getSelectedItem().toString();
            crop_name=cname.getText().toString();
            qty=quantity.getText().toString();
            price=price_kg.getText().toString();
            description=descr.getText().toString();
//            qty=quantity.getText().toString();
//            price=price_kg.getText().toString();
//            description=descr.getText().toString();

      //  object.put("id",id);
        object.put("crop_name",crop_name);
        object.put("qty",qty);
        object.put("price",price);
        object.put("description",description);

       // String js= " { " +"crop_name" + ":" + " """ crop_name +

           // new AsyncCropSelling().execute(object.toString(),"product","crop_name",intname);
        new AsyncCropSelling().execute(object.toString(),"product","id",intid);
            //process the data further
        }
//        else {
//            Toast.makeText(this, "Password Does not match", Toast.LENGTH_SHORT).show();
//        }

        //first validate the form then move ahead
    }


    public void uploadMultipart() {
        //getting name for the image
        String name = "myfile";

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
        public void onClick(View view) {
            if (view == add) {
                try {
                    submitForm();
                } catch (JSONException e) {
                    Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
                }
            }
            if (view == selectimg) {
                showFileChooser();
            }

        }


    class AsyncCropSelling extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Crop_Details_Update_Form.this);
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

            //change for selling of crop
            result = new HTTPRequestDAO().UpdateProduct(params[0], params[1],params[2],params[3]);
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

                    Toast.makeText(Crop_Details_Update_Form.this, "Successfully data sent", Toast.LENGTH_LONG).show();
                    /*Intent i= new Intent(ExpenseManager.this,Farmer_OtpVerify.class);
                    i.putExtra("mob",smob);


                     Intent i=new Intent(this,AllMasterExpensesActivity.class);
//            startActivity(i);

                    startActivity(i);*/

                    Intent i=new Intent(Crop_Details_Update_Form.this,All_Crop_Selling.class);
                    startActivity(i);

                }
               /* else if (mdm.getData() == 500) {

                    Toast.makeText(Farmer_registration.this, "Mobile no already exist!", Toast.LENGTH_LONG).show();


                }*/
                else{
                    Toast.makeText(Crop_Details_Update_Form.this, "Something went wrong", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Crop_Details_Update_Form.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }


}
