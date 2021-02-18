package com.example.customlistview2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Person> persons=new ArrayList<Person>();
    ListView liste;
    Context context=this;
    CustomAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        persons.add(new Person(10,"Bülent","Vardal","resim1.jpg"));
        persons.add(new Person(10,"Ahmet","Zapir","resim2.jpg"));
        persons.add(new Person(10,"Gürcan","Bıldır","resim3.jpg"));
        persons.add(new Person(10,"İsmail","Aktaş","resim4.jpg"));
        persons.add(new Person(10,"Murat","Özdeveci","resim5.jpg"));
        persons.add(new Person(10,"Mustafa","Özer","resim6.jpg"));
        persons.add(new Person(10,"Bülent","Vardal","resim1.jpg"));
        persons.add(new Person(10,"Ahmet","Zapir","resim2.jpg"));
        persons.add(new Person(10,"Gürcan","Bıldır","resim3.jpg"));
        persons.add(new Person(10,"İsmail","Aktaş","resim4.jpg"));
        persons.add(new Person(10,"Murat","Özdeveci","resim5.jpg"));
        persons.add(new Person(10,"Mustafa","Özer","resim6.jpg"));

        liste=findViewById(R.id.liste);
        adp=new CustomAdapter(context,persons);
        liste.setAdapter(adp);
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o=adp.getItem(i);
                Person p=(Person)o;
                Toast.makeText(context, p.ad, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
