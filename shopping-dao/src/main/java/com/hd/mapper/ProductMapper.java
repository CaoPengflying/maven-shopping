package com.hd.mapper;

import com.hd.entity.Product;
import com.hd.queryVo.ProductQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
    List<Product> selectByCategoryIdOrKeywords(ProductQueryVo vo);

    Integer deleteById(Integer id);
}