package com.wu1015.coursessystem.controller.stdclass;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Class_NAME;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Note_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.ClassController;
import com.wu1015.coursessystem.model.ClassSel;
import com.wu1015.coursessystem.model.User;

public class StdClassDelController extends ClassController {
    private User user;
    private ClassSel classSel;

    public StdClassDelController(Class aClass, SQLiteOpenHelper sqLiteOpenHelper) {
        super(aClass, sqLiteOpenHelper);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClassSel(ClassSel classSel) {
        this.classSel = classSel;
    }

    @Override
    public Class doController() {
        Class aClass = this.getaClass();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_Note_NAME + " where id=\"" + classSel.getCS_Id() + "\"", null);
            if (result.getCount() <= 0) {
                int hhh = 1 / 0;
            } else {
                result = sqLiteDatabase.rawQuery("select C_Sum from " + SQL_TB_Class_NAME + " where C_Id=" + aClass.getC_Id() + "",null);
                if (result.getCount()<=0){
                    int hh=1/0;
                }
                ContentValues values = new ContentValues();
                result.moveToNext();
                values.put("C_Sum", Integer.parseInt(result.getString(0)) + 1);
                sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
                String where = "C_id=" + aClass.getC_Id();
                int i = sqLiteDatabase.update(SQL_TB_Class_NAME, values, where, null);
                if (i > 0) {
                    String where2 = "id=" +classSel.getCS_Id();
                    long ij = sqLiteDatabase.delete(SQL_TB_Note_NAME, where2,null);
                    if (ij != -1) {
                        aClass.setC_Flag(FLAG_SUCCESS);
                    } else {
                        aClass.setC_Flag(FLAG_ERROR);
                    }
                } else {
                    aClass.setC_Flag(FLAG_ERROR);
                }
            }
        } catch (Exception e) {
            aClass.setC_Flag(FLAG_ERROR);
            Log.d("TAG", "ClassEditController :" + classSel.getCS_Id());
        } finally {
        }
        return aClass;
    }
}
