package com.wu1015.coursessystem.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.wu1015.coursessystem.model.User;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String SQL_TB_User_NAME = "user";
    public static final String SQL_TB_Major_NAME = "major";
    public static final String SQL_TB_Grade_NAME = "grade";
    public static final String SQL_TB_Class_NAME = "class";
    public static final String SQL_TB_Note_NAME = "note";

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        此处必须是integer而不能是int,否则会出现无法创建的错误
//        SQLite没有枚举类型，因此用检查约束的方式创建
        sqLiteDatabase.execSQL("create table if not exists " + SQL_TB_User_NAME + "(U_Id varchar(45) primary key ," +
                "U_Pwd varchar(45)," +
                "U_No varchar(45)," +
                "U_Name varchar(45)," +
                "U_Sex varchar(25) check(U_Sex in ('male','female'))," +
                "U_Mail varchar(45)," +
                "U_Grade varchar(45)," +
                "U_Major varchar(45)," +
                "U_Img Blob," +
                "U_Flag varchar(45))");

        sqLiteDatabase.execSQL("create table if not exists " + SQL_TB_Major_NAME + "(M_Id integer primary key autoincrement,M_Name varchar(45))");
        sqLiteDatabase.execSQL("create table if not exists " + SQL_TB_Grade_NAME + "(G_Id integer primary key autoincrement,G_Name varchar(45))");
        sqLiteDatabase.execSQL("create table if not exists " + SQL_TB_Class_NAME + "(C_Id integer primary key autoincrement," +
                "C_Name varchar(45)," +
                "C_Teacher varchar(45)," +
                "C_Cost varchat(45)," +
                "C_Num int," +
                "C_Sum int," +
                "M_Id int," +
                "M_Name varchar(45))");

        sqLiteDatabase.execSQL("create table if not exists " + SQL_TB_Note_NAME + "(id integer primary key autoincrement," +
                "U_Id int," +
                "C_Id int," +
                "C_Name varchar(45)," +
                "C_Teacher varchar(45)," +
                "C_Cost varchat(45)," +
                "M_Id int," +
                "M_Name varchar(45)," +
                "note text)");

        try {
//            插入演示数据
            sqLiteDatabase.execSQL("insert into " + SQL_TB_User_NAME + "(U_Id,U_Pwd,U_Name,U_Sex,U_Mail,U_Flag) values(\"S00001\",\"S00001\",\"管理员01\",\"male\",\"S00001@qq.com\",\"" + User.FLAG_ADMIN + "\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_User_NAME + " values(\"User01\",\"User01\",\"2115080001\",\"用户01\",\"male\",\"User01@qq.com\",\"2021级\",\"软件工程\",\"\",\"" + User.FLAG_USER + "\")");

            sqLiteDatabase.execSQL("insert into " + SQL_TB_Major_NAME + "(M_Id,M_Name) values(1,\"软件工程\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_Major_NAME + "(M_Id,M_Name) values(2,\"大数据\")");

            sqLiteDatabase.execSQL("insert into " + SQL_TB_Grade_NAME + " values(1,\"2021级\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_Grade_NAME + " values(2,\"2022级\")");

            sqLiteDatabase.execSQL("insert into " + SQL_TB_Class_NAME + " values(1,\"面向对象程序设计\",\"A老师\",\"2学分\",20,19,1,\"软件工程\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_Class_NAME + " values(2,\"UML程序设计\",\"B老师\",\"3学分\",20,20,1,\"软件工程\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_Class_NAME + " values(3,\"C语言程序设计\",\"C老师\",\"1学分\",20,20,1,\"软件工程\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_Class_NAME + " values(4,\"大数据分析\",\"C老师\",\"2学分\",20,20,2,\"大数据\")");
            sqLiteDatabase.execSQL("insert into " + SQL_TB_Class_NAME + " values(5,\"人工智能\",\"C老师\",\"1学分\",20,20,2,\"大数据\")");

            sqLiteDatabase.execSQL("insert into " + SQL_TB_Note_NAME + " values(1,\"User01\",1,\"面向对象程序设计\",\"A老师\",\"2学分\",1,\"软件工程\",\"\")");
        } catch (Exception e) {
            Log.d("TAG", "SqlHelp onCreate: ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        刷新数据库，即删除重建
        sqLiteDatabase.execSQL("drop table if not exists " + SQL_TB_Class_NAME);
        sqLiteDatabase.execSQL("drop table if not exists " + SQL_TB_Note_NAME);
        sqLiteDatabase.execSQL("drop table if not exists " + SQL_TB_Grade_NAME);
        sqLiteDatabase.execSQL("drop table if not exists " + SQL_TB_Major_NAME);
        sqLiteDatabase.execSQL("drop table if not exists " + SQL_TB_User_NAME);
        onCreate(sqLiteDatabase);
    }
}
