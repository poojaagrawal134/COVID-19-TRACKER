package com.example.covidtracker;

public class screenItem {
    String Title,Desc;
    int ScreenImg;

    public screenItem(String title, String desc, int screenImg) {
        Title = title;
        Desc = desc;
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDesc() {
        return Desc;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
