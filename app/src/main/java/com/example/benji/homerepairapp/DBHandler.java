package com.example.benji.homerepairapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users.db";

    //USERS TABLE
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "userid";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FNAME = "firstName";
    public static final String COLUMN_LNAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_SERVICEPROVIDER = "serviceProvider";
    public static final String COLUMN_SPInfo = "SPInfo";

    //SERVICE PROVIDER INFO TABLE
    public static final String TABLE_SPInfo = "SPINFO";
    public static final String COLUMN_SPINFOID = "SPInfoId";
    public static final String COLUMN_COMPANYNAME = "companyName";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LICENCED = "licenced";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_FNAME + " TEXT," + COLUMN_LNAME
                + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONE + " TEXT," + COLUMN_SERVICEPROVIDER + " TEXT," + COLUMN_SPInfo + " INTEGER," + " FOREIGN KEY(SPInfo) REFERENCES SPInfoID " + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_SPInfo_TABLE = "CREATE TABLE " +
                TABLE_SPInfo + "("
                + COLUMN_SPINFOID + " INTEGER PRIMARY KEY," + COLUMN_COMPANYNAME
                + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_LICENCED + " BOOLEAN" +")";
        db.execSQL(CREATE_SPInfo_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPInfo);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Integer sp = 0;
        if (user.getClass() == ServiceProvider.class) { //checks if user is of type ServiceProvider
            sp = 1;
        }
         else if (user.getClass() == Admin.class){ //checks if user is of type Admin
            sp = 2;
        }
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FNAME, user.getfName());
        values.put(COLUMN_LNAME, user.getlName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE, user.getPhone());
        values.put(COLUMN_SERVICEPROVIDER, sp.toString());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User findUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";
        Cursor cursor = db.rawQuery(query, null);

        User user;

        if (cursor.moveToFirst()) {

            user = null;

            if (Integer.parseInt(cursor.getString(7)) == 1) {
                user = new ServiceProvider(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
            } if (Integer.parseInt(cursor.getString(7)) == 2) {
                user = new Admin(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
            } if (Integer.parseInt(cursor.getString(7)) == 0) { //checks if the account is of type homeowner
                user = new Homeowner(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6));
            }
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    public boolean deleteUser(String username, String password) {
        boolean result = false;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void addSPInfo(String username, String password, String companyName, String description, Boolean licenced){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANYNAME, companyName);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LICENCED, licenced);
        db.insert(TABLE_SPInfo, null, values);

        //THIS QUERY FIND THE PRIMARY KEY OF THE NEWLY ADDED INFO FOR THE SERVICE PROVIDER
        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_COMPANYNAME + " = \"" + companyName + "\"" + " AND " + COLUMN_DESCRIPTION + " = \"" + description + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Integer id = null;

        //GETS THE ID
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }

        //FIND THE ROW IN THE USERS TABLE OF THE MATCHING SERVICE PROVIDER
        query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";
        cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            ContentValues values2 = new ContentValues();
            values.put(COLUMN_SPINFOID, id);
        }
        db.close();
    }

    public Cursor getDBContents(){
        SQLiteDatabase dB = this.getWritableDatabase();
        Cursor users = dB.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        return users;
    }

    public void clearUserTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
    }

    public void clearSPInfoTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPInfo,null,null);
    }

}
