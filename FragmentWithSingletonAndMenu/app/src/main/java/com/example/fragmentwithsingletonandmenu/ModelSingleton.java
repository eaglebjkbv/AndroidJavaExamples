package com.example.fragmentwithsingletonandmenu;

public class ModelSingleton {

    private static ModelSingleton mInstance;
    public int aKenari=0,bKenari=0,cKenari=0,alanSonuc,hacimSonuc;
    private ModelSingleton() {
    }

    public static ModelSingleton getInstance(){
        if(mInstance==null){
            mInstance=new ModelSingleton();
        }
        return mInstance;
    }
}
