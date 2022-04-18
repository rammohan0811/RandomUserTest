package com.example.mvvmdemo.utilities;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {
    private static ProgressDialog progressBarDialog;

    public static void showProgressDialog(Context context) {
        if (progressBarDialog == null) {
            progressBarDialog = new ProgressDialog(context);
            progressBarDialog.setMessage("Loading...");
            progressBarDialog.setCancelable(false);//you can cancel it by pressing back button
            progressBarDialog.setIndeterminate(true);
        }
        try {
            if (!progressBarDialog.isShowing()) {
                progressBarDialog.show();
            }
        } catch (Exception e) {
            progressBarDialog = null;
            progressBarDialog = new ProgressDialog(context);
            progressBarDialog.setMessage("Loading...");
            progressBarDialog.setCancelable(true);//you can cancel it by pressing back button
            progressBarDialog.setIndeterminate(true);
            progressBarDialog.show();
        }
    }


    public static void dismissProgressDialog() {
        if (progressBarDialog != null) {
            progressBarDialog.dismiss();
            progressBarDialog = null;
        }
    }
}
