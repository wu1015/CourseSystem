package com.wu1015.coursessystem.controller.user;

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

public class UserAddController extends UserController {
//    注册
    public UserAddController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        super(user, sqLiteOpenHelper);
    }

    @Override
    public User doController() {
        User user = this.getUser();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            ContentValues values = new ContentValues();
            if (user.getU_Pwd()==null || user.getU_Id()==null){
                int i=1/0;
            }
            values.put("U_Id", user.getU_Id());
            values.put("U_Pwd",user.getU_Pwd());
            values.put("U_No", user.getU_No());
            values.put("U_Name", user.getU_Name());
            values.put("U_Sex", user.getU_Sex());
            values.put("U_Mail", user.getU_Mail());
            values.put("U_Grade", user.getU_Grade());
            values.put("U_Major", user.getU_Major());
            values.put("U_Flag", user.getU_Flag());
            long i=sqLiteDatabase.insert(SQL_TB_User_NAME, null, values);
            if (i != -1) {
                user.setU_Flag(FLAG_SUCCESS);
            } else {
                user.setU_Flag(FLAG_ERROR);
            }
        } catch (Exception e) {
            user.setU_Flag(FLAG_ERROR);
            Log.d("TAG", "UserAddController :" + user);
        } finally {
        }
        return user;
    }
}
