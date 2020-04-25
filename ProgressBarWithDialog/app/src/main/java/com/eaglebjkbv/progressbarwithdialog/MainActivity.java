package com.eaglebjkbv.progressbarwithdialog;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonTikla=findViewById(R.id.buttontikla);
        buttonTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog(true);
            }
        });
    }


    private void setDialog(boolean show){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //View view = getLayoutInflater().inflate(R.layout.progress);
        builder.setView(R.layout.progress);
        Dialog dialog = builder.create();
        if (show)dialog.show();
        else dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setDialog(false);
    }
}
