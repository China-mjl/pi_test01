package com.pitest01.test;

import com.pitest01.util.PI_SerialNumber;

/*
 * STR:流水号抬头，一般票据、物料号、订单号开头拥有有意义词语缩写 如：订单-DD 商品-SP，DD-20200202-1324
 * dateNF:是否拼接日期
 * code:流水号数值的格式,设定流水号数字的位数，不满位数前面补充0；
 * ID:流水号数值的变量；
 */
public class PI_SerialNumber_test01 {
    public static void main(String[] args) {
        String serialnumber = PI_SerialNumber.serialNumber("PI_test",false,6,1);
        System.out.println(serialnumber);
    }
}
