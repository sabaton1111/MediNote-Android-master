package com.skullybunny.medinoteservices.medinote.webdata.errors;

import android.util.SparseIntArray;

import com.skullybunny.medinoteservices.medinote.R;

import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by Martin on 5/9/2017.
 */

public class WebErrorResIdContainer
{
    private static SparseIntArray errorStringResIds = new SparseIntArray();

    static
    {
        errorStringResIds.put(WebErrorCode.STUDENT_ALREADY_EXISTS_WITH_THIS_NIN.getErrorCode(), R.string.student_already_exists_with_nin);
        errorStringResIds.put(WebErrorCode.DOCTOR_ALREADY_EXISTS_WITH_THIS_NIN.getErrorCode(), R.string.doctor_already_exists_with_nin);
        errorStringResIds.put(WebErrorCode.ACCOUNT_ALREADDY_EXISTS_WITH_THIS_NIN.getErrorCode(), R.string.account_already_exists_with_nin);
        errorStringResIds.put(WebErrorCode.MEDICAL_NOTE_ALREADY_EXISTS_WITH_THIS_MEN.getErrorCode(), R.string.medical_note_already_exists_with_this_men);
        errorStringResIds.put(WebErrorCode.MEDICAL_NOTE_NOT_FOUND_WITH_THIS_MEN.getErrorCode(), R.string.medical_note_not_found_with_this_men);
    }

    public static int getStringResIdError(WebErrorCode errorCode)
    {
        return getStringResIdError(errorCode.getErrorCode());
    }

    public static int getStringResIdError(int errorCode)
    {
        int resId = errorStringResIds.get(errorCode);
        return resId;
    }
}
