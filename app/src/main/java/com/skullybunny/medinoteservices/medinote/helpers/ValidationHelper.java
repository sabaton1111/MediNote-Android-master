package com.skullybunny.medinoteservices.medinote.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

/**
 * Created by Martin on 5/10/2017.
 */

public class ValidationHelper
{
    public static void setOrShowErrors(List<ValidationError> errors, Context context)
    {
        for (ValidationError error : errors)
        {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(context);

            if (view instanceof EditText)
            {
                ((EditText) view).setError(message);
            }
            else
            {
                ToastHelper.showToast(message, context);
            }
        }
    }
}
