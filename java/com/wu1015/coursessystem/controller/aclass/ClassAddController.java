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
import com.wu1015.coursessystem.model.UserController;

public class ClassAddController extends ClassController {
    public ClassAddController(Class aClass, SQLiteOpenHelper sqLiteOpenHelper) {
        super(aClass, sqLiteOpenHelper);
    }


    @Override
    public Class doController() {
        Class aClass = this.getaClass();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            ContentValues values = new ContentValues();
            values.put("C_Name", aClass.getC_Name());
            values.put("C_Teacher", aClass.getC_Teacher());
            values.put("C_Cost", aClass.getC_Cost());
            values.put("C_Num", aClass.getC_Num());
            values.put("C_Sum", aClass.getC_Num());
            values.put("M_Id", aClass.getM_Id());
            values.put("M_Name", aClass.getM_Name());
            long i=sqLiteDatabase.insert(SQL_TB_Class_NAME, null, values);
            if (i != -1) {
                aClass.setC_Flag(FLAG_SUCCESS);
            } else {
                aClass.setC_Flag(FLAG_ERROR);
            }
        } catch (Exception e) {
            aClass.setC_Flag(FLAG_ERROR);
            Log.d("TAG", "ClassAddController :" + aClass);
        } finally {
        }
        return aClass;
    }
}
