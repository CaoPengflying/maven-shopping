package com.hd.service;

import com.hd.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getNews(Integer page, Integer pageSize);

    News getNewsById(Integer id);
}
