package com.sxm.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zxl
 * @date 2021/6/17 17:42
 */
@Slf4j
public class TestThrowThread {
    static Logger logger = LoggerFactory.getLogger(TestThrowThread.class);

    public static void main(String[] args) {
        for (int i = 0; i < 118000 ; i++) {
            try{
                System.out.println("Loop:"+(i+1));
                String uer = null;
                uer.getBytes();
            }catch (Exception e){
                logger.error("异常",e);
            }
        }

    }



}
