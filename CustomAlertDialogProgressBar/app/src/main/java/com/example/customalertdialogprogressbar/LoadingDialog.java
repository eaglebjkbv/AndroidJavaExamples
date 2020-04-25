package com.example.customalertdialogprogressbar;


import android.app.Activity;
import android.view.LayoutInflater;

import android.app.AlertDialog;

public class LoadingDialog {

    private AlertDialog alertDialog;
    private  Activity activity;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void alertDialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_alert_dialog,null));
        builder.setCancelable(true); // Bu dialogdan çıkış için herhangi bir yere dokunmak yeterli....
        alertDialog=builder.create();
        alertDialog.show();
    }
    public void alertDialogDismiss(){
        alertDialog.dismiss();
    }



}
