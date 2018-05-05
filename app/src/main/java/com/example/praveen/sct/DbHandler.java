package com.example.praveen.sct;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class DbHandler extends SQLiteOpenHelper {

    //factory method used to customized pre defined class
    //database version
    public static final int DATABASE_VERSION = 2;
    //database name
    public static final String DATABASE_NAME = "place";
    //database table name malls
    public static final String DATABASE_TABLE_MALLS = "MALLS";
    // theaters
    public static final String DATABASE_TABLE_THEATERS = "THEATERS";
    //food
    public static final String DATABASE_TABLE_FOOD = "FOOD";
    //theaters
    public static final String DATABASE_TABLE_SHOPPING = "SHOPPING";
    //shopping
    public static final String DATABASE_TABLE_CAFETERIA = "CAFETERIA";
    //cafeteria
    public static final String DATABASE_TABLE_MONUMENTS = "MONUMENTS";

    //c0lonm id
    public static final String DATABASE_KEY_id = "id";
    public static final String DATABASE_KEY_NAME = "name";
    public static final String DATABASE_KEY_ADDRESS = "address";
    public static final String DATABASE_KEY_LATITUDE = "latitude";
    public static final String DATABASE_KEY_Longitude = "longitude";
    public static final String DATABASE_KEY_RATING = "rating";
    public static final String DATABASE_KEY_DISTANCE = "distance";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        String malls = " CREATE TABLE " + DATABASE_TABLE_MALLS + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        String food = " CREATE TABLE " + DATABASE_TABLE_FOOD + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        String cafeteria = " CREATE TABLE " + DATABASE_TABLE_CAFETERIA + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        String monuments = " CREATE TABLE " + DATABASE_TABLE_MONUMENTS + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        String shoppings = " CREATE TABLE " + DATABASE_TABLE_SHOPPING + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        String theaters = " CREATE TABLE " + DATABASE_TABLE_THEATERS + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";

        db.execSQL(malls);
        db.execSQL(food);
        db.execSQL(cafeteria);
        db.execSQL(monuments);
        db.execSQL(shoppings);
        db.execSQL(theaters);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MALLS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CAFETERIA);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SHOPPING);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_THEATERS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MONUMENTS);
        onCreate(db);
    }
    public void createMallTable(){
        SQLiteDatabase db = getWritableDatabase();
        String malls = " CREATE TABLE " + DATABASE_TABLE_MALLS + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        db.execSQL(malls);
    }
    public void createCoffeeTable(){
        SQLiteDatabase db = getWritableDatabase();
        String cafeteria = " CREATE TABLE " + DATABASE_TABLE_CAFETERIA + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        db.execSQL(cafeteria);

    }
    public void createShoppingTable(){
        SQLiteDatabase db = getWritableDatabase();
        String shoppings = " CREATE TABLE " + DATABASE_TABLE_SHOPPING + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        db.execSQL(shoppings);

    }
    public void createMonumentTable(){
        SQLiteDatabase db = getWritableDatabase();
        String monuments = " CREATE TABLE " + DATABASE_TABLE_MONUMENTS + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        db.execSQL(monuments);

    }
    public void createFoodTable(){
        SQLiteDatabase db = getWritableDatabase();
        String food = " CREATE TABLE " + DATABASE_TABLE_FOOD + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        db.execSQL(food);

    }
    public void createTheaterTable(){
        SQLiteDatabase db = getWritableDatabase();
        String theaters = " CREATE TABLE " + DATABASE_TABLE_THEATERS + "(" + DATABASE_KEY_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATABASE_KEY_NAME + " TEXT, " + DATABASE_KEY_ADDRESS + " TEXT," + DATABASE_KEY_LATITUDE + " REAL," + DATABASE_KEY_Longitude + " REAL, " + DATABASE_KEY_RATING + " REAL, " + DATABASE_KEY_DISTANCE + " INTEGER " + ")";
        db.execSQL(theaters);

    }




    public void dropMallTable(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MALLS);
        createMallTable();
    }
    public void dropCoffeeTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE_CAFETERIA);
        Log.d("cafedropped","dropped");
        createCoffeeTable();
    }
    public void dropFoodTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FOOD);
        createFoodTable();
        Log.d("table","food droped");    }
    public void dropMonumentTable(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MONUMENTS);
        createMonumentTable();
    }
    public void dropShoppingTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SHOPPING);
        createShoppingTable();
    }
    public void dropMTheaterTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_THEATERS);
        createTheaterTable();
    }















    public void addMalls(List<PlaceData> placeData) {

        SQLiteDatabase db = getWritableDatabase();
        for (int i=0;i<placeData.size();i++) {
            //Log.d("cafedata",placeData.get(i).getVenueAddress());

            ContentValues values = new ContentValues();
            values.put(DATABASE_KEY_NAME, placeData.get(i).getVenueName());
            values.put(DATABASE_KEY_ADDRESS, placeData.get(i).getVenueAddress());
            values.put(DATABASE_KEY_LATITUDE, placeData.get(i).getVenueLat());
            values.put(DATABASE_KEY_Longitude, placeData.get(i).getVenueLng());
            values.put(DATABASE_KEY_RATING, placeData.get(i).getVenueRating());
            values.put(DATABASE_KEY_DISTANCE, placeData.get(i).getVenueDistance());

            db.insert(DATABASE_TABLE_MALLS, null, values);


        }
        db.close();
    }


    public void addFood(List<PlaceData> placeData) {

        SQLiteDatabase db = getWritableDatabase();
        for (int i=0;i<placeData.size();i++) {
            //Log.d("cafedata",placeData.get(i).getVenueAddress());

            ContentValues values = new ContentValues();
            values.put(DATABASE_KEY_NAME, placeData.get(i).getVenueName());
            values.put(DATABASE_KEY_ADDRESS, placeData.get(i).getVenueAddress());
            values.put(DATABASE_KEY_LATITUDE, placeData.get(i).getVenueLat());
            values.put(DATABASE_KEY_Longitude, placeData.get(i).getVenueLng());
            values.put(DATABASE_KEY_RATING, placeData.get(i).getVenueRating());
            values.put(DATABASE_KEY_DISTANCE, placeData.get(i).getVenueDistance());

            db.insert(DATABASE_TABLE_FOOD, null, values);


        }
        db.close();
    }

    public void addCafeteria(List<PlaceData> placeData) {
        SQLiteDatabase db = getWritableDatabase();
        for (int i=0;i<placeData.size();i++) {
            //Log.d("cafedata",placeData.get(i).getVenueAddress());

            ContentValues values = new ContentValues();
            values.put(DATABASE_KEY_NAME, placeData.get(i).getVenueName());
            values.put(DATABASE_KEY_ADDRESS, placeData.get(i).getVenueAddress());
            values.put(DATABASE_KEY_LATITUDE, placeData.get(i).getVenueLat());
            values.put(DATABASE_KEY_Longitude, placeData.get(i).getVenueLng());
            values.put(DATABASE_KEY_RATING, placeData.get(i).getVenueRating());
            values.put(DATABASE_KEY_DISTANCE, placeData.get(i).getVenueDistance());

            db.insert(DATABASE_TABLE_CAFETERIA, null, values);


        }
        db.close();
    }


    public void addShopping(List<PlaceData> placeData) {

        SQLiteDatabase db = getWritableDatabase();
        for (int i=0;i<placeData.size();i++) {
            //Log.d("cafedata",placeData.get(i).getVenueAddress());

            ContentValues values = new ContentValues();
            values.put(DATABASE_KEY_NAME, placeData.get(i).getVenueName());
            values.put(DATABASE_KEY_ADDRESS, placeData.get(i).getVenueAddress());
            values.put(DATABASE_KEY_LATITUDE, placeData.get(i).getVenueLat());
            values.put(DATABASE_KEY_Longitude, placeData.get(i).getVenueLng());
            values.put(DATABASE_KEY_RATING, placeData.get(i).getVenueRating());
            values.put(DATABASE_KEY_DISTANCE, placeData.get(i).getVenueDistance());

            db.insert(DATABASE_TABLE_SHOPPING, null, values);


        }
        db.close();
    }


    public void addMonuments(List<PlaceData> placeData) {

        SQLiteDatabase db = getWritableDatabase();
        for (int i=0;i<placeData.size();i++) {
            //Log.d("cafedata",placeData.get(i).getVenueAddress());

            ContentValues values = new ContentValues();
            values.put(DATABASE_KEY_NAME, placeData.get(i).getVenueName());
            values.put(DATABASE_KEY_ADDRESS, placeData.get(i).getVenueAddress());
            values.put(DATABASE_KEY_LATITUDE, placeData.get(i).getVenueLat());
            values.put(DATABASE_KEY_Longitude, placeData.get(i).getVenueLng());
            values.put(DATABASE_KEY_RATING, placeData.get(i).getVenueRating());
            values.put(DATABASE_KEY_DISTANCE, placeData.get(i).getVenueDistance());

            db.insert(DATABASE_TABLE_MONUMENTS, null, values);


        }
        db.close();
    }


    public void addTheaters(List<PlaceData> placeData) {

        SQLiteDatabase db = getWritableDatabase();
        for (int i=0;i<placeData.size();i++) {
            //Log.d("cafedata",placeData.get(i).getVenueAddress());

            ContentValues values = new ContentValues();
            values.put(DATABASE_KEY_NAME, placeData.get(i).getVenueName());
            values.put(DATABASE_KEY_ADDRESS, placeData.get(i).getVenueAddress());
            values.put(DATABASE_KEY_LATITUDE, placeData.get(i).getVenueLat());
            values.put(DATABASE_KEY_Longitude, placeData.get(i).getVenueLng());
            values.put(DATABASE_KEY_RATING, placeData.get(i).getVenueRating());
            values.put(DATABASE_KEY_DISTANCE, placeData.get(i).getVenueDistance());

            db.insert(DATABASE_TABLE_THEATERS, null, values);


        }
        db.close();
    }

    public List<PlaceData> getAllMalls() {

        List<PlaceData> mylist =new ArrayList<>();
        String selectQuery = " SELECT * FROM " + DATABASE_TABLE_MALLS;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                PlaceData emp=new PlaceData();
                //   emp.setId(cursor.getInt(0));
                emp.setVenueName(cursor.getString(1));
                emp.setVenueAddress(cursor.getString(2));
                emp.setVenueLat(cursor.getDouble(3));
                emp.setVenueLng(cursor.getDouble(4));
                emp.setVenueRating(cursor.getFloat(5));
                emp.setVenueDistance(cursor.getInt(6));

                mylist.add(emp);
            }while (cursor.moveToNext());
        }
        return  mylist;
    }

    public List<PlaceData> getAllFood() {

        List<PlaceData> mylist =new ArrayList<>();
        String selectQuery = " SELECT * FROM " + DATABASE_TABLE_FOOD;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                PlaceData emp=new PlaceData();
                //   emp.setId(cursor.getInt(0));
                emp.setVenueName(cursor.getString(1));
                emp.setVenueAddress(cursor.getString(2));
                emp.setVenueLat(cursor.getDouble(3));
                emp.setVenueLng(cursor.getDouble(4));
                emp.setVenueRating(cursor.getFloat(5));
                emp.setVenueDistance(cursor.getInt(6));

                mylist.add(emp);
            }while (cursor.moveToNext());
        }
        return  mylist;
    }


    public List<PlaceData> getAllCafeteria() {

        List<PlaceData> mylist =new ArrayList<>();
        String selectQuery = " SELECT * FROM " + DATABASE_TABLE_CAFETERIA;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{


                PlaceData emp=new PlaceData();
                //   emp.setId(cursor.getInt(0));
                emp.setVenueName(cursor.getString(1));
                emp.setVenueAddress(cursor.getString(2));
                emp.setVenueLat(cursor.getDouble(3));
                emp.setVenueLng(cursor.getDouble(4));
                emp.setVenueRating(cursor.getDouble(5));
                emp.setVenueDistance(cursor.getInt(6));

                mylist.add(emp);
            }while (cursor.moveToNext());
        }
        return  mylist;
    }


    public List<PlaceData> getAllMonuments() {

        List<PlaceData> mylist =new ArrayList<>();
        String selectQuery = " SELECT * FROM " + DATABASE_TABLE_MONUMENTS;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                PlaceData emp=new PlaceData();
                //   emp.setId(cursor.getInt(0));
                emp.setVenueName(cursor.getString(1));
                emp.setVenueAddress(cursor.getString(2));
                emp.setVenueLat(cursor.getDouble(3));
                emp.setVenueLng(cursor.getDouble(4));
                emp.setVenueRating(cursor.getFloat(5));
                emp.setVenueDistance(cursor.getInt(6));

                mylist.add(emp);
            }while (cursor.moveToNext());
        }else{
            Log.d("data","no data");
        }

        return  mylist;
    }


    public List<PlaceData> getAllTheaters() {

        List<PlaceData> mylist =new ArrayList<>();
        String selectQuery = " SELECT * FROM " + DATABASE_TABLE_THEATERS;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                PlaceData emp=new PlaceData();
                //   emp.setId(cursor.getInt(0));
                emp.setVenueName(cursor.getString(1));
                emp.setVenueAddress(cursor.getString(2));
                emp.setVenueLat(cursor.getDouble(3));
                emp.setVenueLng(cursor.getDouble(4));
                emp.setVenueRating(cursor.getFloat(5));
                emp.setVenueDistance(cursor.getInt(6));

                mylist.add(emp);
            }while (cursor.moveToNext());
        }
        return  mylist;
    }
    public List<PlaceData> getAllShopping() {

        List<PlaceData> mylist =new ArrayList<>();
        String selectQuery = " SELECT * FROM " + DATABASE_TABLE_SHOPPING;
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                PlaceData emp=new PlaceData();
                //   emp.setId(cursor.getInt(0));
                emp.setVenueName(cursor.getString(1));
                emp.setVenueAddress(cursor.getString(2));
                emp.setVenueLat(cursor.getDouble(3));
                emp.setVenueLng(cursor.getDouble(4));
                emp.setVenueRating(cursor.getFloat(5));
                emp.setVenueDistance(cursor.getInt(6));

                mylist.add(emp);
            }while (cursor.moveToNext());
        }
        return  mylist;
    }


}

