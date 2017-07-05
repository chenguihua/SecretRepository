//
// Created by chenguihua on 2017/7/3.
//
#include <jni.h>
#include "MD5.h"
#include <android/log.h>

extern "C" {
//#define LOGD(x)    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", x)
//#define LOGD(x, y) __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", x, y)

static const char* const KLoginPresenter = "com/secretrepository/app/ui/login/LoginPresenter";

const char *MD5Encode(const char *);

JNIEXPORT jstring Java_com_secretrepository_app_ui_login_LoginPresenter_register
        (JNIEnv *env, jobject thiz, jstring username, jstring password) {
    jclass clazz = env->FindClass(KLoginPresenter);
    if (clazz == 0) {
        return env->NewStringUTF("");
    }

    // find preference control java interface class.
    jfieldID mDataManagerFieldID = env->GetFieldID(clazz, "mDataManager", "Lcom/secretrepository/app/data/DataManager;");
    jobject mDataManagerObject = env->GetObjectField(thiz, mDataManagerFieldID);
    jclass mDataManagerClazz = env->GetObjectClass(mDataManagerObject);
    jmethodID setLocalPassMethodID = env->GetMethodID(mDataManagerClazz, "setLocalPassword", "(Ljava/lang/String;)V");
    jmethodID setLocalUserMethodID = env->GetMethodID(mDataManagerClazz, "setLocalUsername", "(Ljava/lang/String;)V");

    // insert into preference KEY_USER and KEY_PASS.
    const char* usernameChars = env->GetStringUTFChars(username, false);
    const char* passwordChars = env->GetStringUTFChars(password, false);
    env->CallVoidMethod(mDataManagerObject, setLocalUserMethodID, env->NewStringUTF(usernameChars));
    env->CallVoidMethod(mDataManagerObject, setLocalPassMethodID, env->NewStringUTF(MD5Encode(passwordChars)));

    // generate a serial to be as AES encrypt key.
    char * plain = (char *)malloc(strlen(usernameChars) + strlen(passwordChars) + 1 + 1);
    strcpy(plain, usernameChars);
    strcat(plain, "&");
    strcat(plain, passwordChars);
    const char * generateValue = MD5Encode(plain);
    free(plain);
    env->ReleaseStringUTFChars(username, usernameChars);
    env->ReleaseStringUTFChars(password, passwordChars);
    return env->NewStringUTF(generateValue);
}

JNIEXPORT jstring Java_com_secretrepository_app_ui_login_LoginPresenter_login
        (JNIEnv *env, jobject thiz, jstring username, jstring password) {
    jclass clazz = env->FindClass(KLoginPresenter);
    if (clazz == 0) {
        return env->NewStringUTF("");
    }

    jfieldID mDataManagerFieldID = env->GetFieldID(clazz, "mDataManager", "Lcom/secretrepository/app/data/DataManager;");
    jobject mDataManagerObject = env->GetObjectField(thiz, mDataManagerFieldID);
    jclass mDataManagerClazz = env->GetObjectClass(mDataManagerObject);
    jmethodID getLocalPassMethodID = env->GetMethodID(mDataManagerClazz, "getLocalPassword", "()Ljava/lang/String;");
    jstring passwordJString = (jstring)env->CallObjectMethod(mDataManagerObject, getLocalPassMethodID);

    const char* usernameChars = env->GetStringUTFChars(username, false);
    const char* passwordChars = env->GetStringUTFChars(password, false);

    const char* compuPasswordMdChars = MD5Encode(passwordChars);
    const char* localPasswordMdChars = env->GetStringUTFChars(passwordJString, false);

    if (!strcmp(compuPasswordMdChars, localPasswordMdChars)) {
        char * plain = (char *)malloc(strlen(usernameChars) + strlen(passwordChars) + 1 + 1);
        strcpy(plain, usernameChars);
        strcat(plain, "&");
        strcat(plain, passwordChars);
        const char * generateValue = MD5Encode(plain);
        free(plain);
        env->ReleaseStringUTFChars(username, usernameChars);
        env->ReleaseStringUTFChars(password, passwordChars);
        env->ReleaseStringUTFChars(passwordJString, localPasswordMdChars);
        return env->NewStringUTF(generateValue);
    } else {
        return env->NewStringUTF("");
    }

}

const char *MD5Encode(const char * plain) {
    MD5 md5;
    md5.GenerateMD5((unsigned char*)plain, strlen(plain));
    std::string generatedValue = md5.ToString();
    return generatedValue.c_str();
}

}

