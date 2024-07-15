package com.trophate.ouo.auth.dto;

public class InfoOfUserEditDTO {

    /**
     * 性别
     */
    private Integer sex;
    /**
     * 简介
     */
    private String profile;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
