package com.example.term_project_javafx.util;

public class pCompany {
    String name;
    int mCount;

    public pCompany() {
        name = "";
        mCount = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public void showCompany() {
        System.out.println("o Production Company: " + name + ".   Movie count: " + mCount + "\n");
    }
}
