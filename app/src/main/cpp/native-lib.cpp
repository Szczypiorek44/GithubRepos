#include <jni.h>

extern "C" JNIEXPORT jint JNICALL
Java_pl_karolmichalski_githubrepos_presentation_screens_details_DetailsViewModel_getMagic(
        JNIEnv *env,
        jobject /* this */,
        jobject obj)
{
    jclass cls = env->GetObjectClass(obj);
    jfieldID fieldId = env->GetFieldID(cls, "id", "I");
    int id = env->GetIntField(obj, fieldId);
    return id;
}