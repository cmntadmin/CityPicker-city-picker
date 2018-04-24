package com.zaaach.citypicker.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by litong on 2018/4/20.
 */

@Table(name = "area_vo")
public class AreaVo extends Model {

    @Column(name = "area_id")
    private String area_id;
    @Column(name = "name")
    private String name;
    @Column(name = "parent_id")
    private String parent_id;
    @Column(name = "sequence")
    private String sequence;
    @Column(name = "pinyin")
    private String pinyin;

    @Column(name = "create_time")
    private Long create_time;

    public AreaVo() {
    }

    public AreaVo(String area_id, String name, String parent_id, String sequence, String pinyin) {
        this.area_id = area_id;
        this.name = name;
        this.parent_id = parent_id;
        this.sequence = sequence;
        this.pinyin = pinyin;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}
