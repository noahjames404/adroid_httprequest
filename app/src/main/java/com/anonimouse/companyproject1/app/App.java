package com.anonimouse.companyproject1.app;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class App extends Application {
    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        initImageLoader(this);
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public RequestQueue getReqQueue() {
        if (mRequestQueue == null)  {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToReqQueue(Request<T> req, String tag) {
        getReqQueue().add(req);
    }

    public <T> void addToReqQueue(Request<T> req) {
        getReqQueue().add(req);
    }

    public void cancelPendingReq(Object tag) {
        if (mRequestQueue != null) mRequestQueue.cancelAll(tag);
    }

}
