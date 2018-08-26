package com.hd.queryVo;

import com.hd.entity.Product;
import com.hd.entity.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryQueryVo {
    List<ProductCategoryQueryVo>productCategoryVoList = new ArrayList<>();
    ProductCategory productCategory;
    List<Product>productList = new ArrayList<>();

    public List<ProductCategoryQueryVo> getProductCategoryVoList() {
        return productCategoryVoList;
    }

    public void setProductCategoryVoList(List<ProductCategoryQueryVo> productCategoryVoList) {
        this.productCategoryVoList = productCategoryVoList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
