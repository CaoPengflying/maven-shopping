package com.hd.service.impl;

import com.github.pagehelper.PageHelper;
import com.hd.entity.News;
import com.hd.mapper.NewsMapper;
import com.hd.queryVo.NewsQueryVo;
import com.hd.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("newsService")
public class NewsServiceImpl implements NewsService {
    @Resource(name = "newsMapper")
    private NewsMapper newsMapper;
    @Override
    public List<News> getNews(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<News> news = newsMapper.selectNewsList();
        return news;
    }

    @Override
    public News getNewsById(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }
}
