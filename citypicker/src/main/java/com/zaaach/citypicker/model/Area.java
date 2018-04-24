package com.zaaach.citypicker.model;

import java.io.Serializable;

/**
 * Created by 余鑫 on 2017/3/23.
 */

public class Area implements Serializable {


    /**
     * id : 110000
     * name : 北京
     * parent_id : 0
     * sequence : null
     */

    private String id;
    private String name;
    private String parent_id;
    private String sequence;
    private String Pinyin;

    public Area() {
    }

    public Area(String name, String pinyin) {
        this.name = name;
        Pinyin = pinyin;
    }

    public Area(String id, String name, String pinyin) {
        this.id = id;
        this.name = name;
        Pinyin = pinyin;
    }


    public Area(String id, String name, String parent_id, String sequence, String pinyin) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.sequence = sequence;
        Pinyin = pinyin;
    }

    public String getPinyin() {
        return Pinyin;
    }

    public void setPinyin(String pinyin) {
        Pinyin = pinyin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return name + ",";
    }

}
