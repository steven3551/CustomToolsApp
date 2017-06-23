package com.wuwg.component.compress;

/**
 * Created by wuwengao on 2017/6/23.
 */
public class PhotoCompressHelper {

//    static {
//        System.loadLibrary("compress");
//    }

    private static PhotoCompressHelper instance = null;

    private PhotoCompressHelper() {
    }

    public static PhotoCompressHelper getInstance() {
        synchronized (PhotoCompressHelper.class){
            if(instance == null){
                instance = new PhotoCompressHelper();
            }
        }
        return instance;
    }

//    public native void nativeCompressBitmap(Object bit, int w, int h, int quality, byte[] fileNameByte);

}
