package com.enzhi.recruit.utils;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * JSON 转换工具
 */
public class JsonUtils {


    public static <T> T jsonToObject(String jsonStr, Type clazz) {
        try {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(jsonStr, clazz);
        } catch (com.google.gson.JsonSyntaxException e) {
            LogUtils.e("json解析异常:"+e.toString());
            return null;
        }
    }

    public static <T> String objectToJson(T t) {
        try {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(t);
        } catch (com.google.gson.JsonSyntaxException e) {
            LogUtils.e("json解析异常:"+e.toString());
            return null;
        }
    }

    /**
     * 反序列化bean字段不完整或者字段值为null的方法
     * @param t
     * @param <T> 实体类
     * @return
     */
    public static <T> T objectToJsonAllowNull(String json,Class<T> t) {
        Gson gson = new GsonBuilder().serializeNulls().setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        return gson.fromJson(json,t);
    }



    /**
     * map 转 Json 字符串
     */
    public static String mapToJsonString(Map<String, String> map) {
        StringBuilder tempParams = new StringBuilder();
        tempParams.append("{");
        int pos = 0;
        try {
            for (String key : map.keySet()) {
                if (pos > 0) {
                    tempParams.append(",");
                }
                String value = map.get(key);
                if (value != null && (value.startsWith("{") || value.startsWith("["))) {
                    tempParams.append(String.format("\"%s\":%s", key, value));
                } else {
                    tempParams.append(String.format("\"%s\":\"%s\"", key, value));
                }
                pos++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tempParams.replace(tempParams.length(), tempParams.length(), "}");
        return tempParams.toString();
    }

    /**
     * 去除特殊字符
     */
    public static String cleanSpecialCharacter(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.toCharArray()[i];
            switch (c) {
                case '\"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    //在ASCⅡ码中，第0～31号及第127号(共33个)是控制字符或通讯专用字符
                    if (c > 31 && c != 127) {
                        sb.append(c);
                    }
                    break;
            }
        }
        return sb.toString();
    }
}
