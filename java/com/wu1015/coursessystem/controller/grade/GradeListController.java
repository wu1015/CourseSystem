package com.wu1015.coursessystem.controller.grade;

import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Grade_NAME;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Major_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Grade;
import com.wu1015.coursessystem.model.Major;
import com.wu1015.coursessystem.model.MajorController;

import java.util.ArrayList;
import java.util.List;

public class GradeListController  {

private  Grade grade;
private SQLiteOpenHelper sqLiteOpenHelper;
    public GradeListController(Grade grade, SQLiteOpenHelper sqLiteOpenHelper) {
        this.grade=grade;
        this.sqLiteOpenHelper=sqLiteOpenHelper;
    }

    public SQLiteOpenHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }

    public Grade getGrade() {
        return grade;
    }

    public List<Grade> doList(){
        List<Grade> majorList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_Grade_NAME, null);
            while (result.moveToNext()){
                Grade major=new Grade();
                major.setG_Id(Integer.parseInt(result.getString(0)));
                major.setG_Name(result.getString(1));
                majorList.add(major);
            }
        } catch (Exception e) {
            Log.d("TAG", "MAjorListController :" + majorList);
        } finally {
            result.close();
        }
        return majorList;
    }
}
