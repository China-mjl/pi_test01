package com.pitest01.util;


import com.alibaba.fastjson.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Author: xbl
 * @CreateDate: 2021/10/27 19:08
 */

//配置文件yml转properties
public class YmlToProperties {

    public static void main(String[] args) {
        String path = "D:\\admin-entry\\src\\main\\resources\\application.yml";

        LinkedHashMap<String, Object> result = ymlToPorperties(path);

        System.out.println(new JSONObject(result));
    }

    public static LinkedHashMap<String, Object> ymlToPorperties(String path) {
        LinkedHashMap<String, Object> resultMap = null;
        Yaml yaml = new Yaml();
        FileInputStream in = null;
        try {
            in = new FileInputStream(path);
            LinkedHashMap<String, Object> m = yaml.load(in);
            resultMap = mapToPorperties(m);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultMap;
    }

    public static LinkedHashMap<String, Object> mapToPorperties(LinkedHashMap<String, Object> m) {
        final LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        mapToPorperties(null, resultMap, m);
        return resultMap;
    }

    private static void mapToPorperties(String key, final LinkedHashMap<String, Object> toMap, LinkedHashMap<String, Object> fromMap) {
        Iterator<Map.Entry<String, Object>> it = fromMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            Object value = entry.getValue();
            if (value instanceof Map) {
                String relKey = entry.getKey();
                if (key != null) {
                    relKey = key + "." + entry.getKey();
                }
                mapToPorperties(relKey, toMap, (LinkedHashMap<String, Object>) value);
            } else {
                String relKey = entry.getKey();
                if (key != null) {
                    relKey = key + "." + entry.getKey();
                }
                toMap.put(relKey, entry.getValue());
            }
        }
    }

}
