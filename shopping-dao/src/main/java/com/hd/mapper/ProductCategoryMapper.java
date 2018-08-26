package com.hd.mapper;

import com.hd.entity.ProductCategory;
import com.hd.view.ProductCategoryView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    List<ProductCategory> selectByParentId(Integer id);
    List<ProductCategory> selectByType(Integer type);

    List<ProductCategoryView>selectList();


}