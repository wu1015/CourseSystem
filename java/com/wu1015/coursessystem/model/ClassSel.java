package com.wu1015.coursessystem.model;

public class ClassSel {
    private int CS_Id;
    private int C_Id;
    private String C_Name;
    private String C_Teacher;
    private String C_Cost;
    private int M_Id;
    private String M_Name;
    private String CS_Flag;
    private String CS_Note;

    public ClassSel() {
    }

    public ClassSel(int CS_Id, int c_Id, String c_Name, String c_Teacher, String c_Cost, int m_Id, String m_Name, String CS_Flag, String CS_Note) {
        this.CS_Id = CS_Id;
        C_Id = c_Id;
        C_Name = c_Name;
        C_Teacher = c_Teacher;
        C_Cost = c_Cost;
        M_Id = m_Id;
        M_Name = m_Name;
        this.CS_Flag = CS_Flag;
        this.CS_Note = CS_Note;
    }

    public int getCS_Id() {
        return CS_Id;
    }

    public void setCS_Id(int CS_Id) {
        this.CS_Id = CS_Id;
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

    public String getCS_Flag() {
        return CS_Flag;
    }

    public void setCS_Flag(String CS_Flag) {
        this.CS_Flag = CS_Flag;
    }

    public String getCS_Note() {
        return CS_Note;
    }

    public void setCS_Note(String CS_Note) {
        this.CS_Note = CS_Note;
    }
}

