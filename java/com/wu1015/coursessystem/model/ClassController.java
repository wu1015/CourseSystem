package com.wu1015.coursessystem.model;

import android.database.sqlite.SQLiteOpenHelper;


public class ClassController {
    private Class aClass;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public ClassController(Class aClass, SQLiteOpenHelper sqLiteOpenHelper) {
        this.aClass = aClass;
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public SQLiteOpenHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }

    public Class doController() {
        return getaClass();
    }
}
