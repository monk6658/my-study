package com.sxm.common.util;

import java.util.Map;

/**
 * 数据处理工具类
 * @author zxl
 * @date 2021/4/21 17:14
 */
public class DataUtils {

    /**
     * 将map转换成url
     * @param map map对象
     * @return 问号传参结果
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }


}
