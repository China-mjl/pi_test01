package com.pitest01.util;

import java.util.concurrent.ThreadLocalRandom;

//随机数类
public class PI_random {
    //生成int类型的随机
    public int randomOne(int bound){
        //设置生成的最大数范围
        int random = ThreadLocalRandom.current().nextInt(bound);
        return random;
    }
}
