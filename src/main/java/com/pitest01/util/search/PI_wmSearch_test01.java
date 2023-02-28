package com.pitest01.util.search;


public class PI_wmSearch_test01 {
    public static void main(String[] args) {

        /*//爬取分类标识：百度百科
        String seachSign = "BaiKe";
        //爬取网站URL
        String seachURL = "https://baike.baidu.com/item/";//百度百科
        //爬取内容
        String[] seachContent = {"火星","太阳","月亮","中国","三维空间","发芽马铃薯中毒"};
        for (int i = 0;i < seachContent.length;i++){
            //定向爬取数据
            PI_wmSearch.baiKe(seachSign,seachURL,seachContent[i]);
            //获取爬取到的数据
            System.out.println(PI_wmSearch.reContent());
        }*/

        /*//爬取分类标识：百度名医
        String seachSign = "MingYi";
        //爬取网站URL
        String seachURL = "https://www.baikemy.com/search/disease/list?&keyWord=";//百科名医
        //爬取内容
        String[] seachContent = {"牙疼怎么办","头疼怎么办","晚上睡不着咋回事呢"};
        for (int i = 0;i < seachContent.length;i++){
            //定向爬取数据
            PI_wmSearch.search(seachSign,seachURL,seachContent[i]);
            //获取爬取到的数据
            System.out.println(PI_wmSearch.reContent());
        }*/

        //https://zhidao.baidu.com/
        //爬取分类标识：百度知道
        //爬取分类标识
        String seachSignOne = "ZhiDaoLink";
        String seachSignTwo = "ZhiDao";
        String cookie = "https://zhidao.baidu.com/search?";
        //爬取网站URL
        String seachURL = "https://zhidao.baidu.com/search?lm=0&rn=10&pn=0&fr=search&ie=gbk&dyTabStr=null&word=";
        //爬取内容
        String[] seachContent = {"java","c++语言","python是什么"};
        for (int i = 0,j =1;i < seachContent.length;i++,j++){
            //定向爬取数据,获得要爬取数据的链接
            PI_wmSearch.search(seachSignOne,seachURL,seachContent[i],cookie);
            //获取爬取到的数据的内容
            PI_wmSearch.search(seachSignTwo,PI_wmSearch.reContent(),seachContent[i],cookie);
            System.out.println("-------------------"+j+"--------------------");
            System.out.println(PI_wmSearch.reContent());
        }

    }
}
