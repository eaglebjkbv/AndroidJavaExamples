package com.example.customalertdialogprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    LoadingDialog loadingDialog;
    Button buttondialogGoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttondialogGoster=findViewById(R.id.buttonDialogoster);
        loadingDialog=new LoadingDialog(MainActivity.this);
        buttondialogGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.alertDialogShow();
            }
        });
    }
}
