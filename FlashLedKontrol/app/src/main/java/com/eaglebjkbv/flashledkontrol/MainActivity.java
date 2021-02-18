package com.eaglebjkbv.flashledkontrol;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {
    private ToggleButton buttonLedYak;
    private boolean hasFlash;
    private static final String TAG = "Ana";
    Context context=this;
    CameraManager cameraManager;
    String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLedYak=findViewById(R.id.buttonLedYak);
        hasFlash=getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
        if(!hasFlash){
            AlertDialog alertDialog=new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Problem :");
            alertDialog.setMessage("Flash bu cihazda kullanılamaz");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();
        }

        cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId=cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        buttonLedYak=findViewById(R.id.buttonLedYak);
        buttonLedYak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFlashLight(isChecked);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void switchFlashLight(boolean isChecked) {

        try {
            cameraManager.setTorchMode(cameraId,isChecked);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


}
