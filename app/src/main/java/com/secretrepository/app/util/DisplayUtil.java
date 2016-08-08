package com.secretrepository.app.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by chenguihua on 2016/7/5.
 */
public class DisplayUtil {
    public static int dp2px(Context context, int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                context.getResources().getDisplayMetrics());
    }

}
