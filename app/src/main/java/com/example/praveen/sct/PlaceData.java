package com.example.praveen.sct;

/**
 * Created by praveen on 4/20/2018.
 */

public class PlaceData {
    int id;
    String venueName;
    String venueAddress;
    double venueRating;
    double venueLat;
    double venueLng;
    int venueDistance;


    PlaceData(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public double getVenueRating() {
        return venueRating;
    }



    public double getVenueLat() {
        return venueLat;
    }

    public double getVenueLng() {
        return venueLng;
    }

    public int getVenueDistance() {
        return venueDistance;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public void setVenueRating(double venueRating) {
        this.venueRating = venueRating;
    }

    public void setVenueLat(double venueLat) {
        this.venueLat = venueLat;
    }

    public void setVenueLng(double venueLng) {
        this.venueLng = venueLng;
    }

    public void setVenueDistance(int venueDistance) {
        this.venueDistance = venueDistance;
    }

}
