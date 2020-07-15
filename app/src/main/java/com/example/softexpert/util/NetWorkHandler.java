package com.example.softexpert.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetWorkHandler {
    Context mContext;
    public NetWorkHandler( Context _Context){
        mContext = _Context;
    }
    public boolean isNetworkConnected() {
        Boolean Connected = false;
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        Connected = cm.getActiveNetworkInfo() != null;
        return Connected;
    }
}
