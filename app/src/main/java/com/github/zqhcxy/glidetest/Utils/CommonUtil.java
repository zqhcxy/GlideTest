package com.github.zqhcxy.glidetest.Utils;

import android.content.Context;
import android.net.Uri;

/**
 * Created by zqh-pc on 2016/8/18.
 */
public class CommonUtil {

    public static final String ANDROID_RESOURCE="android.resource://";
    public static final String FOREWARD_SLASH="/";

    /**
     * dp转成px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取资源ID转换成Uri
     * @param context
     * @param resourceId
     * @return
     */
    public static Uri resourceIdToUri (Context context, int resourceId){
        return Uri.parse(ANDROID_RESOURCE+context.getPackageName()+FOREWARD_SLASH+resourceId);
    }
}
