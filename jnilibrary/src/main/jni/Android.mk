#每个Android.mk文件必须以LOCAL_PATH开头，在整个开发中，它通常别用做定位资源文件，例如，功能宏my-dir提供给编译系统当前的路径。
LOCAL_PATH := $(call my-dir)
#CLEAR_VARS指编译系统提供一个特殊的GUN MakeFile来为你清除所有的LOCAL_XXX变量，LOCAL_PATH不会被清除。使用这个变量是因为在编译系统时，所有的控制文件都会在一个GUN Make上下文进行执行，而在此上下文中所有的LOCAL_XXX都是全局的。
include $(CLEAR_VARS)
#LOCAL_MODULE变量是为了确定模块名，并且必须要定义。这个名字必须是唯一的同时不能含有空格。会自动的为文件添加适当的前缀或后缀，模块名为“foo”它将会生成一个名为“libfoo.so”文件。
LOCAL_MODULE    := nela_jni
#包含一系列被编译进模块的C 或C++资源文件
LOCAL_SRC_FILES := NelaLogin.cpp

#指明一个GUN Makefile脚本，并且收集从最近“include$(CLEAR_VARS)”下的所有LOCALL_XXX变量的信息，最后告诉编译系统如何正确的进行编译。将会生成一个静态库hello-jni.a文件。
include $(BUILD_SHARED_LIBRARY)