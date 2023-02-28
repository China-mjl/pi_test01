package com.pitest01.test;

import com.pitest01.util.date.PI_date;
import com.pitest01.util.PI_random;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 线程类
public class PI_thread {
    // 创建可以设置定时执行的线程
    public void scheduledThreadPool_test() {
        // 记录线程执行次数
        final int[] i = {0};
        // 线程池－创建固定数量的线程
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

        // 创建首次延时执行、按设置间隔时间执行的线程
        // 1、基础信息：时间、随机数、当前情绪、当前人格
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        //当前日期
                        PI_date PI_date = new PI_date();
                        String strDate = PI_date.strNowDateAll(new Date());
                        //1~10范围的随机数
                        PI_random pi_random = new PI_random();
                        int random = pi_random.randomOne(10);

                        //获取当前线程
                        Thread currentThread_one = Thread.currentThread();

                        System.out.println("1-基础信息线程："+currentThread_one.getName()
                                +"--执行次数："+i[0]++
                                +"--当前时间："+strDate
                                +"--随机数："+random
                        );
                    }
                    //首次执行延时时间；之后执行间隔时间；设置时间单位
                },0,5,TimeUnit.SECONDS);

        // 2、日常任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //当前日期
                PI_date PI_date = new PI_date();
                String strDate = PI_date.strNowDateAll(new Date());
                //1~10范围的随机数
                PI_random pi_random = new PI_random();
                int random = pi_random.randomOne(10);

                //获取当前线程
                Thread currentThread_two = Thread.currentThread();

                System.out.println("2-日常任务线程:"+currentThread_two.getName()
                        +"--执行次数："+i[0]++
                        +"--当前时间："+strDate
                        +"--随机数："+random
                );
            }
            //首次执行延时时间；之后执行间隔时间；设置时间单位
        },0,5,TimeUnit.SECONDS);

        // 3、模拟生物钟
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //当前日期
                PI_date PI_date = new PI_date();
                String strDate = PI_date.strNowDateAll(new Date());
                //1~10范围的随机数
                PI_random pi_random = new PI_random();
                int random = pi_random.randomOne(10);

                //获取当前线程
                Thread currentThread_two = Thread.currentThread();

                System.out.println("3-模拟生物钟线程:"+currentThread_two.getName()
                        +"--执行次数："+i[0]++
                        +"--当前时间："+strDate
                        +"--随机数："+random
                );
            }
            //首次执行延时时间；之后执行间隔时间；设置时间单位
        },0,5,TimeUnit.SECONDS);
    }
}