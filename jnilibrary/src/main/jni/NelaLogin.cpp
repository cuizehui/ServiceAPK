//
// Created by nela.cui on 2020-03-17.
//
#include <jni.h>
#include "com_nela_jniLibrary_NelaLoginJNI.h"

JNIEXPORT jstring JNICALL Java_com_nela_jniLibrary_NelaLoginJNI_Nela_1login
        (JNIEnv *env, jclass) {
    return env->NewStringUTF("Hello from Nela login");
}