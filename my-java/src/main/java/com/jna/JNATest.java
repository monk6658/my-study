package com.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import org.springframework.util.ResourceUtils;

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

        int ZT_DEV_GetDeviceVersion(int Handle,String szVersion);

        int ZT_DEV_GetDllVersion(String szVersion);

        int ZT_DEV_CloseDevice(int Handle);
    }


    public static void main(String[] args) throws Exception {

//        System.load("D:\\code\\study\\my-study\\my-java\\src\\main\\resources\\DeviceDll\\Hello.dll");
//        System.out.println(System.getProperty("java.library.path"));

//        System.loadLibrary("ZT_DEV");
        System.load(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "DeviceDll/ZT_DEV.dll").getPath());
//        String path = "D:\\code\\study\\my-study\\my-java\\src\\main\\resources\\DeviceDll";
//        readC(path);

        IntByReference flag = new IntByReference();
        String msg = "COM17:9600:E:8:1";
        System.out.println("设备启动状态:" + ZT_DEV.lib.ZT_DEV_OpenDevice(1,msg,flag));
        System.out.println("设备标识:" + flag.getValue());

        IntByReference type = new IntByReference();
        System.out.println("检查是否开启:" + ZT_DEV.lib.ZT_DEV_CheckIsOpen(flag.getValue(),type));
        System.out.println("设备类型:" + type.getValue());


        // 获取驱动版本号
        String szVersion = "";
        System.out.println("获取驱动版本号状态:" + ZT_DEV.lib.ZT_DEV_GetDllVersion(szVersion));
        System.out.println(szVersion);

        String versionMsg = "";
        System.out.println("获取硬件版本号状态:" + ZT_DEV.lib.ZT_DEV_GetDeviceVersion(flag.getValue(),versionMsg));
        System.out.println("版本号信息:" + versionMsg);


        System.out.println("关闭设备状态:" + ZT_DEV.lib.ZT_DEV_CloseDevice(flag.getValue()));
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
