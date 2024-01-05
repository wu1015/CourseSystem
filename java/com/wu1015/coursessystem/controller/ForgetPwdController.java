package com.wu1015.coursessystem.controller;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.model.UserController;

public class ForgetPwdController extends UserController {
    public ForgetPwdController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        super(user, sqLiteOpenHelper);
    }

    @Override
    public User doController() {
        User user = this.getUser();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        String mail;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_User_NAME + " where U_Id=\"" + user.getU_Id() + "\"", null);
            if (result.getCount() <= 0) {
                int hhh = 1 / 0;
            } else {
//                        cursor需要移动到指定行才能使用getString()方法来获取列数据
//                        从-1开始，0为第一行，但第一行为行名
                result.move(1);
//                        第2列
                mail = result.getString(5);
                if (user.getU_Mail().equals(mail) && !mail.equals("")) {
//                            验证账户邮箱是否正确
                    sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
                    ContentValues values = new ContentValues();
                    String where = "U_id=" + user.getU_Id();
                    values.put("U_Pwd", user.getU_Pwd());
                    int i = sqLiteDatabase.update(SQL_TB_User_NAME, values, where, null);
                    if (i > 0) {
                        user.setU_Flag(FLAG_SUCCESS);
                    } else {
                        user.setU_Flag(FLAG_ERROR);
                    }
                } else {
                    int hhh = 1 / 0;
                }
            }
        } catch (Exception e) {
            user.setU_Flag(FLAG_ERROR);
            Log.d("TAG", "ForgetPwdController :" + user);
        } finally {
        }
        return user;
    }
}
