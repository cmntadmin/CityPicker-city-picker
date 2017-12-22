package com.zaaach.citypicker.model;

import java.util.List;

/**
 * Created by 余鑫 on 2017/4/13.
 *
 *
 * 省
 *
 */

public class Provinces {

    private Area area;

    private List<City> citys;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }
}
