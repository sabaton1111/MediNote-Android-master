package com.skullybunny.medinoteservices.medinote.validators.nin;

import java.util.regex.Pattern;

/**
 * Created by Martin on 5/8/2017.
 */

public interface NINValidator<T>
{
    String ninPatternRegex = "^[0-9]{10}$";

    boolean isNIN(T text);
}
