package com.pitest01.test;

import com.hankcs.hanlp.summary.TextRankKeyword;

import java.util.List;

//import com.hankcs.hanlp;
//关键词提取
public class Keyword_test01 {
    public static void main(String[] args) {
        String content = "太阳和月亮是什么";
        List<String> keywordList = TextRankKeyword.getKeywordList(content,3);

        System.out.println("关键词："+keywordList);

    }
}
