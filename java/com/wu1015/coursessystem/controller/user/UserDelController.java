package com.wu1015.coursessystem.controller.user;

import static com.wu1015.coursessystem.model.User.FLAG_ERROR;
import static com.wu1015.coursessystem.model.User.FLAG_SUCCESS;
import static com.wu1015.coursessystem.utils.DBOpenHelper.SQL_TB_User_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.model.UserController;

public class UserDelController extends UserController {
    public UserDelController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        super(user, sqLiteOpenHelper);
    }

    @Override
    public User doController() {
        User user = this.getUser();
        SQLiteDatabase sqLiteDatabase = this.getSqLiteOpenHelper().getWritableDatabase();
        Cursor result = null;
        try {
            String where = "U_id=" + user.getU_Id();
            long i = sqLiteDatabase.delete(SQL_TB_User_NAME, where, null);
            if (i > 0) {
                user.setU_Flag(FLAG_SUCCESS);
            } else {
                user.setU_Flag(FLAG_ERROR);
            }
        } catch (Exception e) {
            user.setU_Flag(FLAG_ERROR);
            Log.d("TAG", "UserDelController :" + user);
        } finally {
            result.close();
        }
        return user;
    }
}
