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

public class ClassDelController extends ClassController {
    public ClassDelController(Class aClass, SQLiteOpenHelper sqLiteOpenHelper) {
        super(aClass, sqLiteOpenHelper);
    }


    @Override
    public Class doController() {
        Class aClass = this.getaClass();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            String where = "C_id=" + aClass.getC_Id();
            long i = sqLiteDatabase.delete(SQL_TB_Class_NAME, where, null);
            if (i>0) {
                aClass.setC_Flag(FLAG_SUCCESS);
            } else {
                aClass.setC_Flag(FLAG_ERROR);
            }
        } catch (Exception e) {
            aClass.setC_Flag(FLAG_ERROR);
            Log.d("TAG", "ClassDelController :" + aClass);
        } finally {
        }
        return aClass;
    }
}
