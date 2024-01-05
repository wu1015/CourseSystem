package com.wu1015.coursessystem.model;

public class Grade {
    private int G_Id;
    private String G_Name;
    private String G_Flag;


    public Grade() {
    }

    public int getG_Id() {
        return G_Id;
    }

    public void setG_Id(int g_Id) {
        G_Id = g_Id;
    }

    public String getG_Name() {
        return G_Name;
    }

    public void setG_Name(String g_Name) {
        G_Name = g_Name;
    }

    public String getG_Flag() {
        return G_Flag;
    }

    public void setG_Flag(String g_Flag) {
        G_Flag = g_Flag;
    }

    public Grade(int g_Id, String g_Name, String g_Flag) {
        G_Id = g_Id;
        G_Name = g_Name;
        G_Flag = g_Flag;
    }
}
