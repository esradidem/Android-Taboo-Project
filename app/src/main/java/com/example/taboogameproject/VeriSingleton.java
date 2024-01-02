package com.example.taboogameproject;

public class VeriSingleton
{
    private static VeriSingleton instance;
    private String takim;
    private int skor1;
    private int skor2;

    private VeriSingleton()
    {}

    public static synchronized VeriSingleton getInstance()
    {
        if (instance == null)
        {
            instance =new VeriSingleton();
        }
        return instance;
    }

    public String getTakim()
    {
        return takim;
    }

    public void setTakim(String takim)
    {
        this.takim = takim;
    }

    public int getSkor1()
    {
        return skor1;
    }

    public void setSkor1(int skor)
    {
        this.skor1 = skor;
    }

    public int getSkor2() {
        return skor2;
    }

    public void setSkor2(int skor2) {
        this.skor2 = skor2;
    }
}
