package com.secretrepository.app.service;

/**
 * Created by chenguihua on 2017/8/22.
 */

public class KeyCache {
    public static KeyCache instance = null;
    private String mKey;

    public static KeyCache getInstance() {
        if (instance == null) {
            synchronized (KeyCache.class) {
                if (instance == null)
                    instance = new KeyCache();
            }
        }
        return instance;
    }

    private KeyCache() {
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        this.mKey = key;
    }
}
