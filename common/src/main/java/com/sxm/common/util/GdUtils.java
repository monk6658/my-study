package com.sxm.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 高德接口工具类
 * @author zxl
 * @date 2021/4/21 17:09
 */
public class GdUtils {

    /**  */
    private static final String GD_REGEOCODE = "regeocode";

    private static final String GD_INFOCODE = "infocode";

    private static final String GD_INFOCODE_S = "10000";

    private static final String GD_FORMATTED_ADDRESS = "formatted_address";

    private static final String GD_RESULT = "result";

    private static final String GD_DESC = "desc";

    /**
     * 基站信息转换
     * @param bts 基站对峙
     * @return 解析结果
     */
    public static String changeBaseStation(String bts){
        String url = "http://apilocate.amap.com/position";
        Map<String,Object> map = new HashMap<>();
        // f82a321f5e357a01d589978d6bcb0a6c
        map.put("key","d9c86110aecc79a21a83005aa208197c");
        map.put("accesstype","0");
        map.put("cdma","0");
        map.put("network","GPRS");
        map.put("bts",bts);
        String urlParamsByMap = DataUtils.getUrlParamsByMap(map);
        System.out.println("请求报文:" + urlParamsByMap);
        String responseContent = HttpUtils.sendHttpPostForm(url, urlParamsByMap);
        System.out.println("响应报文:" + responseContent);
        if(responseContent == null){
            return "";
        }
        JSONObject res = JSON.parseObject(responseContent);
        //高德成功标识
        if(GD_INFOCODE_S.equals(res.getString(GD_INFOCODE))){
            JSONObject resDetail;
            //基站
            if(res.containsKey(GD_RESULT) && (resDetail = res.getJSONObject(GD_RESULT)).containsKey(GD_DESC)){
                StringBuffer sb = new StringBuffer();
                //国家|省|市|地址
                sb.append(resDetail.getString("country")).append("|")
                        .append(resDetail.getString("province")).append("|")
                        .append(resDetail.getString("city")).append("|")
                        .append(resDetail.getString(GD_DESC));
                return sb.toString();
            }
        }
        return "";
    }

    public static String changeGprs(String gprs){
        String url = "https://restapi.amap.com/v3/geocode/regeo";
        Map<String,Object> map = new HashMap<>();
        map.put("key","0e070da4869c3bce7680b582dd6963e2");
        map.put("location",gprs);
        String urlParamsByMap = DataUtils.getUrlParamsByMap(map);
        System.out.println("请求报文:" + urlParamsByMap);
        String responseContent = HttpUtils.sendHttpGet(url, urlParamsByMap);
        System.out.println("响应报文:" + responseContent);

        if(responseContent == null){
            return "";
        }
        JSONObject res = JSON.parseObject(responseContent);
        JSONObject resDetail;
        //gps
        if(res.containsKey(GD_REGEOCODE) && (resDetail = res.getJSONObject(GD_REGEOCODE)).containsKey(GD_FORMATTED_ADDRESS)){
            JSONObject detailObject = resDetail.getJSONObject("addressComponent");
            StringBuffer sb = new StringBuffer();
            //国家|省|市|地址
            sb.append(detailObject.getString("country")).append("|")
                    .append(detailObject.getString("province")).append("|")
                    .append(detailObject.getString("city")).append("|")
                    .append(resDetail.getString(GD_FORMATTED_ADDRESS));
            return sb.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        changeBaseStation("460,00,18420,53792,-73");
//        changeGprs("112.51732421875,37.84060031467014");
    }


}
