package com.io.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: zxl
 * @create: 2024-02-19 14:01
 **/
public class FileChannelTest {
    
    
    @Test
    public void writeFile(){
        try {
            FileOutputStream fos = new FileOutputStream("data.txt");
            FileChannel channel = fos.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("写文件测试！！".getBytes(StandardCharsets.UTF_8));
            
            byteBuffer.flip(); // 切换模式
            channel.write(byteBuffer);
            channel.close();
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void readFile(){

        try {
            FileInputStream fis = new FileInputStream("data.txt");
            FileChannel channel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0, byteBuffer.remaining()));
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    @Test
    public void copyFile() throws Exception {
        
        FileInputStream fis = new FileInputStream("data.txt");
        FileOutputStream fos = new FileOutputStream("data2.txt");

        FileChannel is = fis.getChannel();
        FileChannel os = fos.getChannel();

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        // 清空 - 读 - 写 - 清空
        while (true){
            // 清空
            allocate.clear();

            int read = is.read(allocate);
            if(read == -1){
                break;
            }
            allocate.flip();
            os.write(allocate);
        }

        is.close();
        os.close();
        
    }
    
    // 分散聚集
    @Test 
    public void scatterAndGather() throws Exception{
        
        FileInputStream fis = new FileInputStream("data.txt");
        FileChannel fisChannel = fis.getChannel();
        ByteBuffer b1 = ByteBuffer.allocate(6);
        ByteBuffer b2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = new ByteBuffer[]{b1,b2};
        fisChannel.read(buffers);
        for (ByteBuffer buffer : buffers) {
            buffer.flip();
            System.out.println(new String(buffer.array(),0,buffer.remaining()));
        }

        FileOutputStream fos = new FileOutputStream("data3.txt");
        FileChannel fosChannel = fos.getChannel();
        fosChannel.write(buffers);
        
        fosChannel.close();
        fos.close();
        fisChannel.close();
        fis.close();
        
    }
    
    // 通道复制
    @Test
    public void transfer() throws Exception {

        FileInputStream fis = new FileInputStream("data.txt");
        FileChannel fisChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("data5.txt");
        FileChannel fosChannel = fos.getChannel();
        
//        fosChannel.transferFrom(fisChannel,fisChannel.position(),fisChannel.size());

        fisChannel.transferTo(fisChannel.position(),fisChannel.size(),fosChannel);

        fosChannel.close();
        fos.close();
        fisChannel.close();
        fis.close();
        
    }
    
    
}
