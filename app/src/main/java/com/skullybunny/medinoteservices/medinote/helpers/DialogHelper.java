package com.skullybunny.medinoteservices.medinote.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.skullybunny.medinoteservices.medinote.R;

/**
 * Created by Martin on 5/9/2017.
 */

public class DialogHelper
{
    public static void showDialog(String message, Context context)
    {

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);

        builder.setMessage(message);

        AlertDialog dialog = builder.create();

        dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }
}
