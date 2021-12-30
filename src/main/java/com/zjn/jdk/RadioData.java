package com.zjn.jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oracle.javafx.jmx.json.JSONFactory;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RadioData {


    public static void main(String[] args) throws Exception {
        int total = 0;

        File file = new File("/Users/uxin/Downloads/zzz.txt");//Text文件
        BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
        String s = null;
        int i = 0;
        while((s = br.readLine())!=null){//使用readLine方法，一次读一行
            System.out.println(i++);

            JSONObject jsonObject = JSON.parseObject(s);
            Integer transType = jsonObject.getJSONObject("content").getJSONObject("tags").getJSONObject("entry")
                    .getJSONObject("body").getJSONObject("transType").getInteger("index");

            if (transType == 77 || transType == 81 || transType == 79) {
                Integer workDiamond = jsonObject.getJSONObject("content").getJSONObject("tags").getJSONObject("entry")
                        .getJSONObject("body").getInteger("workDiamond");
                total += workDiamond;
            }

        }
        br.close();



//        String[] split = s.split("####");
//
//        int total = 0;
//
//        for (String s1 : split) {
//
//            JSONObject jsonObject = JSON.parseObject(s1);
//            Integer transType = jsonObject.getJSONObject("content").getJSONObject("tags").getJSONObject("entry")
//                    .getJSONObject("body").getJSONObject("transType").getInteger("index");
//
//            if (transType == 77 || transType == 81 || transType == 79) {
//                Integer workDiamond = jsonObject.getJSONObject("content").getJSONObject("tags").getJSONObject("entry")
//                        .getJSONObject("body").getInteger("workDiamond");
//                total += workDiamond;
//            }
//
//        }
        System.out.println(total);




    }







}
