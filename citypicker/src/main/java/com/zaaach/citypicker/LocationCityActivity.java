package com.zaaach.citypicker;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.utils.StringUtils;


/**
 * Created by Mr.LeeTong on 2017/12/22.
 */

public  class LocationCityActivity extends CityPickerActivity {


    private AMapLocationClient mLocationClient;

    @Override
    public void location() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                initLocation("成都");
//                if (aMapLocation != null) {
//                    if (aMapLocation.getErrorCode() == 0) {
//                        String city = aMapLocation.getCity();
//                        String district = aMapLocation.getDistrict();
//                        String location = StringUtils.extractLocation(city, district);
//                        initLocation("成都");
//                    } else {
//                        //定位失败
//                        initLocation("");
//                    }
//                }
            }
        });


        //请求权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mLocationClient.startLocation();
        } else {
            requestPermissions(this, neededPermissions, this);
        }
    }



    @Override
    public void onGranted() {
        mLocationClient.startLocation();
    }


}
