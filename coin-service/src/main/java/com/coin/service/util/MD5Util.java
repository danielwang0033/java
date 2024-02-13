package com.coin.service.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String MD5(String val) {
        return DigestUtils.md5DigestAsHex(val.getBytes());
    }
}
