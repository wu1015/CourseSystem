package com.wu1015.coursessystem.controller.major;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Major_NAME;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Major;
import com.wu1015.coursessystem.model.MajorController;

public class MajorEditController extends MajorController {

    public MajorEditController(Major major, SQLiteOpenHelper sqLiteOpenHelper) {
        super(major, sqLiteOpenHelper);
    }

    @Override
    public Major doController() {
        Major major = this.getMajor();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_User_NAME + " where M_Id=\"" + major.getM_Id() + "\"", null);
            if (result.getCount() <= 0) {
                int hhh = 1 / 0;
            } else {
                ContentValues values = new ContentValues();
                values.put("M_Id", major.getM_Id());
                values.put("M_Name", major.getM_Name());
                sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
                String where = "M_id=" + major.getM_Id();
                int i = sqLiteDatabase.update(SQL_TB_Major_NAME, values, where, null);
                if (i > 0) {
                    major.setM_Flag(FLAG_SUCCESS);
                } else {
                    major.setM_Flag(FLAG_ERROR);
                }
            }
        } catch (Exception e) {
            major.setM_Flag(FLAG_ERROR);
            Log.d("TAG", "MajorEditController :" + major);
        } finally {
            result.close();
        }
        return major;
    }
}
