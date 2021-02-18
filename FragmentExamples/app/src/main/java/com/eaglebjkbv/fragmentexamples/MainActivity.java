package com.eaglebjkbv.fragmentexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonFragmentAAc;
    Button buttonFragmentAKapat;
    Button buttonFragmentBAc;
    Button buttonFragmentBKapat;
    Button buttonReplaceWithA, buttonReplaceWithB;
    Button buttonShowA,buttonHideA,buttonShowB,buttonHideB;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonFragmentAAc = findViewById(R.id.buttonFragAAc);
        buttonFragmentAKapat = findViewById(R.id.buttonFragAKapat);
        buttonFragmentBAc = findViewById(R.id.buttonFragBAc);
        buttonFragmentBKapat = findViewById(R.id.buttonFragBKapat);
        buttonReplaceWithA = findViewById(R.id.butonReplaceA);
        buttonReplaceWithB = findViewById(R.id.butonReplaceB);

        buttonShowA=findViewById(R.id.butonShowA);
        buttonHideA=findViewById(R.id.butonHideA);
        buttonShowB=findViewById(R.id.butonShowB);
        buttonHideB=findViewById(R.id.butonHideB);






        manager = getSupportFragmentManager();
        FragmentA fragmentA=new FragmentA();
        FragmentB fragmentB=new FragmentB();

        FragmentTransaction fragmentTransaction=manager.beginTransaction();
       // fragmentTransaction.add(R.id.fragment_container,fragmentB,"fragB");
        fragmentTransaction.add(R.id.fragment_container,fragmentA,"fragA");
        fragmentTransaction.commit();

        buttonShowA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1=manager.beginTransaction();
                fragmentTransaction1.show(manager.findFragmentByTag("fragA"));
                fragmentTransaction1.commit();
            }
        });
        buttonHideA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction1=manager.beginTransaction();
                fragmentTransaction1.hide(manager.findFragmentByTag("fragA"));
                fragmentTransaction1.commit();
            }
        });

/*
        buttonReplaceWithA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = new FragmentA();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragmentA, "fragA");
                fragmentTransaction.commit();
            }
        });
        buttonReplaceWithB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentB = new FragmentB();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragmentB, "fragB");
                fragmentTransaction.commit();
            }
        });


        buttonFragmentAAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = new FragmentA();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, fragmentA, "fragA");
                fragmentTransaction.commit();
                Toast.makeText(MainActivity.this, "Frag a ac", Toast.LENGTH_SHORT).show();
            }
        });
        buttonFragmentAKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.remove(manager.findFragmentByTag("fragA"));
                fragmentTransaction.commit();
            }
        });
        buttonFragmentBAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentB = new FragmentB();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, fragmentB, "fragB");
                fragmentTransaction.commit();
                Toast.makeText(MainActivity.this, "Frag b ac", Toast.LENGTH_SHORT).show();
            }
        });
        buttonFragmentBKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.remove(manager.findFragmentByTag("fragB"));
                fragmentTransaction.commit();
            }
        });
*/

    }
}
