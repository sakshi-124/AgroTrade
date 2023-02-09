package com.example.agrotrade.User.ShoppingCart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.agrotrade.Model.Farmer_ProductModel;
import com.example.agrotrade.Model.User_ProductModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP-pc on 12-Apr-17.
 */

public class SqliteDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "favorite";
    private static final String TABLE_PRODUCTS = "brand";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_BRANDNAME = "brandname";
    private static final String COLUMN_BRANDLOGO = "brandlogo";

    private static final String COLUMN_BRANDABOUT = "brandabout";
    private static final String COLUMN_BRANDID = "brandid";
    private static final String COLUMN_QTY = "quantity";



    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_BRANDNAME + " TEXT,"
                + COLUMN_BRANDID + " TEXT,"
                + COLUMN_BRANDLOGO + " TEXT,"
                + COLUMN_BRANDABOUT + " TEXT,"
                + COLUMN_QTY + " TEXT" +")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);

    }
    public List<User_ProductModel> listProducts(){
        String sql = "select * from " + TABLE_PRODUCTS;
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        List<User_ProductModel> storeProducts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String pro_name = cursor.getString(1);
                String pro_id = cursor.getString(2);
                String img = cursor.getString(3);

                String price = cursor.getString(4);
                String qty = cursor.getString(5);

                storeProducts.add(new User_ProductModel(id, pro_id,pro_name, price,img,qty));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeProducts;
    }
    public void addProduct(User_ProductModel product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BRANDNAME, product.getP_name());
        values.put(COLUMN_BRANDLOGO, product.getImg());
        values.put(COLUMN_BRANDID, product.getPro_id());
        values.put(COLUMN_BRANDABOUT, product.getPrize());
      values.put(COLUMN_QTY, product.getQty());

        //values.put(COLUMN_RES, product.getRes_id());
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
    }


//    public void addMonitor(MonitorFeatureModel product){
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_BRANDNAME, product.getP_name());
//        values.put(COLUMN_BRANDLOGO, product.getImg());
//        values.put(COLUMN_BRANDID, product.getPro_id());
//        values.put(COLUMN_BRANDABOUT, product.getPrize());
//        values.put(COLUMN_QTY, product.getQty());
//        //values.put(COLUMN_RES, product.getRes_id());
//        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(TABLE_PRODUCTS, null, values);
//    }
//
//    public void addRouter(RouterFeatureModel product){
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_BRANDNAME, product.getP_name());
//        values.put(COLUMN_BRANDLOGO, product.getImg());
//        values.put(COLUMN_BRANDID, product.getPro_id());
//        values.put(COLUMN_BRANDABOUT, product.getPrize());
//        values.put(COLUMN_QTY, product.getQty());
//        //values.put(COLUMN_RES, product.getRes_id());
//        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(TABLE_PRODUCTS, null, values);
//    }
//
//
//    public void addPrinter(PrinterFeatureModel product){
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_BRANDNAME, product.getP_name());
//        values.put(COLUMN_BRANDLOGO, product.getImg());
//        values.put(COLUMN_BRANDID, product.getPro_id());
//        values.put(COLUMN_BRANDABOUT, product.getPrize());
//        values.put(COLUMN_QTY, product.getQty());
//        //values.put(COLUMN_RES, product.getRes_id());
//        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(TABLE_PRODUCTS, null, values);
//    }
//
//    public void addLaptop(LaptopFeatureModel product){
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_BRANDNAME, product.getP_name());
//        values.put(COLUMN_BRANDLOGO, product.getImg());
//        values.put(COLUMN_BRANDID, product.getPro_id());
//        values.put(COLUMN_BRANDABOUT, product.getPrize());
//        values.put(COLUMN_QTY, product.getQty());
//        //values.put(COLUMN_RES, product.getRes_id());
//        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(TABLE_PRODUCTS, null, values);
//    }
//
//    public void addHarddisk(HarddiskFeatureModel product){
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_BRANDNAME, product.getP_name());
//        values.put(COLUMN_BRANDLOGO, product.getImg());
//        values.put(COLUMN_BRANDID, product.getPro_id());
//        values.put(COLUMN_BRANDABOUT, product.getPrize());
//        values.put(COLUMN_QTY, product.getQty());
//        //values.put(COLUMN_RES, product.getRes_id());
//        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
//        db.insert(TABLE_PRODUCTS, null, values);
//    }





    public int getSelectedStore(String Storename) {
        String query = "Select * FROM brand where brandname="+"'"+Storename+"'";
        android.database.sqlite.SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.moveToFirst();
        cursor.close();
        return count;
    }
  /*  public void updateProduct(ShopModel product){
        ContentValues values = new ContentValues();
        values.put(COLUMN_BRANDNAME, product.getStoreName());
        values.put(COLUMN_BRANDLOGO, product.getLogo());
        values.put(COLUMN_BRANDID, product.getStoreId());
        values.put(COLUMN_BRANDWEBSITE, product.getShipping());
        values.put(COLUMN_BRANDABOUT, product.getAbout());

        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_PRODUCTS, values, COLUMN_ID    + "    = ?", new String[] { String.valueOf(product.getId())});
    }
    public int findProduct(String name){
        String query = "Select * FROM brand where brandname= 'Flipkart'";
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        //db.;
        ShopModel mProduct = null;
        int id=0;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            id = Integer.parseInt(cursor.getString(0));
            String productName = cursor.getString(1);
            String productId = cursor.getString(2);
            String productlogo = cursor.getString(3);
            String productwebsite = cursor.getString(4);
            String productabout = cursor.getString(5);
            mProduct = new ShopModel(id, productlogo, productabout,productName,productId,productwebsite);
        }
        cursor.close();
        return id;
    }*/
    public void deleteProduct(int id){
        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID    + "    = ?", new String[] { String.valueOf(id)});

    }


    public void delete(){




        android.database.sqlite.SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_PRODUCTS);
        db.close();
      //  db.delete(TABLE_PRODUCTS, COLUMN_ID    + "    = ?", new String[] { String.valueOf(id)});

    }


}
