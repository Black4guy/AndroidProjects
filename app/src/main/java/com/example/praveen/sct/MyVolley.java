package com.example.praveen.sct;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyVolley extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1000;
    DbHandler dbHandler;
    ArrayList arrayList;
    String latitude, longitude, timein, timeout;
    private RequestQueue requestQueue;
    ArrayList selectedData;
    private String foursquareBaseURL = "https://api.foursquare.com/v2/";
    TextView textView;
    private String foursquareClientID;
    private String foursquareClientSecret;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String date;
    private String ll;
    private TextView animtextview;
    Button view;
    private Set<String> pldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Congrats");
        setContentView(R.layout.activity_my_volley);

        view=findViewById(R.id.viewButton);
        dbHandler = new DbHandler(this);

        requestQueue = Volley.newRequestQueue(this);


        RequestQueue queue = Singleton.getInstance(this.getApplicationContext()).getRequestQueue();

        foursquareClientID = getResources().getString(R.string.foursquare_client_id);
        foursquareClientSecret = getResources().getString(R.string.foursquare_client_secret);


        sharedPreferences = getSharedPreferences("timing", MODE_PRIVATE);
        latitude = sharedPreferences.getString("latitude", null);
        longitude = sharedPreferences.getString("longitude", null);
        timein = sharedPreferences.getString("timein", null);
        timeout = sharedPreferences.getString("timeout", null);

        animtextview=(TextView)findViewById(R.id.animttextview);



        ll = latitude + "," + longitude;


        date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        apiCalling();
        viewButton();
    }

    private void viewButton() {

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyVolley.this,MapsActivity.class);
                //intent.putStringArrayListExtra("selectedData",selectedData);

SharedPreferences sharedPreferences=getSharedPreferences("hello",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putStringSet("hi",pldata);
        editor.apply();
                startActivity(intent);
                finish();
            }
        });
    }

    public void apiCalling() {
        Bundle bundle = getIntent().getExtras();

        arrayList = bundle.getParcelableArrayList("pp");
pldata=new  HashSet<>(arrayList);
        selectedData=new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            selectedData.add(arrayList.get(i));

            if (arrayList.get(i).equals("Cafeteria")) {
                cafeteria();

            }
            if (arrayList.get(i).equals("Theaters")) {

                theater();

            }
            if (arrayList.get(i).equals("Food")) {
                food();

            }
            if (arrayList.get(i).equals("Monuments")) {
                monument();

            }
            if (arrayList.get(i).equals("Shopping")) {
                shopping();


            }
            if (arrayList.get(i).equals("Malls")) {

                malls();

            }



        }

    }



    public void cafeteria(){
        String url = foursquareBaseURL + "search/recommendations?v=" + date + "&intent=coffee&client_id=" + foursquareClientID + "&client_secret=" + foursquareClientSecret + "&ll="+ll;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                List<PlaceData> additional = new ArrayList<>();
                try {

                    JSONObject cafeJson = response.getJSONObject("response");


                    JSONObject jsonGroups = cafeJson.getJSONObject("group");
                    JSONArray arrayResult = jsonGroups.getJSONArray("results");


                    //  for (int ii = 0; ii < arrayResult.length(); ii++) {
                    JSONObject venues = arrayResult.getJSONObject(0);

                    JSONObject venueDetail = venues.getJSONObject("venue");


                    double venueRating = venueDetail.getDouble("rating");

                    String venueName = venueDetail.getString("name");
                    JSONObject location = venueDetail.getJSONObject("location");
                    int locationDistance = location.getInt("distance");
                    String locationAddress;
                    if(!location.has("address")){
                        JSONArray formattedAddress=location.getJSONArray("formattedAddress");

                        locationAddress=formattedAddress.get(0).toString();

                    }else {
                        locationAddress = location.getString("address");
                    }
                    //  String locationAddress = location.getString("address");
                    double locationLat = location.getDouble("lat");
                    double locationLng = location.getDouble("lng");


                    PlaceData placeData = new PlaceData();

                    placeData.setVenueName(venueName);
                    placeData.setVenueAddress(locationAddress);
                    placeData.setVenueLat(locationLat);
                    placeData.setVenueLng(locationLng);
                    placeData.setVenueRating(venueRating);
                    placeData.setVenueDistance(locationDistance);
                    additional.add(placeData);


                    //}


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyVolley.this, ""+e, Toast.LENGTH_SHORT).show();
                }
                dbHandler.dropCoffeeTable();
                dbHandler.addCafeteria(additional);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyVolley.this, "Please Retry", Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim);
        animtextview.startAnimation(animation);
    }

    public void theater(){

        String url = foursquareBaseURL + "venues/explore?ll=" + ll + "&query=movies&client_id=" + foursquareClientID + "&client_secret=" + foursquareClientSecret + "&v=" + date;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                List<PlaceData> additional = new ArrayList<>();
                try {

                    JSONObject cafeJson = response.getJSONObject("response");

                    JSONArray jsonGroups = cafeJson.getJSONArray("groups");
                    JSONObject grouping = jsonGroups.getJSONObject(0);
                    JSONArray arrayResult = grouping.getJSONArray("items");


                    // for (int ii = 0; ii < arrayResult.length(); ii++) {
                    JSONObject venues = arrayResult.getJSONObject(0);

                    JSONObject venueDetail = venues.getJSONObject("venue");


                    double venueRating = venueDetail.getDouble("rating");

                    String venueName = venueDetail.getString("name");
                    JSONObject location = venueDetail.getJSONObject("location");
                    int locationDistance = location.getInt("distance");
                    String locationAddress;
                    if (!location.has("address")) {
                        JSONArray formattedAddress = location.getJSONArray("formattedAddress");

                        locationAddress = formattedAddress.get(0).toString();

                    } else {
                        locationAddress = location.getString("address");
                    }
                    double locationLat = location.getDouble("lat");

                    double locationLng = location.getDouble("lng");

                    PlaceData placeData = new PlaceData();

                    placeData.setVenueName(venueName);
                    placeData.setVenueAddress(locationAddress);
                    placeData.setVenueLat(locationLat);
                    placeData.setVenueLng(locationLng);
                    placeData.setVenueRating(venueRating);
                    placeData.setVenueDistance(locationDistance);
                    additional.add(placeData);





                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dbHandler.dropMTheaterTable();
                dbHandler.addTheaters(additional);
                Log.d("theater","added");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyVolley.this, "volley error\n" + error, Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
    public void food(){
        String url = foursquareBaseURL + "venues/explore?ll=" + ll + "&query=food&client_id=" + foursquareClientID + "&client_secret=" + foursquareClientSecret + "&v=" + date;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                List<PlaceData> additional = new ArrayList<>();
                try {

                    JSONObject cafeJson = response.getJSONObject("response");

                    JSONArray jsonGroups = cafeJson.getJSONArray("groups");
                    JSONObject grouping=jsonGroups.getJSONObject(0);
                    JSONArray arrayResult = grouping.getJSONArray("items");


                    JSONObject venues = arrayResult.getJSONObject(0);

                    JSONObject venueDetail = venues.getJSONObject("venue");
                    double venueRating = venueDetail.getDouble("rating");

                    String venueName = venueDetail.getString("name");
                    JSONObject location = venueDetail.getJSONObject("location");
                    int locationDistance = location.getInt("distance");
                    String locationAddress;
                    if(!location.has("address")){
                        JSONArray formattedAddress=location.getJSONArray("formattedAddress");

                        locationAddress=formattedAddress.get(0).toString();

                    }else {
                        locationAddress = location.getString("address");
                    }                        double locationLat = location.getDouble("lat");
                    double locationLng = location.getDouble("lng");
                     PlaceData placeData = new PlaceData();

                    placeData.setVenueName(venueName);
                    placeData.setVenueAddress(locationAddress);
                    placeData.setVenueLat(locationLat);
                    placeData.setVenueLng(locationLng);
                    placeData.setVenueRating(venueRating);
                    placeData.setVenueDistance(locationDistance);
                    additional.add(placeData);








                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dbHandler.dropFoodTable();
                dbHandler.addFood(additional);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyVolley.this, "volley error\n" + error, Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }


    public void monument(){
        String  url = foursquareBaseURL + "venues/explore?ll=" + ll + "&query=monument&client_id=" + foursquareClientID + "&client_secret=" + foursquareClientSecret + "&v=" + date;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                List<PlaceData> additional = new ArrayList<>();
                try {

                    JSONObject cafeJson = response.getJSONObject("response");

                    JSONArray jsonGroups = cafeJson.getJSONArray("groups");
                    JSONObject grouping=jsonGroups.getJSONObject(0);
                    JSONArray arrayResult = grouping.getJSONArray("items");


                    JSONObject venues = arrayResult.getJSONObject(0);

                    JSONObject venueDetail = venues.getJSONObject("venue");
                    double venueRating = venueDetail.getDouble("rating");

                    String venueName = venueDetail.getString("name");
                    JSONObject location = venueDetail.getJSONObject("location");
                    int locationDistance = location.getInt("distance");
                    String locationAddress;
                    if(!location.has("address")){
                        JSONArray formattedAddress=location.getJSONArray("formattedAddress");

                        locationAddress=formattedAddress.get(0).toString();

                    }else {
                        locationAddress = location.getString("address");
                    }                        double locationLat = location.getDouble("lat");
                    double locationLng = location.getDouble("lng");
                    PlaceData placeData = new PlaceData();

                    placeData.setVenueName(venueName);
                    placeData.setVenueAddress(locationAddress);
                    placeData.setVenueLat(locationLat);
                    placeData.setVenueLng(locationLng);
                    placeData.setVenueRating(venueRating);
                    placeData.setVenueDistance(locationDistance);
                    additional.add(placeData);






                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyVolley.this, ""+e, Toast.LENGTH_SHORT).show();
                }
                dbHandler.dropMonumentTable();
                dbHandler.addMonuments(additional);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyVolley.this, "volley error\n" + error, Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }



    public void shopping(){
        String url = foursquareBaseURL + "venues/explore?ll=" + ll + "&query=shopping&client_id=" + foursquareClientID + "&client_secret=" + foursquareClientSecret + "&v=" + date;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                List<PlaceData> additional = new ArrayList<>();
                try {

                    JSONObject cafeJson = response.getJSONObject("response");

                    JSONArray jsonGroups = cafeJson.getJSONArray("groups");
                    JSONObject grouping=jsonGroups.getJSONObject(0);
                    JSONArray arrayResult = grouping.getJSONArray("items");


                    JSONObject venues = arrayResult.getJSONObject(0);

                    JSONObject venueDetail = venues.getJSONObject("venue");
                    double venueRating = venueDetail.getDouble("rating");

                    String venueName = venueDetail.getString("name");
                    JSONObject location = venueDetail.getJSONObject("location");
                    int locationDistance = location.getInt("distance");
                    String locationAddress = location.getString("address");
                    double locationLat = location.getDouble("lat");
                    double locationLng = location.getDouble("lng");
                    PlaceData placeData = new PlaceData();

                    placeData.setVenueName(venueName);
                    placeData.setVenueAddress(locationAddress);
                    placeData.setVenueLat(locationLat);
                    placeData.setVenueLng(locationLng);
                    placeData.setVenueRating(venueRating);
                    placeData.setVenueDistance(locationDistance);
                    additional.add(placeData);







                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dbHandler.dropShoppingTable();
                dbHandler.addShopping(additional);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyVolley.this, "volley error\n" + error, Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }



    public void malls(){
        String url = foursquareBaseURL + "venues/explore?ll=" + ll + "&query=mall&client_id=" + foursquareClientID + "&client_secret=" + foursquareClientSecret + "&v=" + date;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                List<PlaceData> additional = new ArrayList<>();
                try {

                    JSONObject cafeJson = response.getJSONObject("response");

                    JSONArray jsonGroups = cafeJson.getJSONArray("groups");
                    JSONObject grouping=jsonGroups.getJSONObject(0);
                    JSONArray arrayResult = grouping.getJSONArray("items");




                    JSONObject venues = arrayResult.getJSONObject(0);

                    JSONObject venueDetail = venues.getJSONObject("venue");
                    double venueRating = venueDetail.getDouble("rating");

                    String venueName = venueDetail.getString("name");
                    JSONObject location = venueDetail.getJSONObject("location");
                    int locationDistance = location.getInt("distance");
                    String locationAddress;
                    if(!location.has("address")){
                        JSONArray formattedAddress=location.getJSONArray("formattedAddress");

                        locationAddress=formattedAddress.get(0).toString();

                    }else {
                        locationAddress = location.getString("address");
                    }                        double locationLat = location.getDouble("lat");
                    double locationLng = location.getDouble("lng");
                    // PlaceData placeData=new PlaceData(venueName,locationAddress,venueRating,locationLat,locationLng,locationDistance);
                    PlaceData placeData = new PlaceData();

                    placeData.setVenueName(venueName);
                    placeData.setVenueAddress(locationAddress);
                    placeData.setVenueLat(locationLat);
                    placeData.setVenueLng(locationLng);
                    placeData.setVenueRating(venueRating);
                    placeData.setVenueDistance(locationDistance);
                    additional.add(placeData);







                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dbHandler.dropMallTable();
                dbHandler.addMalls(additional);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyVolley.this, "volley error\n" + error, Toast.LENGTH_SHORT).show();
            }
        });
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }




}


