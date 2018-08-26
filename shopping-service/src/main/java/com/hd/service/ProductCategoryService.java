package com.hd.service;

import com.hd.entity.ProductCategory;
import com.hd.queryVo.ProductCategoryQueryVo;
import com.hd.view.ProductCategoryView;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryQueryVo> selectProductCategory();

    List<ProductCategoryView> getProductCategoryList(Integer currentPage, Integer pageSize);

    ProductCategory getProductCategoryById(Integer id);


    List<ProductCategory> getProductCategoryByType(int i);

    List<ProductCategory> getProductCategoryByParentId(Integer parentId);

    Integer updateProductCategory(ProductCategory productCategory);

    Integer addProductCategory(ProductCategory productCategory);

    Integer deleteProductCategoryById(Integer id);
}
