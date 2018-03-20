package com.anonimouse.companyproject1.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import com.anonimouse.companyproject1.Model.UserInfoObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronald on 12/7/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    private static final String DB_NAME = "db_banktranctions";

    // table_name
    private static final String DB_TABLE_USERS_INFO = "tbl_userinfo";

    // columns
    private static final String DB_COLUMN_FIRST_NAME = "first_name";
    private static final String DB_COLUMN_LAST_NAME = "last_name";
    private static final String DB_COLUMN_AGE = "age";
    private static final String DB_COLUMN_ADDRESS = "address";
    private static final String DB_COLUMN_USERNAME = "username";
    private static final String DB_COLUMN_PASSWORD = "password";

    private Context mContext;


    // constructor
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        Log.d("Database Operations","Database Created");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableUserInfo = "CREATE TABLE " + DB_TABLE_USERS_INFO
                + " (id INTEGER PRIMARY KEY, "
                + DB_COLUMN_FIRST_NAME + " TEXT, "
                + DB_COLUMN_LAST_NAME + " TEXT, "
                + DB_COLUMN_ADDRESS + " TEXT, "
                + DB_COLUMN_USERNAME + " TEXT, "
                + DB_COLUMN_PASSWORD + " TEXT, "
                + DB_COLUMN_AGE + " TEXT);";

        db.execSQL(createTableUserInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_USERS_INFO);
//        Log.d("fuck database", "meron"+ 1);
        onCreate(db);
    }


    public void SaveUsername(String firstname, String lastname, String age, String address,String username,String password,Context context){
        ContentValues cv = new ContentValues();
        cv.put(DB_COLUMN_FIRST_NAME, firstname);
        cv.put(DB_COLUMN_LAST_NAME, lastname);
        cv.put(DB_COLUMN_AGE, age);
        cv.put(DB_COLUMN_ADDRESS, address);
        cv.put(DB_COLUMN_USERNAME, username);
        cv.put(DB_COLUMN_PASSWORD, password);
        this.getWritableDatabase().insert(DB_TABLE_USERS_INFO, null, cv);
        Toast.makeText(context, "Registration Success", Toast.LENGTH_SHORT).show();
        Log.d("Save database", "saved");

    }

    public void UpdateUsers(String firstname,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" UPDATE " + DB_TABLE_USERS_INFO + " SET first_name = '"+firstname+"'  WHERE id = '"+id+"' ");
        Log.d("Update", "User info");
        this.getWritableDatabase().close();
    }

    public void DeleteUsers(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Delete from " + DB_TABLE_USERS_INFO + " where id = '"+id+"' ");
        Log.d("Deleted", "users info");
        db.close();
    }

//    public void walang return

//    public List<UserInfo> getUserDetails(){
//        List<UserInfo> mlistUserInfo = new ArrayList<>();
//        Cursor c = this.getReadableDatabase().rawQuery("Select * from " + DB_TABLE_USERS_INFO, null);
//        if(c.getCount() > 0){
//            c.moveToFirst();
//            while (!c.isAfterLast()){
//                UserInfo userInfo = new UserInfo();
//                userInfo.setFirstname(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_FIRST_NAME)));
//                userInfo.setLastname(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_LAST_NAME)));
//                userInfo.setAge(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_AGE)));
//                userInfo.setAddress(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_ADDRESS)));
//                mlistUserInfo.add(userInfo);
//                c.moveToNext();
//            }
//        }
//        c.close();
//        this.getReadableDatabase().close();
//        return mlistUserInfo;
//    }

    public  List<UserInfoObject> Userlogin(String username,String password, Context context ){
        List<UserInfoObject> mlistusers = new ArrayList<>();
        Cursor c = this.getReadableDatabase().rawQuery("select * from " + DB_TABLE_USERS_INFO + " where username = '"+username+"' and password = '"+password+"' ", null );
        if(c.getCount() > 0 ){
            c.moveToFirst();
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
            while (!c.isAfterLast()){
                UserInfoObject userInfoObject = new UserInfoObject();
                userInfoObject.setFirstname(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_FIRST_NAME)));
                userInfoObject.setLastname(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_LAST_NAME)));
                userInfoObject.setAge(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_AGE)));
                userInfoObject.setAddress(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_ADDRESS)));

                Log.d("fuck", "" + userInfoObject.getFirstname().toString());
                mlistusers.add(userInfoObject);
                c.moveToNext();
            }
        }

//        UserInfoObject userInfoObject = new UserInfoObject();
//        Log.d("results ",  " " + userInfoObject.getFirstname().toString() + "" + userInfoObject.getLastname() + "");
        c.close();
        this.getReadableDatabase().close();
        return mlistusers;
    }
//
//
//    public List<UserInfo> userlogin(String username, String password,Context context){
//        List<UserInfo> mlistuserinfo = new ArrayList<>();
//        Cursor c = this.getReadableDatabase().rawQuery("Select * from " + DB_TABLE_USERS_INFO + "where username = '"+username+"' and password = '"+password+"'",  null  );
//        if(c.getCount() > 0){
//            c.moveToNext();
//            while (!c.isAfterLast()) {
//                UserInfo userInfo = new UserInfo();
//                userInfo.setFirstname(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_FIRST_NAME)));
//                userInfo.setLastname(c.getString(c.getColumnIndexOrThrow(DB_COLUMN_LAST_NAME)));
//                mlistuserinfo.add(userInfo);
//                c.moveToNext();
//            }
//            UserInfo userInfo = new UserInfo();
//            Toast.makeText(context, " Account Details " + userInfo.getFirstname().toString() +"  " + userInfo.getLastname().toString(), Toast.LENGTH_SHORT).show();
//            c.moveToNext();
//
//
//        }
//        c.close();
//        this.getReadableDatabase().close();
//        return mlistuserinfo;
//    }

    // show details
//    public List<OrderObject> getOrderDetails() {
//        List<OrderObject> mListOrders = new ArrayList<>();
//
//        Cursor c = this.getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_ORDERS, null);
//
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            while(!c.isAfterLast()) {
//                OrderObject orderObject = new OrderObject();
//                orderObject.setId(c.getInt(c.getColumnIndexOrThrow(DB_KEY_ID)));
//                orderObject.setBarcode(c.getString(c.getColumnIndexOrThrow(DB_KEY_BARCODE)));
//                orderObject.setProductname(c.getString(c.getColumnIndexOrThrow(DB_KEY_PRODUCTNAME)));
//                orderObject.setQuantity(c.getString(c.getColumnIndexOrThrow(DB_KEY_QUANTITY)));
//                orderObject.setPrice(c.getString(c.getColumnIndexOrThrow(DB_KEY_PRICE)));
//                orderObject.setOriginalprice(c.getString(c.getColumnIndexOrThrow(DB_KEY_ORIGINAL_PRICE)));
//
//                Log.d("total price ", ""+orderObject.getPrice());
//
////
//                mListOrders.add(orderObject);
//                c.moveToNext();
//            }
//        }
//        c.close();
//        this.close();
//        return mListOrders;
//    }





}
