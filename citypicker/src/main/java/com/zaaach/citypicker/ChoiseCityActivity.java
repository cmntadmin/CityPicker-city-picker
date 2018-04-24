package com.zaaach.citypicker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zaaach.citypicker.CityPickerActivity;

/**
 * Created by 余鑫 on 2017/4/6.
 */

public class ChoiseCityActivity extends CityPickerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void location() {


    }

    @Override
    public void finish() {

    }


    @Override
    public void onGranted() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
