package com.wu1015.coursessystem.controller.major;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Major_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Major;
import com.wu1015.coursessystem.model.MajorController;

public class MajorDelController extends MajorController {


    public MajorDelController(Major major, SQLiteOpenHelper sqLiteOpenHelper) {
        super(major, sqLiteOpenHelper);
    }

    @Override
    public Major doController() {
        Major major = this.getMajor();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            String where = "M_id=" + major.getM_Id();
            long i = sqLiteDatabase.delete(SQL_TB_Major_NAME, where, null);
            if (i > 0) {
                major.setM_Flag(FLAG_SUCCESS);
            } else {
                major.setM_Flag(FLAG_ERROR);
            }
        } catch (Exception e) {
            major.setM_Flag(FLAG_ERROR);
            Log.d("TAG", "MajorDelController :" + major);
        } finally {
            result.close();
        }
        return major;
    }
}
