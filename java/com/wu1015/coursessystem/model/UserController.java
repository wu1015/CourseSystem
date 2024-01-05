package com.wu1015.coursessystem.model;

import android.database.sqlite.SQLiteOpenHelper;


public class UserController {
    private User user;
    private SQLiteOpenHelper sqLiteOpenHelper;

    public UserController(User user, SQLiteOpenHelper sqLiteOpenHelper) {
        this.user = user;
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SQLiteOpenHelper getSqLiteOpenHelper() {
        return sqLiteOpenHelper;
    }

    public User doController() {
        return getUser();
    }
}
