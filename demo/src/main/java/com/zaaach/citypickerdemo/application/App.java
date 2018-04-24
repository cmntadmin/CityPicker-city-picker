package com.zaaach.citypickerdemo.application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by litong on 2018/4/24.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
