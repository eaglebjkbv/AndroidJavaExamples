package com.eaglebjkbv.flatradiobuttonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=findViewById(R.id.radioGroup1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioMg:
                        Toast.makeText(MainActivity.this, "MG Seçildi", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radioMl:
                        Toast.makeText(MainActivity.this, "Ml Seçildi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioUnit:
                        Toast.makeText(MainActivity.this, "Unit Seçildi", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
