package com.bao.island.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BAO on 2018/5/4.
 */

public class DataManager {

    public static List<News> getNewsList(String url) {

        List<News> list = new ArrayList<>();
        try {
            Document xisha = Jsoup.connect(url).timeout(10000).get();
            Elements news = xisha.select("div.chunlist ul li");

            for (Element e : news) {

                String title = e.select("p a").text();
                String time = e.select("span").text();
                String href = e.select("p a").attr("abs:href");
                News item = new News(title, time, href);
                list.add(item);

//                System.out.println(title+"\n"+time+"\n"+href);
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static NewsDetail getNewsDetail(String url) {
        NewsDetail detail = null;
        Document history = null;
        StringBuilder content = new StringBuilder();
        List<String> imgs = new ArrayList<>();
        String desc, title;
        try {
            history = Jsoup.connect(url).timeout(10000).get();

            desc = history.select("div.arc div.des").text();
            title = history.select("div.arc div.title h1").text();
            System.out.println(title);
//            System.out.println(desc);
            Elements contents = history.select("div.content table tbody tr td div div");

            Elements pics = contents.select("img");
            for (Element e : pics) {
                String picUrl = e.attr("abs:src");
                System.out.println(picUrl);
                imgs.add(picUrl);
            }

//            System.out.println(imgs);
            for (Element e : contents) {
                String para = e.text();
                content.append("   " + para + "\n");
//                System.out.println(para);
            }
//            System.out.println(sb.toString());

            detail = new NewsDetail(title, desc, content.toString(), imgs);
            return detail;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return detail;
    }


    public static List<News> getSliderList(String url) {

        List<News> list = new ArrayList<>();
        try {
            Document slider = Jsoup.connect(url).timeout(1000).get();
            Elements es = slider.select("div.fc_pics div.pica");
            for (Element e : es) {

                String title = e.select("a").text();
                String imgUrl = e.select("a img").attr("abs:src");
                String href = e.select("a").attr("abs:href");
                News item = new News(title, imgUrl, href);
                list.add(item);
            }
            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }


    public  static List<News> getRecomendation(String url){
        List<News> list = new ArrayList<>();
        try {
            Document d = Jsoup.connect(url).timeout(1000).get();
            Elements es = d.select("div.hot ul li");
            for (Element e: es){
                String title = e.select("a").text();
                String href =  e.select("a").attr("abs:href");
                News item = new News(title,"xxx",href);
//                System.out.println(title+"\n"+href);
                list.add(item);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
