#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_pl_karolmichalski_githubrepos_presentation_screens_details_DetailsViewModel_doMagic(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
