package com.eaglebjkbv.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context=this;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonGoster=findViewById(R.id.buttonGoster);
        buttonGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog dialog=new MyDialog(context);

                dialog.show();

                TextView textMesaj=(TextView) dialog.findViewById(R.id.textMesaj);
                textMesaj.setText("Selamlar");

            }
        });
    }
}
