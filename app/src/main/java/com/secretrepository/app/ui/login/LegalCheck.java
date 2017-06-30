package com.secretrepository.app.ui.login;

import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenguihua on 2017/6/30.
 */

public class LegalCheck {
    private static final int MAX_LENGTH = 50;

    public static final int NO_ERROR = 0;
    public static final int EMPTY_ERROR = 1;
    public static final int LENGTH_LIMIT_ERROR = 2;
    public static final int ILLEGAL_CHAR_ERROR = 3;

    @IntDef({NO_ERROR, EMPTY_ERROR, LENGTH_LIMIT_ERROR, ILLEGAL_CHAR_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CheckError {}

    private boolean checkIsEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    private boolean checkLengthLimit(String str, int min) {
        return checkLengthLimit(str, min, MAX_LENGTH);
    }

    private boolean checkLengthLimit(String str, int min, int max) {
        if (str == null) return false;

        if (str.length() >= min && str.length() <= max) {
            return true;
        }
        return false;
    }

    private boolean checkLegalCharacter(String str) {
        String regex = "((\\w)|@)+";
        Matcher matcher = Pattern.compile(regex).matcher(str);
        return matcher.matches();
    }

    private boolean checkFormat(String str) {
        return true;
    }

    @CheckError
    public int check(String str) {
        if (checkIsEmpty(str)) {
            return EMPTY_ERROR;
        }

        if (checkLengthLimit(str, 6)) {
            return LENGTH_LIMIT_ERROR;
        }

        if (checkLegalCharacter(str)) {
            return ILLEGAL_CHAR_ERROR;
        }

        return NO_ERROR;
    }
}
