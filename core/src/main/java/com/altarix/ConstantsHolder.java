package com.altarix;

import java.text.SimpleDateFormat;

public class ConstantsHolder {
    private static ConstantsHolder instance;
    public static ConstantsHolder getConstatsHolder(){
        if (instance == null) instance = new ConstantsHolder();
        return instance;
    }

    public static final int LOCK_PERIOD = 10 * 60 * 1000;

    public static final String ROLE_PREFIX = "ROLE_";
    public static final long LOGIN_TOKEN_TTL_MILLIS = 1000 * 60 * 60 * 24;
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static SimpleDateFormat getTimeFormatter() {
        return new SimpleDateFormat(TIME_FORMAT);
    };

    public String getRolePrefix(){
        return ROLE_PREFIX;
    }
    public long getLoginTokenTtlMillis() {
        return LOGIN_TOKEN_TTL_MILLIS;
    }
    public String getTimeFormat() {return TIME_FORMAT;}

}
