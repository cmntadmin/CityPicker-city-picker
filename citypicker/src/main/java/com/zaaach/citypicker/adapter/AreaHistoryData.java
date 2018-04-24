package com.zaaach.citypicker.adapter;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.zaaach.citypicker.entity.AreaVo;
import com.zaaach.citypicker.model.Area;

import java.util.Date;
import java.util.List;

/**
 * Created by litong on 2018/4/23.
 */

public class AreaHistoryData {

    /**
     * 添加地区
     *
     * @param area
     */
    public void addHistoryArea(Area area) {

        List<AreaVo> areaVos = new Select().from(AreaVo.class).orderBy("create_time desc").execute();
        for (int i = 0; i < areaVos.size(); i++) {
            if (areaVos.get(i).getArea_id().equals(area.getId())) {
                new Delete().from(AreaVo.class).where("area_id = ?", area.getId()).execute();
            }
        }
        AreaVo areaVo = new AreaVo();
        areaVo.setArea_id(area.getId());
        areaVo.setName(area.getName());
        areaVo.setParent_id(area.getParent_id());
        areaVo.setPinyin(area.getPinyin());
        if(null == area.getSequence()){
            areaVo.setSequence(" ");
        }else{
            areaVo.setSequence(area.getSequence());
        }
        areaVo.setCreate_time(new Date().getTime());
        areaVo.save();
    }


    /**
     * 获取保存地区数据
     *
     * @return
     */
    public List<AreaVo> getHistoryArea() {
        return new Select().from(AreaVo.class).limit(3).orderBy("create_time desc").execute();
    }

}
