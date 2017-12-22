package com.zaaach.citypicker.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by 余鑫 on 2017/2/20.
 */
public class App extends Application {


    private static App instance;
    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mContext = this;


    }//end onCreate


    public static Context getContext() {
        return mContext;
    }

    public App getInstance() {
        return instance;
    }

}



