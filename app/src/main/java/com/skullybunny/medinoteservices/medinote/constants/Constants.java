package com.skullybunny.medinoteservices.medinote.constants;

/**
 * Created by Martin on 5/10/2017.
 */

public class Constants
{
    public static final String NIN_REGEX = "^[0-9]{10}$";
    public static final String ADDRESS_REGEX = "^[\\p{L}{0-9}\\s.’'\\-,]+$";
    public static final String NAME_REGEX = "^[\\p{L}\\s.’'\\-,]+$";
    public static final String UIN_REGEX = "^[0-9]{6}$";
    public static final String POSITION_REGEX = "^[\\p{L}\\s.’'\\-,]+$";
    public static final String HEALTHCARE_FACILITY_NAME_REGEX = "^[\\p{L}{0-9}\\s.’'\\-,]+$";
    public static final String MOBILE_REGEX = "^[{0-9}+/]+$";
    public static final String MEN_REGEX = "^[0-9]{6}$";
    public static final String DIAGNOSE_REGEX = "^[\\p{L}{0-9}\\s.’'\\-,]+$";
    public static final String NEEDS_REGEX = "^[\\p{L}{0-9}\\s.’'\\-,]+$";
    public static final String SERVE_TO_REGEX = "^[\\p{L}{0-9}\\s.’'\\-,]+$";
    public static final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))" +
            "\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?" +
            "\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468]" +
            "[048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]" +
            "|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
}
