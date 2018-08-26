package com.hd.util;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

public class SecurityUtil {

    private SecurityUtil(){

    }
    public static String getHexStr(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }
    public static String getHex3Str(String value) {
        for (int i = 0; i < 3; i++) {
            value = DigestUtils.md5DigestAsHex(value.getBytes());
        }
        return value;
    }
}
