package com.wu1015.coursessystem.controller.major;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Major_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Major;
import com.wu1015.coursessystem.model.MajorController;

public class MajorAddController extends MajorController {
    public MajorAddController(Major major, SQLiteOpenHelper sqLiteOpenHelper) {
        super(major, sqLiteOpenHelper);
    }

    @Override
    public Major doController() {
        Major major=this.getMajor();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            ContentValues values = new ContentValues();
            values.put("M_Name", major.getM_Name());
            long i=sqLiteDatabase.insert(SQL_TB_Major_NAME, null, values);
            if (i != -1) {
                major.setM_Flag(FLAG_SUCCESS);
            } else {
                major.setM_Flag(FLAG_ERROR);
            }
        } catch (Exception e) {
            major.setM_Flag(FLAG_ERROR);
            Log.d("TAG", "MajorAddController :" + major);
        } finally {
            result.close();
        }
        return major;
    }
}
