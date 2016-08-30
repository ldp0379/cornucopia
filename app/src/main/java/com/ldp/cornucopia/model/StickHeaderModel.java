package com.ldp.cornucopia.model;

/**
 * StickHeaderRV - Model
 * Created by ldp on 16/8/5.
 */
public class StickHeaderModel {
    public String sticky;
    public String name;
    public String gender;
    public String profession;

    public StickHeaderModel(String sticky, String name, String gender, String profession) {
        this.sticky = sticky;
        this.name = name;
        this.gender = gender;
        this.profession = profession;
    }
}
