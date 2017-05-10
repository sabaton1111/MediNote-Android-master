package com.skullybunny.medinoteservices.medinote.helpers;

import android.widget.EditText;

/**
 * Created by Martin on 4/26/2017.
 */

public class EditTextHelper
{
    public static String getTrimmedTextString(EditText editText)
    {
        String text = editText.getText().toString().trim();
        return text;
    }
}
