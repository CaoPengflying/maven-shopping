package com.hd.controller;

import com.github.pagehelper.PageInfo;
import com.hd.entity.News;
import com.hd.entity.Product;
import com.hd.service.NewsService;
import com.hd.util.Pager;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class NewsController {
    public static Integer SELECT_NOW = 7;
    @Resource(name = "newsService")
    NewsService newsService;
    @RequestMapping("/sys/news/queryNewsList")
    public String queryNewsList(Integer currentPage, Model model){
        if (currentPage == null){
            currentPage = 1;
        }
        Integer pageSize = 10;
        List<News> newsList = newsService.getNews(currentPage, pageSize);
        PageInfo<News> pageInfo = new PageInfo<>(newsList);
        String url = "sys/news/queryNewsList.action?pageSize="+pageSize;

        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("newsList",newsList);
        model.addAttribute("menu",SELECT_NOW);
        return "jsp/backend/news/newsList";
    }

    @RequestMapping("/sys/newsDetail")
    public String newsDetail(Integer id, Model model){

        News news = newsService.getNewsById(id);
        model.addAttribute("news",news);
        return "jsp/backend/news/newsDetail";
    }

}
