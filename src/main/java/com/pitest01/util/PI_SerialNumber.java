package com.pitest01.util;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//生成自定义格式流水号
public class PI_SerialNumber {
    /*
    * STR:流水号抬头，一般票据、物料号、订单号开头拥有有意义词语缩写 如：订单-DD 商品-SP，DD-20200202-1324
    * dateNF:是否拼接日期
    * code:流水号数值的格式,设定流水号数字的位数，不满位数前面补充0；
    * ID:流水号数值的变量；
    * */
    public static String serialNumber(String STR,Boolean dateNF,int code, int ID) {
        String CODE = null;
        String CODE_SIX = "000000"; //六位CODE
        String CODE_EIGHT = "00000000"; //八位CODE

        String qwe = "";    //最终拼接结果变量
        String date = "";   //日期串

        //判断要生成的数字位数
        if(code == 6){
            CODE = CODE_SIX;
        }else if (code == 8){
            CODE = CODE_EIGHT;
        }

        //判断是否需要拼接日期
        if(dateNF){
            // 使用日期格式化工具类将日期格式化成想要的格式   LocalDateTime是1.8新增的   他是线程安全的
            LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
            // 定义日期格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            date = formatter.format(now);
        }
        //使用数字格式化工具类将ID转换成想要的数据格式     DecimalFormat:数字格式化工具类
        DecimalFormat dft = new DecimalFormat(CODE);
        String ncode = dft.format(ID); // 格式化为定义流水号格式
        qwe = STR + date + ncode;
        return qwe;
    }
}
