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

public class UserEditController extends UserController {
    public UserEditController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        super(user, sqLiteOpenHelper);
    }

    @Override
    public User doController() {
        User user = this.getUser();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getReadableDatabase();
        Cursor result = null;
        try {
            result = sqLiteDatabase.rawQuery("select * from " + SQL_TB_User_NAME + " where U_Id=\"" + user.getU_Id() + "\"", null);
            if (result.getCount() <= 0) {
                int hhh = 1 / 0;
            } else {
                Log.d("TAG", "d1111oController: ");
                ContentValues values = new ContentValues();
//                values.put("U_Id", user.getU_Id());
                values.put("U_No", user.getU_No());
                if (user.getU_Pwd()!=null && !user.getU_Pwd().equals("")) {
                    values.put("U_Pwd", user.getU_Pwd());
                    Log.d("TAG", "do0000Controller: ");
                }
                Log.d("TAG", "000d1111oController: ");
                values.put("U_Name", user.getU_Name());
                values.put("U_Sex", user.getU_Sex());
                values.put("U_Mail", user.getU_Mail());
                values.put("U_Grade", user.getU_Grade());
                values.put("U_Major", user.getU_Major());
//                values.put("U_Img", user.getU_Img());
//                values.put("U_Flag", user.getU_Flag());
                sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
                String where = "U_Id=\"" + user.getU_Id()+"\"";
//                sqLiteDatabase.insert(SQL_TB_User_NAME,null,values);
                int i = sqLiteDatabase.update(SQL_TB_User_NAME, values, where, null);
                if (i > 0) {
                    user.setU_Flag(FLAG_SUCCESS);
                } else {
                    user.setU_Flag(FLAG_ERROR);
                }
            }
        } catch (Exception e) {
            user.setU_Flag(FLAG_ERROR);
            Log.d("TAG", "UserEditController :" + user);
        } finally {
            result.close();
        }
        return user;
    }
}
