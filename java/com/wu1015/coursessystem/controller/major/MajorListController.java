package com.wu1015.coursessystem.controller.major;

import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_Major_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.Major;
import com.wu1015.coursessystem.model.MajorController;

import java.util.ArrayList;
import java.util.List;

public class MajorListController extends MajorController {


    public MajorListController(Major major, SQLiteOpenHelper sqLiteOpenHelper) {
        super(major, sqLiteOpenHelper);
    }

    public List<Major> doList(){
        List<Major> majorList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_Major_NAME, null);
            while (result.moveToNext()){
                Major major=new Major();
                major.setM_Id(Integer.parseInt(result.getString(0)));
                major.setM_Name(result.getString(1));
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
