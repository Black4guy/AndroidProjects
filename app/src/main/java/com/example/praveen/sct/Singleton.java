package com.example.praveen.sct;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by AUG on 16-04-2018.
 */

class Singleton {
    private static Singleton mInstance;
    private static Context mCtx;
    private ImageLoader imageLoader;
    private RequestQueue mRequestQueue;

    private Singleton(Context context) {
        mCtx = context;



    }
    public static synchronized Singleton getInstance(Context context){

        if(mInstance==null){
            mInstance=new Singleton(context);
        }
        return mInstance;
    }


    public RequestQueue getRequestQueue() {

        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;

    }
    public <T>  void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

}


