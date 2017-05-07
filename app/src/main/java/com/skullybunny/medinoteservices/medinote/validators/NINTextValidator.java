package com.skullybunny.medinoteservices.medinote.validators;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Martin on 5/7/2017.
 */

public class NINTextValidator implements TextWatcher {

    private String mErrorMessage;
    private EditText mEditText;

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
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mEditText.setError(mErrorMessage);
    }
}
