package com.mergefileupdate;

public class MergeUtil {

    static{
        System.loadLibrary("Patcher");
    }

    public native int mergeFile(String oldFilePath,String newFilePath,String patchFilePath);
}
