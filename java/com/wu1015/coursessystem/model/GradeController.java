package com.wu1015.coursessystem.model;

import android.database.sqlite.SQLiteOpenHelper;


public class GradeController {
    private Grade grade;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public GradeController(Grade grade, SQLiteOpenHelper sqLiteOpenHelper) {
        this.grade = grade;
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setMajor(Grade grade) {
        this.grade = grade;
    }

    public void setSqLiteOpenHelper(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public SQLiteOpenHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }

    public Grade doController() {
        return getGrade();
    }
}
