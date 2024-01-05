package com.wu1015.coursessystem.model;

public class Class {
    private int C_Id;
    private String C_Name;
    private String C_Teacher;
    private String C_Cost;
    private int C_Num;
    private int C_Sum;
    private int M_Id;
    private String M_Name;
    private String C_Flag;

    public Class() {
    }

    public int getC_Id() {
        return C_Id;
    }

    public void setC_Id(int c_Id) {
        C_Id = c_Id;
    }

    public String getC_Name() {
        return C_Name;
    }

    public void setC_Name(String c_Name) {
        C_Name = c_Name;
    }

    public String getC_Teacher() {
        return C_Teacher;
    }

    public void setC_Teacher(String c_Teacher) {
        C_Teacher = c_Teacher;
    }

    public String getC_Cost() {
        return C_Cost;
    }

    public void setC_Cost(String c_Cost) {
        C_Cost = c_Cost;
    }

    public int getC_Num() {
        return C_Num;
    }

    public void setC_Num(int c_Num) {
        C_Num = c_Num;
    }

    public int getC_Sum() {
        return C_Sum;
    }

    public void setC_Sum(int c_Sum) {
        C_Sum = c_Sum;
    }

    public int getM_Id() {
        return M_Id;
    }

    public void setM_Id(int m_Id) {
        M_Id = m_Id;
    }

    public String getM_Name() {
        return M_Name;
    }

    public void setM_Name(String m_Name) {
        M_Name = m_Name;
    }

    public String getC_Flag() {
        return C_Flag;
    }

    public void setC_Flag(String c_Flag) {
        C_Flag = c_Flag;
    }

    public Class(int c_Id, String c_Name, String c_Teacher, String c_Cost, int c_Num, int c_Sum, int m_Id, String m_Name, String c_Flag) {
        C_Id = c_Id;
        C_Name = c_Name;
        C_Teacher = c_Teacher;
        C_Cost = c_Cost;
        C_Num = c_Num;
        C_Sum = c_Sum;
        M_Id = m_Id;
        M_Name = m_Name;
        C_Flag = c_Flag;
    }
}
