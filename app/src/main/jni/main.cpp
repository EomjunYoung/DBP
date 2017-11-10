#include <jni.h>
#include "com_example_ejy77_myapplication_Recognition_jnisearch.h"

#include <opencv2/core/core.hpp>

#include <opencv2/imgproc/imgproc.hpp>

using namespace cv;


extern "C"{


    JNIEXPORT void JNICALL
   Java_com_example_ejy777_myapplication_Recognition_jnisearch_recog(
            JNIEnv *env,
            jobject  instance,
            jlong matAddrInput,
            jlong matAddrResult){


        Mat &matInput = *(Mat *)matAddrInput;
        Mat &matResult = *(Mat *)matAddrResult;

        cvtColor(matInput, matResult, CV_RGBA2GRAY);


      }
}