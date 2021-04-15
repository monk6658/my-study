package com.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

import java.io.File;

/**
 * 
 * @author zxl
 * @date 2021/4/9 10:03
 */
public class JNATest {


    public interface ZT_DEV extends Library {
        ZT_DEV lib = Native.load("ZT_DEV", ZT_DEV.class);

        // ZT_DEV_OpenDevice为ZT_DEV.dll中的一个方法.
        int ZT_DEV_OpenDevice(int nDeviceType, String strComm, IntByReference nHandle);

        int ZT_DEV_CheckIsOpen(int Handle,IntByReference nDeviceType);
    }


    public static void main(String[] args) throws Exception {

        System.load("D:\\code\\study\\my-study\\my-java\\src\\main\\resources\\DeviceDll\\Hello.dll");
        System.out.println(System.getProperty("java.library.path"));

//        System.loadLibrary("ZT_DEV");

//        String path = "D:\\code\\study\\my-study\\my-java\\src\\main\\resources\\DeviceDll";
//        readC(path);

        IntByReference intByReference = new IntByReference();
        String msg = "COM1:9600:E:8:1";
        System.out.println(ZT_DEV.lib.ZT_DEV_OpenDevice(1,msg,intByReference));
        System.out.println(intByReference.getValue());

        System.out.println(ZT_DEV.lib.ZT_DEV_CheckIsOpen(intByReference.getValue(),intByReference));
        System.out.println(intByReference.getValue());
    }

    /**
     * 获取所有dll，类库
     * @param path 文件路径
     */
    private static void readC(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for(File f : files){
            System.loadLibrary(f.getName().replaceAll(".dll",""));
        }
    }

}
