package com.skullybunny.medinoteservices.medinote.webdata.errors;

import com.skullybunny.medinoteservices.medinote.R;

/**
 * Created by Martin on 5/9/2017.
 */

public enum WebErrorCode
{
    DOCTOR_ALREADY_EXISTS_WITH_THIS_NIN(18002),
    STUDENT_ALREADY_EXISTS_WITH_THIS_NIN(19002),
    ACCOUNT_ALREADDY_EXISTS_WITH_THIS_NIN(20002),
    MEDICAL_NOTE_ALREADY_EXISTS_WITH_THIS_MEN(17002),
    MEDICAL_NOTE_NOT_FOUND_WITH_THIS_MEN(17001)
    ;
    private final int errorCode;

    public int getErrorCode() {
        return errorCode;
    }


    WebErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }
}
