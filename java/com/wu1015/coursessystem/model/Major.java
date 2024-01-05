package com.wu1015.coursessystem.model;

public class Major {
    private int M_Id;
    private String M_Name;
    private String M_Flag;


    public Major() {
    }

    public Major(int m_Id, String m_Name, String m_Flag) {
        M_Id = m_Id;
        M_Name = m_Name;
        M_Flag = m_Flag;
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

    public String getM_Flag() {
        return M_Flag;
    }

    public void setM_Flag(String m_Flag) {
        M_Flag = m_Flag;
    }
}
