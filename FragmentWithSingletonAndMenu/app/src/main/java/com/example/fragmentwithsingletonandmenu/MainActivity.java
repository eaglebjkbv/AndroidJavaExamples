package com.example.fragmentwithsingletonandmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    Button buttonAlanHesapla,buttonHacimHesapla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentA fragmentA=new FragmentA();

        buttonAlanHesapla=findViewById(R.id.buttonAlanHesapla);
        buttonHacimHesapla=findViewById(R.id.buttonHacimHesapla);
        manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        ft.add(R.id.container,fragmentA,"fragA");
        ft.commit();

        buttonAlanHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentA fragmentA=new FragmentA();
                FragmentTransaction ft=manager.beginTransaction();
                ft.replace(R.id.container,fragmentA,"fragA");
                ft.commit();
            }
        });
        buttonHacimHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentB fragmentB=new FragmentB();
                FragmentTransaction ft=manager.beginTransaction();
                ft.replace(R.id.container,fragmentB,"fragB");
                ft.commit();
            }
        });



    }
}
