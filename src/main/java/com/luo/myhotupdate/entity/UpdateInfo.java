package com.luo.myhotupdate.entity;

public class UpdateInfo {

    private String appName;

    private int versionCode;

    private String versionName;

    private String downUrl;

    private String description;

    private int isPatch;

    private int openSilent;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsPatch() {
        return isPatch;
    }

    public void setIsPatch(int isPatch) {
        this.isPatch = isPatch;
    }

    public int getOpenSilent() {
        return openSilent;
    }

    public void setOpenSilent(int openSilent) {
        this.openSilent = openSilent;
    }
}
