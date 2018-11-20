package com.example.benji.homerepairapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_SERVICEPROVIDER = "serviceProvider";
    public static final String COLUMN_SPInfo = "SPInfo";

    //SERVICE PROVIDER INFO TABLE
    public static final String TABLE_SPINFO = "SPINFO";
    public static final String COLUMN_SPINFOID = "SPInfoId";
    public static final String COLUMN_COMPANYNAME = "companyName";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LICENCED = "licenced";
    public static final String COLUMN_MONDAYSTART = "mondayStart";
    public static final String COLUMN_MONDAYEND = "mondayEnd";
    public static final String COLUMN_TUESDAYSTART = "tuesdayStart";
    public static final String COLUMN_TUESDAYEND = "tuesdayEnd";
    public static final String COLUMN_WEDNESDAYSTART = "wednesdayStart";
    public static final String COLUMN_WEDNESDAYEND = "wednesdayEnd";
    public static final String COLUMN_THURSDAYSTART = "thursdayStart";
    public static final String COLUMN_THURSDAYEND = "thursdayEnd";
    public static final String COLUMN_FRIDAYSTART = "fridayStart";
    public static final String COLUMN_FRIDAYEND = "fridayEnd";
    public static final String COLUMN_SATURDAYSTART = "saturdayStart";
    public static final String COLUMN_SATURDAYEND = "saturdayEnd";
    public static final String COLUMN_SUNDAYSTART = "sundayStart";
    public static final String COLUMN_SUNDAYEND = "sundayEnd";
    public static final String COLUMN_SERVICE1 = "service1";
    public static final String COLUMN_SERVICE2 = "service2";
    public static final String COLUMN_SERVICE3 = "service3";
    public static final String COLUMN_SERVICE4 = "service4";
    public static final String COLUMN_SERVICE5 = "service5";

    //CREATES SERVICES TABLE
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICEID = "serviceID";
    public static final String COLUMN_SERVICENAME = "serviceName";
    public static final String COLUMN_RATE = "rate";

    //CREATES SERVICE TO SP LINK TABLE
    public static final String TABLE_SERVICESFORPROVIDERS = "servicesForProviders";
    public static final String COLUMN_SP = "sp";
    public static final String COLUMN_SERVICE = "service";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //BUILDS THE USER TABLE
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_FNAME + " TEXT," + COLUMN_LNAME
                + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONE + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_SERVICEPROVIDER + " TEXT," + COLUMN_SPInfo + " INTEGER," + " FOREIGN KEY(SPInfo) REFERENCES SPInfoID " + ")";
        db.execSQL(CREATE_USERS_TABLE);

        //BUILDS THE SPINFO TABLE
        String CREATE_SPINFO_TABLE = "CREATE TABLE " +
                TABLE_SPINFO + "("
                + COLUMN_SPINFOID + " INTEGER PRIMARY KEY," + COLUMN_COMPANYNAME
                + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_LICENCED + " BOOLEAN" + COLUMN_MONDAYSTART + "TEXT," + COLUMN_MONDAYEND + "TEXT," + COLUMN_TUESDAYSTART + "TEXT,"
                + COLUMN_TUESDAYEND + "TEXT," + COLUMN_WEDNESDAYSTART + "TEXT," + COLUMN_WEDNESDAYEND + "TEXT," + COLUMN_THURSDAYSTART + "TEXT," + COLUMN_THURSDAYEND + "TEXT,"
                + COLUMN_FRIDAYSTART + "TEXT," + COLUMN_FRIDAYEND + "TEXT," + COLUMN_SATURDAYSTART + "TEXT," + COLUMN_SATURDAYEND + "TEXT," + COLUMN_SUNDAYSTART + "TEXT," +
                COLUMN_SUNDAYEND + "TEXT" + COLUMN_SERVICE1 + "TEXT," + COLUMN_SERVICE2 + "TEXT," + COLUMN_SERVICE3 + "TEXT," + COLUMN_SERVICE4 + "TEXT," + COLUMN_SERVICE5 + "TEXT" + ")";
        db.execSQL(CREATE_SPINFO_TABLE);

        String CREATE_SERVICES_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + COLUMN_SERVICEID + " INTEGER PRIMARY KEY," + COLUMN_SERVICENAME
                + " TEXT," + COLUMN_RATE + " DOUBLE" + ")";
        db.execSQL(CREATE_SERVICES_TABLE);

        String CREATE_SERVICESFORPROVIDERS_TABLE = "CREATE TABLE " +
                TABLE_SERVICESFORPROVIDERS + "("
                + COLUMN_SP + " INTEGER," + COLUMN_SERVICE
                + " TEXT," + COLUMN_RATE + " DOUBLE" + ")";
        db.execSQL(CREATE_SERVICESFORPROVIDERS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersionNumber, int newVersionNumber) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }
    /*
        AddUser takes in the information of the given user and stores its values in
        the Users table
        @param user
     */
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
        //ADD THE VALUES OF A NEW USER TO THE TABLE
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FNAME, user.getfName());
        values.put(COLUMN_LNAME, user.getlName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE, user.getPhone());
        values.put(COLUMN_ADDRESS, user.getAddress());
        values.put(COLUMN_SERVICEPROVIDER, sp.toString());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    /*
       findUser takes in a username and password and finds the user with that password
       and username in the users table and returns an instance of that user
       @param username
       @param password

       @return user
    */
    public User findUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";
        Cursor cursor = db.rawQuery(query, null);

        User user;

        if (cursor.moveToFirst()) {

            user = null;

            if (Integer.parseInt(cursor.getString(8)) == 1) {
                user = new ServiceProvider(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getString(7));

                //THIS QUERY LOOKS FOR THE ROW IN SPINFO THAT CONTAINS THE ADDITIONAL INFORMATION OF THE SERVICE PROVIDER BEING CREATED
                String query2 = "Select * FROM " + TABLE_SPINFO + " WHERE " +
                        COLUMN_SPINFOID + " = \"" + cursor.getInt(9) + "\"";

                //THIS CURSOR SEARCHES THROUGH THE SPINFO TABLE FOR THE THE ADDITONAL INFORMATION TO ADD
                Cursor cursor2 = db.rawQuery(query2, null);

                if (cursor2.moveToFirst()) {
                    ((ServiceProvider) user).additionalInfo(cursor2.getString(1), cursor2.getString(2), cursor.getInt(3) > 0,
                            this.createTimesArray(cursor.getInt(9)),this.createServiceArray(cursor.getInt(9)));
                }

            } if (Integer.parseInt(cursor.getString(8)) == 2) {
                user = new Admin(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));

            } if (Integer.parseInt(cursor.getString(8)) == 0) { //checks if the account is of type homeowner
                user = new Homeowner(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            }
        } else {
            user = null;
        }
        db.close();
        return user;
    }

    /*
       deleteUser takes in a username and password and finds the user with that password
       and username in the users table and deletes the user. It returns true if the user was
       deleted successfully.
       @param username
       @param password

       @return boolean
    */
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
    /*
       addSPInfo takes in additonal information for the service provider and adds them to the
       SPINFO database table
       @param username
       @param password
       @param companyName
       @param description
       @param licenced
       @param times

    */
    public void addSPInfo(String username, String password, String companyName, String description, Boolean licenced, String mondayStart, String mondayEnd,
                          String tuesdayStart, String tuesdayEnd, String wednesdayStart, String wednesdayEnd ,String thursdayStart, String thursdayEnd, String fridayStart,
                          String fridayEnd, String saturdayStart, String saturdayEnd, String sundayStart, String sundayEnd){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_COMPANYNAME, companyName); values.put(COLUMN_DESCRIPTION, description); values.put(COLUMN_LICENCED, licenced);
        values.put(COLUMN_MONDAYSTART, mondayStart); values.put(COLUMN_MONDAYEND, mondayEnd); values.put(COLUMN_TUESDAYSTART, tuesdayStart);
        values.put(COLUMN_TUESDAYEND, tuesdayEnd); values.put(COLUMN_WEDNESDAYSTART, wednesdayStart); values.put(COLUMN_WEDNESDAYEND, wednesdayEnd);
        values.put(COLUMN_THURSDAYSTART, thursdayStart); values.put(COLUMN_THURSDAYEND, thursdayEnd); values.put(COLUMN_FRIDAYSTART, fridayStart);
        values.put(COLUMN_FRIDAYEND, fridayEnd); values.put(COLUMN_SATURDAYSTART, saturdayStart); values.put(COLUMN_SATURDAYEND, saturdayEnd);
        values.put(COLUMN_SUNDAYSTART, sundayStart); values.put(COLUMN_SUNDAYEND, sundayEnd);
        db.insert(TABLE_SPINFO, null, values);

        //THIS QUERY FIND THE PRIMARY KEY OF THE NEWLY ADDED INFO FOR THE SERVICE PROVIDER
        String query = "Select * FROM " + TABLE_SPINFO + " WHERE " +
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

    public void updateHours(String username, String password, String[] newTimes){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";
        Cursor cursor = db.rawQuery(query, null);
        int id;
        id = cursor.getInt(0);

        //MONDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_MONDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_MONDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        //TUESDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_TUESDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_TUESDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        //WEDNESDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_WEDNESDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_WEDNESDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        //THURSDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_THURSDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_THURSDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        //FRIDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_FRIDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_FRIDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        //SATURDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_SATURDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_SATURDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        //SUNDAY

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_SUNDAYSTART + " = '" + newTimes[0] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);

        query = "UPDATE " + TABLE_SPINFO + " SET " + COLUMN_SUNDAYEND + " = '" + newTimes[1] + "' WHERE "
                + COLUMN_SPINFOID + " = '" + id + "'";
        db.execSQL(query);
    }

    public void addSPService(String username, String password, String service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "Select * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_USERNAME + " = \"" + username + "\"" + " AND " + COLUMN_PASSWORD + " = \"" + password + "\"";
        Cursor cursor = db.rawQuery(query, null);
        int id = cursor.getInt(9);
        query = "Select * FROM " + TABLE_SPINFO + " WHERE " +
                COLUMN_SPINFOID + " = \"" + id + "\"";
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            int locationIndex = 0 ;
            for (int i = 18; i<24; i++){
                if (cursor.getString(i) == null && locationIndex == 0) {
                    locationIndex = i;
                }
                if (cursor.getString(i)== service){
                    locationIndex = 0;
                    break;
                }
            }
            if (locationIndex == 18){
                values.put(COLUMN_SERVICE1, service);
            } if (locationIndex ==19){
                values.put(COLUMN_SERVICE2, service);
            } if (locationIndex ==20) {
                values.put(COLUMN_SERVICE3, service);
            } if (locationIndex ==21) {
                values.put(COLUMN_SERVICE4, service);
            } if (locationIndex ==22) {
                values.put(COLUMN_SERVICE5, service);
            }
            db.close();
            return;

        }

    }

    /*
       getDBContents returns are cursor object that is positioned it
       in the users table

       @return Cursor
    */
    public Cursor getDBContents(){
        SQLiteDatabase dB = this.getWritableDatabase();
        Cursor users = dB.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        return users;
    }

    /*
       clearUserTable empties the users table
    */
    public void clearUserTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
    }
    /*
        clearSPInfoTable empties the users table
    */
    public void clearSPInfoTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SPINFO,null,null);
    }
    /*
        createTimesArray takes in a key value and locates the row in the SPINFO
        table with that primary key, the method then generates a list that of all
        the start and end times of that service provider

        @param key

        @return newTimes
    */
    private String[] createTimesArray(int key){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_SPINFO + " WHERE " +
                COLUMN_SPINFOID + " = \"" + key + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String[] newTimes = new String[13];
        int i = 4;
        int j = 0;
        while (i<18){ //Go through all columns that store times
            newTimes[j] = cursor.getString(i);
            i++;
            j++;
        }
        db.close();
        return newTimes;
    }

    private Service[] createServiceArray(int key){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_SPINFO + " WHERE " +
                COLUMN_SPINFOID + " = \"" + key + "\"";
        Cursor cursor = db.rawQuery(query, null);
        Service[] newServices = new Service[4];
        int i = 18;
        int j = 0;
        while (i<23){ //Go through all columns that store times
            if (cursor.getString(i) != null) {
                newServices[j] = this.findService(cursor.getString(i));
            } else {
                newServices[j] = null;
            }
            i++;
            j++;
        }
        db.close();
        return newServices;
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

    public void updateRate(String oldRate, String service, String newRate){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_SERVICES + " SET " + COLUMN_RATE + " = '" + newRate + "' WHERE "
                + COLUMN_SERVICENAME + " = '" + service + "'" + " AND " + COLUMN_RATE + " = '" + oldRate + "'";
        db.execSQL(query);
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

    public Cursor getServiceContents(){
        SQLiteDatabase dB = this.getReadableDatabase();
        Cursor services = dB.rawQuery("SELECT * FROM " + TABLE_SERVICES, null);
        return services;
    }
}
