package com.example.testapplication.Model;

import java.util.Date;

public class TitleModel {
    private String title;
    private String date;
    private int LastPageIndex;
    private boolean isSwitchChecked;
    private boolean isLastPageReached;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSwitchChecked() {
        return isSwitchChecked;
    }

    public void setSwitchChecked(boolean switchChecked) {
        isSwitchChecked = switchChecked;
    }

    public boolean isLastPageReached() {
        return isLastPageReached;
    }

    public void setLastPageReached(boolean lastPageReached) {
        isLastPageReached = lastPageReached;
    }

    public int getLastPageIndex() {
        return LastPageIndex;
    }

    public void setLastPageIndex(int lastPageIndex) {
        LastPageIndex = lastPageIndex;
    }
}
