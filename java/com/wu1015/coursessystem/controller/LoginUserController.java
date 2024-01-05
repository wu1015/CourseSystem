package com.wu1015.coursessystem.controller;


import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.model.UserController;

public class LoginUserController extends UserController {

    public LoginUserController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        super(user, sqLiteOpenHelper);
    }

    @Override
    public User doController() {
        User user = this.getUser();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        String pwd = "";
        Cursor result = null;

        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_User_NAME + " where U_Id=\"" + user.getU_Id() + "\"", null);
            if (result.getCount() <= 0) {
                int hhh = 1 / 0;
            } else {
//                        cursor需要移动到指定行才能使用getString()方法来获取列数据
//                        从-1开始，0为第一行，但第一行为行名
                result.move(1);
//                        第2列
                pwd = result.getString(1);
                if (user.getU_Pwd().equals(pwd) && !pwd.equals("")) {
//                            验证账户密码是否正确
                    try {
                        user.setU_No(result.getString(2));
                        user.setU_Name(result.getString(3));
                        user.setU_Sex(result.getString(4));
                        user.setU_Mail(result.getString(5));
                        user.setU_Grade(result.getString(6));
                        user.setU_Major(result.getString(7));
                        user.setU_Img(result.getString(8).getBytes());
                    } catch (Exception e) {

                    }
                    user.setU_Flag(result.getString(9));
                } else {
                    int hhh = 1 / 0;
                }
            }
        } catch (Exception e) {
            user.setU_Flag(FLAG_ERROR);
            Log.d("TAG", "LoginController : " + user.getU_Pwd() + pwd);
        } finally {
        }
        return user;
    }
}
