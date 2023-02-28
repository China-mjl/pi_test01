package com.pitest01.test;

import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.io.IOException;
import java.util.List;
//分词测试
public class Participle_test01 {
    public static <Exceptin> void main(String[] args) throws IOException {

        //是否启用用户词典（中文分词≠词典，不用词典照样分词。）
        NLPTokenizer.ANALYZER.enableCustomDictionary(true);
        //是否开启词性标注
        NLPTokenizer.ANALYZER.enablePartOfSpeechTagging(true);

        //String str01 = "我新造了一个词叫幻想乡你能识别并正确标注词性吗？我是中国人！一字长蛇阵，龚雪一梅花。";
        String str01 = "我感觉自己的心情很悲伤,我不高兴并且十分难受。啦啦啦啦啦啦！哈哈哈哈哈哈。";
        //切分为句子形式
        List list01 = NLPTokenizer.seg2sentence(str01,false);
        System.out.println("初始状态："+list01);
        System.out.println("-------------------------------------");
        //List list02 = new ArrayList();
        StringBuilder strb01 = new StringBuilder();
        for (Object list:list01) {
            //null的存在是因为关闭了词性标注
            String str02 = list.toString().replaceAll("null,","")
                    //去除句子中的所有空格
                    //.replaceAll(" ","")
                    //去除中括号
                    .replaceAll("\\[","")
                    .replaceAll("\\]","");
            strb01.append(str02+", ");

        }
        System.out.println("strb01："+strb01);

    }
}
