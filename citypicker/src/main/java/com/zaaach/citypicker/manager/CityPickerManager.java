package com.zaaach.citypicker.manager;

import com.zaaach.citypicker.entity.AreaVo;
import com.zaaach.citypicker.model.Area;

import java.util.List;

/**
 * Created by litong on 2018/4/24.
 */

public interface CityPickerManager {

    public List<Area> getAreaByName(String name);

    public List<Area> getCitys();


    public void addHistoryArea(Area area);


    public List<AreaVo> getHistoryArea();

}
