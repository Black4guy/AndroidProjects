package com.example.praveen.sct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class Prefrences extends AppCompatActivity {
    CheckBox malls,shopping,food,theater,cafeteria,monuments;
    String mall,shop,foo,theate,cafet,monum;
    private Button save;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TRACKING_LOCATION_KEY = "tracking_location";
    double lat;
    double longi;
    Intent intent;
    // Location classes
    private boolean mTrackingLocation;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Location mLocations;
    private DbHandler dbHandler;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAsync myAsync=new MyAsync();
        myAsync.execute();

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Prefrences");
        setContentView(R.layout.activity_prefrences);
        malls=findViewById(R.id.malls);
        theater=findViewById(R.id.theaters);
        shopping=findViewById(R.id.shopping);
        food=findViewById(R.id.food);
        monuments=findViewById(R.id.monument);
        cafeteria=findViewById(R.id.cafeteria);
        save=findViewById(R.id.save_Type);


        dbHandler=new DbHandler(this);



        startTrackingLocation();

    }

    @Override
    protected void onResume() {
        super.onResume();

        dbHandler=new DbHandler(this);
        MyAsync myAsync=new MyAsync();
        myAsync.execute();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList = new ArrayList<>();

                if (mTrackingLocation) {
                    mTrackingLocation = false;

                }

                if (malls.isChecked()) {
                    mall = malls.getText().toString();
                    arrayList.add(mall);

                } else {

                    mall = "";
                }

                if (theater.isChecked()) {
                    theate = theater.getText().toString();
                    arrayList.add(theate);

                } else {

                    theate = "";
                }



                if (shopping.isChecked()) {
                    shop = shopping.getText().toString();
                    arrayList.add(shop);


                } else {

                    shop = "";
                }





                if (food.isChecked()) {
                    foo = food.getText().toString();

                    arrayList.add(foo);

                } else {

                    foo = "";
                }
                if (monuments.isChecked()) {
                    monum = monuments.getText().toString();

                    arrayList.add(monum);

                } else {

                    monum = "";
                }


                if (cafeteria.isChecked()) {
                    cafet = cafeteria.getText().toString();
                    arrayList.add(cafet);


                } else {

                    cafet = "";
                }

                if(mLocations==null){
                    Toast.makeText(Prefrences.this, "Please Enable Location ", Toast.LENGTH_SHORT).show();
                }else {
if(cafeteria.isChecked()||monuments.isChecked()||shopping.isChecked()||malls.isChecked()||theater.isChecked()||food.isChecked()){
                    Intent intent = new Intent(Prefrences.this, MyVolley.class);
                    intent.putStringArrayListExtra("pp", arrayList);
                    startActivity(intent);
finish();}else{
    Toast.makeText(Prefrences.this, "Please Choose atleast one", Toast.LENGTH_SHORT).show();
}
                }

            }



        });
        if (mTrackingLocation) {
            startTrackingLocation();
        }

    }

    @SuppressLint("MissingPermission")
    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]
                            {android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);

            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(),
                            mLocationCallback,
                            Looper.myLooper() /* Looper */);

        } else {
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates
                    (getLocationRequest(),
                            mLocationCallback,
                            Looper.myLooper() /* Looper */);


        }
    }
    private void stopTrackingLocation() {


    }

    private LocationRequest getLocationRequest() {
        @SuppressLint("RestrictedApi") LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:


                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    startTrackingLocation();
                } else {
                    Toast.makeText(this,
                            R.string.location_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        if (mTrackingLocation) {
            stopTrackingLocation();
            mTrackingLocation = true;
        }
        super.onPause();
    }
    class MyAsync extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(
                    getApplicationContext());
            mLocationCallback = new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    // If tracking is turned on, reverse geocode into an address
                    if (mTrackingLocation) {
                        mLocations= locationResult.getLastLocation();
                        lat =mLocations.getLatitude();
                        longi=mLocations.getLongitude();

                        SharedPreferences sharedpreferences =Prefrences.this.getSharedPreferences("timing", Context.MODE_PRIVATE);



                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("latitude",String.valueOf(lat));
                        editor.putString("longitude",String.valueOf(longi));
                        editor.apply();


                    }
                }
            };
            return null;
        }


    }




}

