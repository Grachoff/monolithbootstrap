package com.altarix;

public class ConstantsHolder {
    private static ConstantsHolder instance;
    public static ConstantsHolder getConstatsHolder(){
        if (instance == null) instance = new ConstantsHolder();
        return instance;
    }
    public static final String ROLE_PREFIX = "ROLE_";
    public static final long LOGIN_TOKEN_TTL_MILLIS = 1000 * 60 * 60 * 24;

    public String getRolePrefix(){
        return ROLE_PREFIX;
    }

    public long getLoginTokenTtlMillis() {
        return LOGIN_TOKEN_TTL_MILLIS;
    }
}
