package com.example.praveen.sct;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    List<PlaceData> coffee, theaterdata, monumentsdata, shopingdata, mallsdata, fooddata, placeDataa;
    int time;

    ArrayList al;
    private GoogleMap mMap;
    private DbHandler dbHandler;
    SharedPreferences sharedpreferences;
    int cafe;
    int theater;
    int shopping;
    int monument;
    int food;
    int mall;
    int alCount;
    double cafelatitude, cafelongitude, monumentlatitude, malllongitude, malllatitude, monumentlongitude, theaterlatitude, theaterlongitude, foodlatitude, foodlongitude, shoppinglatitude, shoppinglongitude, lati, longi;
    String cafetime, theatertime, shoppingtime, monumenttime, foodtime, malltime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        dbHandler = new DbHandler(this);
        sharedpreferences = this.getSharedPreferences("timing", Context.MODE_PRIVATE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        placeDataa = new ArrayList<>();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new MyInfoWindow(MapsActivity.this));
            SharedPreferences settings = this.getSharedPreferences("hello", Context.MODE_PRIVATE);

            Set<String> set=settings.getStringSet("hi",null);
            List<String> al = new ArrayList<String>(set);


       // Bundle bundle = getIntent().getExtras();
        //al = bundle.getParcelableArrayList("selectedData");
        alCount = al.size();
        for (int i = 0; i < al.size(); i++) {

        }
        lati = Double.valueOf(sharedpreferences.getString("latitude", null));
        longi = Double.valueOf(sharedpreferences.getString("longitude", null));
        LatLng latLn = new LatLng(lati, longi);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses = null; // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        try {
            addresses = geocoder.getFromLocation(lati, longi, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String addressing = addresses.get(0).getAddressLine(0);
        mMap.addMarker(new MarkerOptions().position(latLn).title("You are here").snippet(addressing)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLn, 14.0f));


        for (int i = 0; i < al.size(); i++) {

            if (al.get(i).equals("Cafeteria")) {
                coffee = dbHandler.getAllCafeteria();


                cafelatitude = coffee.get(0).getVenueLat();
                cafelongitude = coffee.get(0).getVenueLng();
                String name = coffee.get(0).getVenueName();
                String address = coffee.get(0).getVenueAddress();
                double rating = coffee.get(0).getVenueRating();
                int distance = coffee.get(0).getVenueDistance();
                int cafetimee = distance / 500;

                cafe = cafetimee + 30;
                cafetime();
                placeDataa.add(coffee.get(0));
                LatLng latLng = new LatLng(cafelatitude, cafelongitude);

                mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address+"\n"+cafetime));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
            }
            if (al.get(i).equals("Theaters")) {
                theaterdata = dbHandler.getAllTheaters();


                theaterlatitude = theaterdata.get(0).getVenueLat();
                theaterlongitude = theaterdata.get(0).getVenueLng();
                String name = theaterdata.get(0).getVenueName();
                String address = theaterdata.get(0).getVenueAddress();
                double rating = theaterdata.get(0).getVenueRating();
                int distance = theaterdata.get(0).getVenueDistance();
                int thratertimee = distance / 500;

                theater = thratertimee + 210;
                theatertime();
                placeDataa.add(theaterdata.get(0));
                LatLng latLng = new LatLng(theaterlatitude, theaterlongitude);

                mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address+"\n"+theatertime+"\n"+distance+" m"
               +"\n"+rating+"/10" ));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
            }
            if (al.get(i).equals("Monuments")) {
                monumentsdata = dbHandler.getAllMonuments();


                monumentlatitude = monumentsdata.get(0).getVenueLat();
                monumentlongitude = monumentsdata.get(0).getVenueLng();
                String name = monumentsdata.get(0).getVenueName();
                String address = monumentsdata.get(0).getVenueAddress();
                double rating = monumentsdata.get(0).getVenueRating();
                int distance = monumentsdata.get(0).getVenueDistance();
                int monumentt = distance / 500;
                monument = monumentt + 300;
                monumenttime();
                placeDataa.add(monumentsdata.get(0));
                LatLng latLng = new LatLng(monumentlatitude, monumentlongitude);

                Marker m=  mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address+" \n"+cafetime));
                m.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));

            }
            if (al.get(i).equals("Food")) {
                fooddata = dbHandler.getAllFood();


                foodlatitude = fooddata.get(0).getVenueLat();
                foodlongitude = fooddata.get(0).getVenueLng();
                String name = fooddata.get(0).getVenueName();
                String address = fooddata.get(0).getVenueAddress();
                double rating = fooddata.get(0).getVenueRating();
                int distance = fooddata.get(0).getVenueDistance();
                int fooding = distance / 500;
                food = fooding + 30;

                foodtime();
                placeDataa.add(fooddata.get(0));
                LatLng latLng = new LatLng(foodlatitude, foodlongitude);

                Marker m=    mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address+" \n"+foodtime));
                m.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
            }

            if (al.get(i).equals("Malls")) {
                mallsdata = dbHandler.getAllMalls();


                malllatitude = mallsdata.get(0).getVenueLat();
                malllongitude = mallsdata.get(0).getVenueLng();
                String name = mallsdata.get(0).getVenueName();
                String address = mallsdata.get(0).getVenueAddress();
                double rating = mallsdata.get(0).getVenueRating();
                int distance = mallsdata.get(0).getVenueDistance();
                int mallss = distance / 500;
                mall = mallss + 90;
                mallstime();
                placeDataa.add(mallsdata.get(0));


                LatLng latLng = new LatLng(malllatitude, malllongitude);

                Marker m=  mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address+"\n"+malltime));
                m.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
            }

            if (al.get(i).equals("Shopping")) {
                shopingdata = dbHandler.getAllShopping();


                shoppinglatitude = shopingdata.get(0).getVenueLat();
                shoppinglongitude = shopingdata.get(0).getVenueLng();
                String name = shopingdata.get(0).getVenueName();
                String address = shopingdata.get(0).getVenueAddress();
                double rating = shopingdata.get(0).getVenueRating();
                int distance = shopingdata.get(0).getVenueDistance();

                int shoppings = distance / 500;
                shopping = shoppings + 90;
                shoppingtime();
                placeDataa.add(shopingdata.get(0));
                LatLng latLng = new LatLng(shoppinglatitude, shoppinglongitude);

                mMap.addMarker(new MarkerOptions().position(latLng).title(name).snippet(address+"\n"+shoppingtime));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14.0f));
            }


        }
        PolylineOptions options = new PolylineOptions().width(8).color(Color.BLUE).geodesic(true);
        options.add(new LatLng(lati, longi));
        for (int z = 0; z < placeDataa.size(); z++) {
            LatLng point = new LatLng(placeDataa.get(z).getVenueLat(), placeDataa.get(z).getVenueLng());
            options.add(point);
        }
        mMap.addPolyline(options);
        timeSchedule();


    }




    public void timeSchedule() {
        String timein = sharedpreferences.getString("timein", null);
        String timeout = sharedpreferences.getString("timeout", null);

        String[] hourMin = timein.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        mins = hoursInMins + mins;


        String[] hourMins = timeout.split(":");
        int hours = Integer.parseInt(hourMins[0]);
        int minss = Integer.parseInt(hourMins[1]);
        int hoursInMinss = hours * 60;
        minss = hoursInMinss + minss;

        time = minss - mins;


    }
    public void foodtime(){
        if(food<60){
            foodtime=food+" mins";
        }else{
            int x=food/60;
            int y=food%60;
            foodtime=x+" hr:"+y+" mins";
        }
    }

    public void cafetime(){
        if(cafe<60){
            cafetime=cafe+" mins";
        }else{
            int x=cafe/60;
            int y=cafe%60;
            cafetime=x+" hr:"+y+" mins";
        }
    }
    public void monumenttime(){
        if(monument<60){
            monumenttime=monument+" mins";
        }else{
            int x=monument/60;
            int y=monument%60;
            monumenttime=x+" hr:"+y+" mins";
        }
    }
    public void mallstime(){
        if(mall<60){
            malltime=mall+" mins";
        }else{
            int x=mall/60;
            int y=mall%60;
            malltime=x+" hr:"+y+" mins";
        }
    }
    public void shoppingtime(){
        if(shopping<60){
            shoppingtime=shopping+" mins";
        }else{
            int x=shopping/60;
            int y=shopping%60;
            shoppingtime=x+" hr:"+y+" mins";
        }
    }
    public void theatertime(){
        if(theater<60){
            theatertime=theater+" mins";
        }else{
            int x=theater/60;
            int y=theater%60;
            theatertime=x+" hr:"+y+" mins";
        }
    }
    @Override
    public boolean onMarkerClick(Marker arg0) {

        return true;
    }

}