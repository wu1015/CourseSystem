package com.wu1015.coursessystem.controller.aclass;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Class_NAME;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.ClassController;
import com.wu1015.coursessystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class ClassListController extends ClassController {
    public ClassListController(Class aClass, SQLiteOpenHelper sqLiteOpenHelper) {
        super(aClass, sqLiteOpenHelper);
    }


    public List<Class> doList() {
        List<Class> classList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_Class_NAME + " where M_Name=\"" + getaClass().getM_Name() + "\"", null);
            while (result.moveToNext()) {
                if (result.getInt(5) > 0) {

                    Class aClass = new Class();
                    aClass.setC_Id(Integer.parseInt(result.getString(0)));
                    aClass.setC_Name(result.getString(1));
                    aClass.setC_Teacher(result.getString(2));
                    aClass.setC_Cost(result.getString(3));
                    aClass.setC_Num(Integer.parseInt(result.getString(4)));
                    aClass.setC_Sum(Integer.parseInt(result.getString(5)));
                    aClass.setM_Id(Integer.parseInt(result.getString(6)));
                    aClass.setM_Name(result.getString(7));
                    classList.add(aClass);
                }
            }
        } catch (Exception e) {
            Log.d("TAG", "ClassListController :" + getaClass().getM_Name());
        } finally {
        }
        return classList;
    }

    public List<Class> doList2() {
        List<Class> classList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_Class_NAME + "", null);
            while (result.moveToNext()) {
                Class aClass = new Class();
                aClass.setC_Id(Integer.parseInt(result.getString(0)));
                aClass.setC_Name(result.getString(1));
                aClass.setC_Teacher(result.getString(2));
                aClass.setC_Cost(result.getString(3));
                aClass.setC_Num(Integer.parseInt(result.getString(4)));
                aClass.setC_Sum(Integer.parseInt(result.getString(5)));
                aClass.setM_Id(Integer.parseInt(result.getString(6)));
                aClass.setM_Name(result.getString(7));
                classList.add(aClass);
            }
        } catch (Exception e) {
            Log.d("TAG", "ClassListController :" + getaClass().getM_Name());
        } finally {
        }
        return classList;
    }
}
