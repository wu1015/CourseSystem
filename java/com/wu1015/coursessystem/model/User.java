package com.wu1015.coursessystem.model;

public class User {
    public static final String FLAG_USER = "10086";
    public static final String FLAG_ADMIN = "1008613";
    public static final String FLAG_SUCCESS = "88888";
    public static final String FLAG_ERROR = "99999";
    private String U_Id;
    private String U_Pwd;
    private String U_No;
    private String U_Name;
    private String U_Sex;
    private String U_Mail;
    private String U_Grade;
    private String U_Major;
//    todo 到时候换成blob，如果需要有头像的话
    private byte[] U_Img;
    private String U_Flag;

    public User() {
    }

    public User(String u_Id, String u_Pwd, String u_No, String u_Name, String u_Sex, String u_Mail, String u_Grade, String u_Major, byte[] u_Img, String u_Flag) {
        U_Id = u_Id;
        U_Pwd = u_Pwd;
        U_No = u_No;
        U_Name = u_Name;
        U_Sex = u_Sex;
        U_Mail = u_Mail;
        U_Grade = u_Grade;
        U_Major = u_Major;
        U_Img = u_Img;
        U_Flag = u_Flag;
    }

    public String getU_Id() {
        return U_Id;
    }

    public void setU_Id(String u_Id) {
        U_Id = u_Id;
    }

    public String getU_Pwd() {
        return U_Pwd;
    }

    public void setU_Pwd(String u_Pwd) {
        U_Pwd = u_Pwd;
    }

    public String getU_No() {
        return U_No;
    }

    public void setU_No(String u_No) {
        U_No = u_No;
    }

    public String getU_Name() {
        return U_Name;
    }

    public void setU_Name(String u_Name) {
        U_Name = u_Name;
    }

    public String getU_Sex() {
        return U_Sex;
    }

    public void setU_Sex(String u_Sex) {
        U_Sex = u_Sex;
    }

    public String getU_Mail() {
        return U_Mail;
    }

    public void setU_Mail(String u_Mail) {
        U_Mail = u_Mail;
    }

    public String getU_Grade() {
        return U_Grade;
    }

    public void setU_Grade(String u_Grade) {
        U_Grade = u_Grade;
    }

    public String getU_Major() {
        return U_Major;
    }

    public void setU_Major(String u_Major) {
        U_Major = u_Major;
    }

    public byte[] getU_Img() {
        return U_Img;
    }

    public void setU_Img(byte[] u_Img) {
        U_Img = u_Img;
    }

    public String getU_Flag() {
        return U_Flag;
    }

    public void setU_Flag(String u_Flag) {
        U_Flag = u_Flag;
    }
}
