package com;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Data
public class Test  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;

//    public static void main(String[] args) {
//        Test test = new Test();
//        System.out.println(JSON.toJSONString(test));
//        Test aa = new Test();
//        BeanUtils.copyProperties(aa,test);
//        System.out.println(JSON.toJSONString(aa));
//    }


    public static void main(String[] args) {
        double d = 0.3456789;
        BigDecimal bd = new BigDecimal("2");
        double test1 = bd.setScale(8, BigDecimal.ROUND_DOWN).doubleValue(); // 保留两位小数，不四舍五入(可选舍入模式)
        System.out.println("方法一：" + test1); //0.34


        DecimalFormat df2 =new DecimalFormat("#.00000000");
        System.out.println(df2.format(2));
        System.out.println(Double.parseDouble(df2.format(2)));
        System.out.println(Double.valueOf(df2.format(2)));


        String format = String.format("%.8f", d);

        
        System.out.println(new BigDecimal(format).setScale(8, BigDecimal.ROUND_DOWN).doubleValue());
        
        
        
    }
    
}
