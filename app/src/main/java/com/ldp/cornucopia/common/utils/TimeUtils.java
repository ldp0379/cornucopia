package com.ldp.cornucopia.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间util
 * Created by ldp on 16/8/30.
 */
public class TimeUtils {
    /**
     * 获得当前时间（用于刷新控件的时间获取）
     */
    public static String getCurTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
}
