package com.example.benji.homerepairapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class ServiceDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Services.db";
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SERVICENAME = "serviceName";
    public static final String COLUMN_RATE = "rate";

    public ServiceDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SERVICES_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SERVICENAME
                + " TEXT," + COLUMN_RATE + " DOUBLE" + ")";
        db.execSQL(CREATE_SERVICES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    public void addService(Service service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICENAME, service.getServiceName());
        values.put(COLUMN_RATE, service.getRate());
        db.insert(TABLE_SERVICES, null, values);
        db.close();
    }

    public Service findService(String serviceName){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + " = \"" + serviceName+ "\"";
        Cursor cursor = db.rawQuery(query, null);

        Service service;
        if (cursor.moveToFirst()){
            service = new Service(cursor.getString(1),cursor.getDouble(2));
        } else {
            service = null;
        }
        db.close();
        return service;
    }

    public Service findServicebyRate(Double rate){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " +
                COLUMN_RATE + " = \"" + rate + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Service service;
        if (cursor.moveToFirst()){
            service = new Service(cursor.getString(1),cursor.getDouble(2));
        } else {
            service = null;
        }
        db.close();
        return service;
    }

    public boolean deleteService(String serviceName) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " +
                COLUMN_SERVICENAME + " = \"" + serviceName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_SERVICES, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Cursor getDBContents(){
        SQLiteDatabase dB = this.getWritableDatabase();
        Cursor services = dB.rawQuery("SELECT * FROM " + TABLE_SERVICES, null);
        return services;
    }

}
