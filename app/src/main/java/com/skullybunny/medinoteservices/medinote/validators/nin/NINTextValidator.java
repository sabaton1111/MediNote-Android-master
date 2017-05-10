package com.skullybunny.medinoteservices.medinote.validators.nin;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Martin on 5/7/2017.
 */

public class NINTextValidator implements TextWatcher, NINValidator<String> {


    private String mErrorMessage;
    private EditText mEditText;
    private Pattern mRegexPattern;

    public EditText getEditText() {
        return mEditText;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public NINTextValidator(String errorMessage, EditText editText)
    {
        mErrorMessage = errorMessage;
        mEditText = editText;
        mRegexPattern = Pattern.compile(ninPatternRegex);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        if (!isNIN(s.toString()))
        {
            mEditText.setError(mErrorMessage);
        }
        else
        {
            mEditText.setError(null);
        }
    }

    @Override
    public boolean isNIN(String text)
    {
        boolean matchesNIN = mRegexPattern.matcher(text).matches();

        return matchesNIN;
    }
}
