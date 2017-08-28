package com.secretrepository.app.util;

/**
 * Created by chenguihua on 2017/8/22.
 */

public class CryptUtil {
    public CryptUtil() {
    }

    public native String encrypt(String key, String plain);
    public native String decrypt(String key, String plain);
}
