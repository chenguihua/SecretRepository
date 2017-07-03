//
// Created by chenguihua on 2017/7/3.
//
#include <jni.h>
#include <android/log.h>

extern "C" {

static const char* const KLoginPresenter = "com/secretrepository/app/ui/login/LoginPresenter";

JNIEXPORT jstring Java_com_secretrepository_app_ui_login_LoginPresenter_register
        (JNIEnv *env, jobject thiz, jstring username, jstring password) {
    jclass clazz = env->FindClass(KLoginPresenter);
    if (clazz == 0) {
        return env->NewStringUTF("");
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", "LoginPresenter class find successfully.");
    jfieldID fieldDataManager = env->GetFieldID(clazz, "DataManager", "[L/java/lang/object;");
    if (fieldDataManager == 0) {
        return env->NewStringUTF("");
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", "LoginPresenter-DataManager fieldID find successfully.");
    jobject jobjectDataManager = env->GetObjectField(thiz, fieldDataManager);
    if (jobjectDataManager == 0) {
        return env->NewStringUTF("");
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", "LoginPresenter-DataManager field find successfully.");
    return env->NewStringUTF("not finish.");
}

JNIEXPORT jstring Java_com_secretrepository_app_ui_login_LoginPresenter_login
        (JNIEnv *env, jobject thiz, jstring username, jstring password) {
    jclass clazz = env->FindClass(KLoginPresenter);
    if (clazz == 0) {
        return env->NewStringUTF("");
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", "LoginPresenter class find successfully.");
    jfieldID fieldDataManager = env->GetFieldID(clazz, "DataManager", "L/java/lang/object;");
    if (fieldDataManager == 0) {
        return env->NewStringUTF("");
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", "LoginPresenter-DataManager fieldID find successfully.");
    jobject jobjectDataManager = env->GetObjectField(thiz, fieldDataManager);
    if (jobjectDataManager == 0) {
        return env->NewStringUTF("");
    }
    __android_log_print(ANDROID_LOG_DEBUG, "NDK_LOG", "LoginPresenter-DataManager field find successfully.");
    return env->NewStringUTF("not finish.");
}

}

