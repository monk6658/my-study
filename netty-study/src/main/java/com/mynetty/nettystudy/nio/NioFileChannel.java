package com.mynetty.nettystudy.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 
 * @author zxl
 * @date 2020/12/22 14:51
 */
public class NioFileChannel {

    static String str = "hello,测试";

    static String path = "1.txt";

    static String targetPath = "2.txt";

    public static void main(String[] args) throws Exception {
        writeFile();
//        readFile();
//        copyFile();
//        copyFileTransferFrom();
//      MappedBuffer 可让文件直接在内存（堆外内存）中修改，操作系统不需要拷贝一次。
//        updateFile();
    }

    private static void writeFile() throws IOException{

        // 1. 创建一个输出流 ——> channel
        FileOutputStream fileOutputStream = new FileOutputStream(path);

        // 2. 通过文件输出流获取对应的 fileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 3. 创建一个缓冲区 ByteBuffer，并把str放入缓冲区中
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        // 4. 对ByteBuffer 进行位置翻转（详细见源码）
        byteBuffer.flip();

        // 5. 把byteBuffer 写入至 fileChannel 中。
        fileChannel.write(byteBuffer);

        // 6. 关闭流连接
        fileChannel.close();
        fileOutputStream.close();

    }

    private static void readFile() throws IOException{
        // 1. 创建一个输入流 ——> channel
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // 2. 通过文件输入流获取对应的 fileChannel
        FileChannel fileChannel = fileInputStream.getChannel();

        // 3. 创建一个缓冲区 ByteBuffer，并把文件里的东西，读至缓存区中
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(byteBuffer);

        // 4. 输出文件内容
        System.out.println(new String(byteBuffer.array()));

        // 5. 关闭流连接
        fileChannel.close();
        fileInputStream.close();
    }

    private static void copyFile() throws IOException{

        // 1. 创建一个输入流 ——> channel
        FileInputStream fileInputStream = new FileInputStream(path);
        FileChannel fileChannel01 = fileInputStream.getChannel();

        // 2. 通过文件输出流获取对应的 fileChannel
        FileOutputStream fileOutputStream = new FileOutputStream(targetPath);
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        // 3. 创建一个缓冲区 ByteBuffer，并把str放入缓冲区中
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 4. 循环读取，每次置空缓存区位置
        while (true){
            byteBuffer.clear();
            int count = fileChannel01.read(byteBuffer);
            if(count == -1){
                break;
            }
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }


        // 5. 关闭流连接
        fileChannel02.close();
        fileOutputStream.close();
        fileChannel01.close();
        fileInputStream.close();
    }

    private static void copyFileTransferFrom() throws IOException{

        // 1. 创建一个输入流 ——> channel
        FileInputStream fileInputStream = new FileInputStream(path);
        FileChannel fileChannel01 = fileInputStream.getChannel();

        // 2. 通过文件输出流获取对应的 fileChannel
        FileOutputStream fileOutputStream = new FileOutputStream(targetPath);
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        // 3. copy
//        fileChannel02.transferFrom(fileChannel01,0,fileChannel01.size());
        /*
        transferTo 底层使用零拷贝，在windows下只能发送8m，linux无限制
         */
        fileChannel01.transferTo(0,fileChannel01.size(),fileChannel02);

        // 4. 关闭流连接
        fileChannel02.close();
        fileOutputStream.close();
        fileChannel01.close();
        fileInputStream.close();
    }

    private static void updateFile() throws IOException{

        // 1. 创建一个输出流 ——> channel
        RandomAccessFile randomAccessFile = new RandomAccessFile(path,"rw");

        // 2. 通过文件输出流获取对应的 fileChannel
        FileChannel fileChannel = randomAccessFile.getChannel();

        /*
        param1：文件修改模式
        param2: 下标起始位置
        param3: 可修改大小，不包含size
        实际操作类型为：DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'X');
        mappedByteBuffer.put(4, (byte) 'L');

        // 3. 关闭流连接
        fileChannel.close();
        randomAccessFile.close();
    }


}
