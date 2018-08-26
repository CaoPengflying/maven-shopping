package com.hd.mapper;

import com.hd.entity.News;
import com.hd.queryVo.NewsQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News>selectNewsList();
}