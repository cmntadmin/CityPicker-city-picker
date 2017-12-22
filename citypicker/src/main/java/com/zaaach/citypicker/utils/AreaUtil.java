package com.zaaach.citypicker.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zaaach.citypicker.model.Area;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.Provinces;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by 余鑫 on 2017/3/23.
 * <p>
 * 用来获取城市区域编码的工具类
 */

public class AreaUtil {


    private static final String FILE_NAME = "area.json";

    private static Map<String, Area> areaMap = new TreeMap<>();


    /**
     * 初始化区域数据
     */
    public static void initAreaData(Context context) throws IOException {

        if (areaMap.size() > 1024) {
            return;
        }

        //init
        InputStream inputStream = context.getAssets().open(FILE_NAME);
        Gson gson = new Gson();
        List<Area> tempList = gson.fromJson(
                new com.google.gson.stream.JsonReader(new InputStreamReader(inputStream)),
                new TypeToken<List<Area>>() {
                }.getType()
        );
        //list ==> map
        for (int i = 0; i < tempList.size(); i++) {
            areaMap.put(tempList.get(i).getId(), tempList.get(i));
        }
        tempList = null;
    }


    /**
     * 获取所有的省市区的数据
     *
     * @return
     */
    public static List<City> getAllProvinces(Context context) {
        List<City> cityses = new ArrayList<>();
        try {
            initAreaData(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Area> prs = getProvinces();
        if (prs != null) {
            for (int i = 0; i < prs.size(); i++) {
                Provinces prov = new Provinces();
                prov.setArea(prs.get(i));
                List<Area> areas = getAreasByParentId(prs.get(i).getId());
                List<City> cities = new ArrayList<>();
                for (int j = 0; j < areas.size(); j++) {
                    City city = new City();
                    areas.get(j).setPinyin(CharacterParser.getInstance().getSelling(areas.get(j).getName()));
                    city.setArea(areas.get(j));
                    cities.add(city);
                    cityses.add(city);
                }//end for
            }
        }
        return cityses;

    }


    /**
     * 获取所有的 省的数据
     *
     * @return
     */
    private static List<Area> getProvinces() {

        List<Area> areas = new ArrayList<>();
        Iterator<Map.Entry<String, Area>> it = areaMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Area> entry = it.next();
            Area area = entry.getValue();
            if (area.getId().endsWith("0000")) {
                areas.add(area);
            }
        }
        return areas;
    }

    /**
     * 通过parentId 去获取所有的数据
     *
     * @return
     */
    private static List<Area> getAreasByParentId(String parentId) {
        List<Area> areas = new ArrayList<>();
        Iterator<Map.Entry<String, Area>> it = areaMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Area> entry = it.next();
            Area area = entry.getValue();
            if (area.getParent_id() != null && area.getParent_id().equals(parentId)) {
                areas.add(area);
            }
        }
        return areas;
    }

}
