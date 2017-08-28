#define LOG_TAG "secret-lib"

#include <jni.h>
#include "MD5.h"
#include <android/log.h>

static struct login_offsets_t {
    jclass mClass;
    jfieldID  mDataManager;

} gLoginOffsets;

static struct cryptutil_offsets_t {
    jclass mClass;
    jmethodID mEncrypt;
    jmethodID mDecrypt;
} gCryptUtilOffsets;

static struct datamanager_offset_t {
    jclass mClass;
    jmethodID mSetPass;
    jmethodID mSetUser;
} gDataManagerOffsets;

#define ALOGE(...) \
    __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__);

const char *MD5Encode(const char *);

jstring com_secretrepository_app_ui_login_LoginPresenter_register
        (JNIEnv *env, jobject thiz, jstring username, jstring password) {
    // find preference control java interface class.
    jobject mDataManagerObject = env->GetObjectField(thiz, gLoginOffsets.mDataManager);
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

jstring com_secretrepository_app_ui_login_LoginPresenter_login
        (JNIEnv *env, jobject thiz, jstring username, jstring password) {
    jobject mDataManagerObject = env->GetObjectField(thiz, gLoginOffsets.mDataManager);
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

jstring com_secretrepository_app_ui_edit_EditPresenter_encrypt(JNIEnv *env, jobject thiz, jstring key, jstring plainText)
{
    const char* serialChars = env->GetStringUTFChars(key, false);
    char * serialCpy = (char *)malloc(strlen(serialChars) + 1 + 7);
    strcpy(serialCpy, "serial_");
    strcat(serialCpy, serialChars);
    jstring secretKey = env->NewStringUTF(serialChars);
    free(serialCpy);
    env->ReleaseStringUTFChars(key, serialChars);
    jstring cipherText = (jstring)env->CallStaticObjectMethod(gCryptUtilOffsets.mClass, gCryptUtilOffsets.mEncrypt, secretKey, plainText);
    return cipherText;
}

jstring com_secretrepository_app_ui_edit_EditPresenter_decrypt(JNIEnv *env, jobject thiz, jstring key, jstring cipherText)
{
    const char* serialChars = env->GetStringUTFChars(key, false);
    char * serialCpy = (char *)malloc(strlen(serialChars) + 1 + 7);
    strcpy(serialCpy, "serial_");
    strcat(serialCpy, serialChars);
    jstring secretKey = env->NewStringUTF(serialChars);
    free(serialCpy);
    env->ReleaseStringUTFChars(key, serialChars);
    jstring plainText = (jstring)env->CallStaticObjectMethod(gCryptUtilOffsets.mClass, gCryptUtilOffsets.mDecrypt, secretKey, cipherText);
    return plainText;
}

const char *MD5Encode(const char * plain) {
    MD5 md5;
    md5.GenerateMD5((unsigned char*)plain, strlen(plain));
    std::string generatedValue = md5.ToString();
    return generatedValue.c_str();
}

static const char* const kLoginPresenterPathName = "com/secretrepository/app/ui/login/LoginPresenter";
static JNINativeMethod gMethods[] = {
        {"register",    "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",     (void *)com_secretrepository_app_ui_login_LoginPresenter_register   },
        {"login",       "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",     (void *)com_secretrepository_app_ui_login_LoginPresenter_login      },
};

int register_com_secrectrespository_app_login_crypt(JNIEnv* env)
{
    jclass clazz;
    clazz = env->FindClass(kLoginPresenterPathName);
    if (clazz == NULL)
    {
        ALOGE("Unable to find class com.secretrepository.app.ui.login.LoginPresenter");
        return JNI_FALSE;
    }
    gLoginOffsets.mClass = (jclass) env->NewGlobalRef(clazz);
    gLoginOffsets.mDataManager = env->GetFieldID(clazz, "mDataManager", "Lcom/secretrepository/app/data/DataManager;");

    if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

static const char* const kEditPresenterPathName = "com/secretrepository/app/util/CryptUtil";
static const char* const kCryptUtilPathName     = "com/secretrepository/app/util/crypt/AESCrypt";
static JNINativeMethod gEditMethods[] = {
        {"encrypt",     "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",                       (void *)com_secretrepository_app_ui_edit_EditPresenter_encrypt      },
        {"decrypt",     "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;",                       (void *)com_secretrepository_app_ui_edit_EditPresenter_decrypt      },
};

int register_com_secrectrespository_util_crypt(JNIEnv *env)
{
    jclass clazz;

    clazz = env->FindClass(kCryptUtilPathName);
    if (clazz == NULL) {
        ALOGE("Unable to find class com.secretreposotory.app.util.AESCrypt");
        return JNI_FALSE;
    }
    gCryptUtilOffsets.mClass = (jclass) env->NewGlobalRef(clazz);
    gCryptUtilOffsets.mEncrypt = env->GetStaticMethodID(clazz, "encrypt", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
    gCryptUtilOffsets.mDecrypt = env->GetStaticMethodID(clazz, "decrypt", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");

    clazz = env->FindClass(kEditPresenterPathName);
    if (clazz == NULL) {
        ALOGE("Unable to find class com.secretreposotory.app.ui.edit.EditPresenter");
        return JNI_FALSE;
    }

    if (env->RegisterNatives(clazz, gEditMethods, sizeof(gEditMethods) / sizeof(gEditMethods[0])) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved)
{
    JNIEnv* env = NULL;
    jint result = -1;

    if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        ALOGE("ERROR: GetEnv failed\n");
        goto bail;
    }

    if (!register_com_secrectrespository_app_login_crypt(env)) {
        ALOGE("ERROR: LoginPresenter native register failed");
        goto bail;
    }

    if (!register_com_secrectrespository_util_crypt(env)) {
        ALOGE("ERROT: AESCrypt native register failed");
        goto bail;
    }

    result = JNI_VERSION_1_4;
    bail:
    return result;

}

