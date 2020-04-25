package com.example.fragmentwithsingleton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements MyListener {
    DataSingleton dataSingleton;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSingleton=DataSingleton.getDataSingletonInstance();
        dataSingleton.setId(10);
        dataSingleton.setIsim("Bülent");

        manager=getSupportFragmentManager();
        FragmentA fragmentA=new FragmentA();
        FragmentB fragmentB=new FragmentB();
        FragmentTransaction ft=manager.beginTransaction();
        ft.add(R.id.containera,fragmentA,"fragA");
        ft.add(R.id.containerb,fragmentB,"fragB");
        ft.commit();



    }

    @Override
    public void myListener() {
        Log.d("Mesaj","My listener Çalıştı");
        FragmentTransaction ft=manager.beginTransaction();
        FragmentA fragmentA= (FragmentA) manager.findFragmentByTag("fragA");
        FragmentB fragmentB=(FragmentB) manager.findFragmentByTag("fragB");

            ft.detach(fragmentA);
            ft.attach(fragmentA);

            ft.detach(fragmentB);
            ft.attach(fragmentB);

        ft.commit();

    }
}
