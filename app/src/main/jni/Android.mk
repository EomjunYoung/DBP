LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

#opencv library
OPENCVROOT:= D:/OpenCV-3.1.0-android-sdk/OpenCV-android-sdk
OPENCV_CAMERA_MODULES:=on
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=SHARED
include ${OPENCVROOT}\sdk\native\jni\OpenCV.mk


LOCAL_MODULE    := main2
LOCAL_SRC_FILES := main2.cpp
LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)
