package com.example.customlistview2;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class CustomAdapter extends BaseAdapter {
    List<Person> persons = new ArrayList<Person>();
    Context context;
    LayoutInflater inflater;
    Person p;
    public CustomAdapter(Context context, List<Person> persons) {

        this.persons = persons;
        this.context = context;

    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int i) {
        return persons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater=LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.clistview_layout,null);
        ImageView imageviewLogo = v.findViewById(R.id.imageviewLogo);
        TextView textViewIsim = v.findViewById(R.id.textviewIsim);
        p = persons.get(i);
        textViewIsim.setText(p.ad);
        imageviewLogo.setImageResource(R.mipmap.ic_launcher);
//        textViewIsim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, p.ad, Toast.LENGTH_SHORT).show();
//            }
//        });


        return v;
    }
}

