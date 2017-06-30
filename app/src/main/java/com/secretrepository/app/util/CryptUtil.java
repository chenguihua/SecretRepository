package com.secretrepository.app.util;

/**
 * Created by chenguihua on 2017/6/30.
 */

public class CryptUtil {

    public native String encryptA(String plain);

    public native String encryptB(String plain);

    static  {
        System.loadLibrary("crypt-lib");
    }
}
