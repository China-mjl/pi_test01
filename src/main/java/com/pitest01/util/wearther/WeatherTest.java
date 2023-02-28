package com.pitest01.util.wearther;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WeatherTest {
    public static void main(String[] args) throws IOException {
        //now、day、week
        //0：今天,1:明天,2:后天。。。。
        int searchScope = 2;
        String cityCode = "101010100";  //城市代码
        //String msg = "";
        GetWeatherMain getWeatherMain = new GetWeatherMain();
        List<Map<String,String>> list = getWeatherMain.searchWeather(cityCode, searchScope);

        //查询一周的天气概况数据
        if (searchScope == 7){
            //System.out.println("--------------------------------------------------------system:"+list.size());
            for (int i=0;i<list.size();i++){
                System.out.println("--------------------------------------------------------system:"+i);
                System.out.println(list.get(i));
            }

        }else {//查询某一天的天气详情数据
            for (int i=0;i<list.size();i++){
                //查询当前时间天气明细数据——此数据只有当前时间才有
                if (searchScope==0&&i==list.size()-1){
                    System.out.println("--------------------------------------------------------system:"+i);
                    System.out.println(list.get(i));
                }else {//查询某一天的天气详情数据
                    System.out.println("--------------------------------------------------------system:"+i);
                    System.out.println(list.get(i));
                }
            }
        }


       /* msg = "【XXX地区天气预报】\n"+ list.get(0).toString()+"\n"+ "-----------------------------" + "\n" + list.get(1).toString();
        //msg = msg.replace('=','：').replace('{',' ').replace('}',' ').replace(',','\n');
        System.out.println("--------------------------------------------------------system");
        System.out.println(msg);*/
    }
}
