package com.wu1015.coursessystem.controller.stdclass;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Class_NAME;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Note_NAME;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Class;
import com.wu1015.coursessystem.model.ClassController;
import com.wu1015.coursessystem.model.User;

public class StdClassSelController extends ClassController {
    private User user;
    public StdClassSelController(Class aClass, SQLiteOpenHelper sqLiteOpenHelper) {
        super(aClass, sqLiteOpenHelper);
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Class doController() {
        Class aClass = this.getaClass();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_Class_NAME + " where C_Id=\"" + aClass.getC_Id() + "\"", null);
            if (result.getCount() <= 0) {
                int hhh = 1 / 0;
            } else {
//                todo 检查所有主键是否是自增的
                ContentValues values = new ContentValues();
                values.put("C_Sum", aClass.getC_Sum()-1);
                sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
                String where = "C_id=" + aClass.getC_Id();
                int i = sqLiteDatabase.update(SQL_TB_Class_NAME, values, where, null);
                if (i > 0) {
                    ContentValues values1=new ContentValues();
                    values1.put("U_Id",user.getU_Id());
                    values1.put("C_Id",aClass.getC_Id());
                    values1.put("C_Name",aClass.getC_Name());
                    values1.put("C_Teacher",aClass.getC_Teacher());
                    values1.put("C_Cost",aClass.getC_Cost());
                    values1.put("M_Id",aClass.getM_Id());
                    values1.put("M_Name",aClass.getM_Name());
                    sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
                    long ij=sqLiteDatabase.insert(SQL_TB_Note_NAME,null,values1);
                    if (ij!=-1){
                        aClass.setC_Flag(FLAG_SUCCESS);
                    }else {
                        aClass.setC_Flag(FLAG_ERROR);
                    }
                } else {
                    aClass.setC_Flag(FLAG_ERROR);
                }
            }
        } catch (Exception e) {
            aClass.setC_Flag(FLAG_ERROR);
            Log.d("TAG", "ClassEditController :" + aClass);
        } finally {
        }
        return aClass;
    }
}
