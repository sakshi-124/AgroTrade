package com.example.agrotrade.Farmer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.agrotrade.Helpers.HTTPRequestDAO;
import com.example.agrotrade.Model.LoginModel;
import com.example.agrotrade.R;
import com.google.gson.Gson;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;


public class Crop_Selling_Form extends AppCompatActivity implements View.OnClickListener {

    EditText cname,quantity,price_kg,descr;
    Spinner category;
    Button add,selectimg;
    private AwesomeValidation awesomeValidation;
    ProgressDialog pd;

    AlertDialog alertDialog;
    String f_id,crop_name,cat_name,qty,price,description,d;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String NameFam = "nameKey";
    SharedPreferences sharedpreferences;

    ImageView imageView;

    String f;




    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap,bitmap2;



    //Uri to store the image uri
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop__selling__form);








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


        pd=new ProgressDialog(this);

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

    public void MyWeb(View view)
    {
        openUrl("https://www.kisaanhelpline.com/crops/mandi_bhav");
    }

    public  void openUrl(String url)
    {
        Uri uri=Uri.parse(url);
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(i);
    }
    private void submitForm() {

        if (awesomeValidation.validate() ) {

            //& fpass.getText().toString().equals(fcpass.getText().toString())
            cat_name=category.getSelectedItem().toString();
            crop_name=cname.getText().toString();
            qty=quantity.getText().toString();
            price=price_kg.getText().toString();
            description=descr.getText().toString();



            uploadMultipart();



           // new AsyncCropSelling().execute(f_id,cat_name,crop_name,qty,price,description,f);

            //process the data further
        }
        else {
            Toast.makeText(this, "Password Does not match", Toast.LENGTH_SHORT).show();
        }

        //first validate the form then move ahead
    }


    public void uploadMultipart() {




        pd.setMessage("Please Wait....");

        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(true);
        pd.show();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                    if (pd.isShowing())
                        pd.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();










        //getting name for the image
        String name = "myfile";

        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();


//            param.add(new BasicNameValuePair("cat_name", cat_name));
//            param.add(new BasicNameValuePair("crop_name", crop_name));
//            param.add(new BasicNameValuePair("qty", qty));
//            param.add(new BasicNameValuePair("price", price));
//            param.add(new BasicNameValuePair("description", description));
//            param.add(new BasicNameValuePair("f_id", f_id));
//            param.add(new BasicNameValuePair("image", img));

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("cat_name", cat_name) //Adding text parameter to the request
                    .addParameter("crop_name", crop_name) //Adding text parameter to the request
                    .addParameter("qty", qty) //Adding text parameter to the request
                    .addParameter("price", price) //Adding text parameter to the request
                    .addParameter("description", description) //Adding text parameter to the request
                    .addParameter("f_id", f_id) //Adding text parameter to the request
                   //Adding text parameter to the request
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

            f = getPath(filePath);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                bitmap2 = BitmapFactory.decodeFile(f);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);

                Toast.makeText(this, "" + f, Toast.LENGTH_SHORT).show();


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
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
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
               submitForm();
            }
            if (view == selectimg) {
                showFileChooser();
            }


        }


    class AsyncCropSelling extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Crop_Selling_Form.this);
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
            result = new HTTPRequestDAO().AddCropSelling(params[0], params[1],params[2],params[3],params[4],params[5],params[6]);
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

                    Toast.makeText(Crop_Selling_Form.this, "Successfully data sent", Toast.LENGTH_LONG).show();
                    /*Intent i= new Intent(ExpenseManager.this,Farmer_OtpVerify.class);
                    i.putExtra("mob",smob);


                     Intent i=new Intent(this,AllMasterExpensesActivity.class);
//            startActivity(i);

                    startActivity(i);*/

                    Intent i=new Intent(Crop_Selling_Form.this,All_Crop_Selling.class);
                    startActivity(i);

                }
               /* else if (mdm.getData() == 500) {

                    Toast.makeText(Farmer_registration.this, "Mobile no already exist!", Toast.LENGTH_LONG).show();


                }*/
                else{
                    Toast.makeText(Crop_Selling_Form.this, "Something went wrong", Toast.LENGTH_LONG).show();

                }

            } catch (Exception e) {
                Toast.makeText(Crop_Selling_Form.this, "invalid server", Toast.LENGTH_LONG).show();
            }
        }

    }


}
