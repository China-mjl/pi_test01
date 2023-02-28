package com.pitest01.util.wearther;

import com.pitest01.util.date.PI_date;
import com.pitest01.util.PI_Format;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class GetWeatherMain {
    /**
     * @param
     * @return 获取未来7天的天气预报
     */
    public List<Map<String, String>> searchWeather(String id,int searchScope) throws IOException {

        // 封装结果
        List<Map<String,String>> weatherList=new ArrayList<>();
        /**
         * 1、未来七天的天气概况数据
         */
        if (searchScope==7){
            String result= GetWeather.getResult("http://www.weather.com.cn/weather/"+id+".shtml");
            Document document= Jsoup.parse(result);
            Elements elements;

            // 获取日期和星期
            elements=document.select("h1");
            List<String> dateList=new ArrayList<>();
            List<String> dayList=new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                String text=elements.get(i).text();
                int length=text.length();
                dateList.add(text.substring(0,length-4));
                dayList.add(text.substring(length-3,length-1));
            }
            //System.out.println(dateList);
            //System.out.println(dayList);

            // 获取天气
            elements=document.select("p[class=wea]");
            List<String> weatherList01=new ArrayList<>();
            for (Element item : elements) {
                weatherList01.add(item.text());
            }
            //System.out.println(weatherList);

            // 获取温度，最高温和最低温
            elements=document.select("p[class=tem]");
            int i=0;
            List<String> highTempList=new ArrayList<>();
            List<String> lowTempList=new ArrayList<>();
            for (Element item : elements) {
                highTempList.add(item.select("span").text());
                lowTempList.add(item.select("i").text());
            }
            //System.out.println(highTempList);
            //System.out.println(lowTempList);
            // 封装结果，每天一行，未来7天
            for (int j = 0; j < 7; j++) {
                Map<String,String> weekMap = new LinkedHashMap<>();
                //日期
                weekMap.put("weekTime#"+j,dateList.get(j));
                //星期
                weekMap.put("week#"+j,dayList.get(j));
                //天气
                weekMap.put("weekWeather#"+j,weatherList01.get(j));
                if (j == 0){
                    //当前温度
                    weekMap.put("weekTemperature#"+j,lowTempList.get(j));
                }else {
                    //最高温度
                    weekMap.put("weekTemperatureMax#"+j,highTempList.get(j));
                    //最底温度
                    weekMap.put("weekTemperatureMin#"+j,lowTempList.get(j));
                }
                weatherList.add(weekMap);
            }


        }else {//未来一周某天的天气情况
            String result = GetWeather.getResult("http://www.weather.com.cn/weather1d/" + id + ".shtml");

            Document document = Jsoup.parse(result);
            Elements elements;

            elements = document.select("div[class=left-div] script");
            String strWeather = PI_Format.htmlToStr(elements.toString());
            String[] weekWeatherAll = strWeather.split("var observe24h_data = ");

            //解析json数据hour3data
            /**
             * 2、未来七天的天气详情数据
             */
            String[] weekWeather = weekWeatherAll[0].split("\"7d\":")[1].split("\",\"");
            for (int i = 0; i < weekWeather.length; i++) {
                String strWeekWeather = weekWeather[i].replaceAll("\"", "");
                //System.out.println(strWeekWeather);
                //根据day筛选需要的某一天详细数据
                if (strWeekWeather.contains(Integer.valueOf(PI_date.strNowDateD(new Date())) + searchScope + "日")) {
                    Map dayMap = new LinkedHashMap();
                    //System.out.println(strWeekWeather);
                    String[] searchWea = strWeekWeather.split(",");
                    for (int j = 0; j < searchWea.length; j++) {
                        if (j != 1 && j != 6) {
                            switch (j) {
                                case 0://时间
                                    dayMap.put("dayTime#"+i, searchWea[j]);
                                case 2://天气
                                    dayMap.put("dayWeather#"+i, searchWea[j]);
                                case 3://温度
                                    dayMap.put("dayTemperature#"+i, searchWea[j]);
                                case 4://风向
                                    dayMap.put("dayWindDirection#"+i, searchWea[j]);
                                case 5://风级
                                    dayMap.put("dayWindScale#"+i, searchWea[j]);

                            }

                        }
                    }
                    weatherList.add(dayMap);

                }
            }


            //System.out.println(hashMapDay.toString());
            //System.out.println("hashMapDay.size()："+hashMapDay.size()/5);
            //System.out.println("--------------------------------------------------------");

            /**
             * 3、查询当前时间天气的明细数据
             */
            if (searchScope==0){
                //System.out.println(weekWeatherAll[1]);
                //解析json数据observe24h_data
                //当前时间天气数据
                String[] strNowDayWeather = weekWeatherAll[1].split("\"od21\":")[1].replaceAll("\"", "").split(",");
                //上个时间点天气数据
                String[] strFrontDayWeather = weekWeatherAll[1].split("\"od21\":")[2].replaceAll("\"", "").split(",");
                Map nowDetailMap = new LinkedHashMap();

                for (int i = 0; i < strNowDayWeather.length; i++) {
                    //System.out.println(strNowDayWeather[i]);
                    if (i == 0) {//当前时间
                        nowDetailMap.put("nowTime", PI_date.strNowDateYmdHm(new Date()));
                    } else {
                        String key = strNowDayWeather[i].split(":")[0];
                        String value = "";
                        //空气质量的值可能为空
                        if (strNowDayWeather[i].split(":").length == 2) {
                            value = strNowDayWeather[i].split(":")[1];
                        }else {
                            if (i==7)
                                value = strFrontDayWeather[i].split(":")[1];
                        }

                        switch (key) {//当前温度
                            case "od22":
                                nowDetailMap.put("nowTemperature", value + "℃");
                            case "od24"://当前风向
                                nowDetailMap.put("nowWindDirection", value);
                            case "od25"://当前风级
                                nowDetailMap.put("nowWindScale", value + "级");
                            case "od26"://当前降水量
                                nowDetailMap.put("nowPrecipitation", value + "毫米");
                            case "od27"://当前空气湿度
                                nowDetailMap.put("nowAirHumidity", value + "%");
                            case "od28"://当前空气质量
                                nowDetailMap.put("nowAirQuality", value);
                        }

                    }

                }

                weatherList.add(nowDetailMap);
            }

        }

        //System.out.println(hashMapMX.toString());


        return weatherList;
    }
}
