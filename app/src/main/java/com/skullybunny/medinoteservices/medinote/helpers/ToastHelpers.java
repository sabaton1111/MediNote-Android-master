package com.skullybunny.medinoteservices.medinote.helpers;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.skullybunny.medinoteservices.medinote.R;

/**
 * Created by Martin on 5/6/2017.
 */

public class ToastHelpers
{
    public static void showToast(String text, Context context)
    {
        showToast(text, context, Toast.LENGTH_SHORT);
    }

    public static void showToast(String text, Context context, int length)
    {
        Toast toast = Toast.makeText(context, text, length);
        toast.show();
    }

    public static void showToast(@StringRes int resId, Context context)
    {
        showToast(resId, context, Toast.LENGTH_SHORT);
    }

    public static void showToast(@StringRes int resId, Context context, int length)
    {
        Toast toast = Toast.makeText(context, resId, length);
        toast.show();
    }
}
