package com.example.fragmentwithsingleton;

import android.provider.ContactsContract;

public class DataSingleton {

    public int id;
    public String isim;

    private static DataSingleton mDataSinglton;

    private DataSingleton() {

    }

    public static DataSingleton getDataSingletonInstance(){
        if(mDataSinglton==null){
            mDataSinglton=new DataSingleton();
        }
        return mDataSinglton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }
}
