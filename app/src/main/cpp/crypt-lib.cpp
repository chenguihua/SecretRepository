//
// Created by chenguihua on 2017/6/30.
//

#include "crypt-lib.h"


JNIEXPORT jstring JNICALL Java_com_secretrepository_app_util_CryptUtil_encryptA
        (JNIEnv *jniEnv, jobject , jstring);

/*
 * Class:     com_secretrepository_app_util_CryptUtil
 * Method:    encryptB
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_secretrepository_app_util_CryptUtil_encryptB
        (JNIEnv *, jobject, jstring);
