package com.wu1015.coursessystem.model;

import android.database.sqlite.SQLiteOpenHelper;


public class MajorController {
    private Major major;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public MajorController(Major major, SQLiteOpenHelper sqLiteOpenHelper) {
        this.major = major;
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public SQLiteOpenHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }

    public Major doController() {
        return getMajor();
    }
}
