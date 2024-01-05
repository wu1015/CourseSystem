package com.wu1015.coursessystem.controller.user;

import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.model.UserController;

import java.util.ArrayList;
import java.util.List;

public class UserListController extends UserController {
    public UserListController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        super(user, sqLiteOpenHelper);
    }

    public List<User> doList(){
        List<User> userList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_User_NAME, null);
            while (result.moveToNext()){
                User user=new User();
                user.setU_Id(result.getString(0));
                user.setU_No(result.getString(2));
                user.setU_Name(result.getString(3));
                user.setU_Sex(result.getString(4));
                user.setU_Mail(result.getString(5));
                user.setU_Grade(result.getString(6));
                user.setU_Major(result.getString(7));
                user.setU_Img(result.getString(8).getBytes());
                user.setU_Flag(result.getString(9));
                userList.add(user);
            }
        } catch (Exception e) {
            Log.d("TAG", "UserListController :" + userList);
        } finally {
            result.close();
        }
        return userList;
    }
}
