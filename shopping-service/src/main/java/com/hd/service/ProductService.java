package com.hd.service;


import com.hd.entity.Product;

import java.util.List;

public interface ProductService {
   List<Product> getProductListByCategoryId(Integer categoryId, Integer level, String keywords, Integer currentPage, Integer pageSize);

    Product getProductById(Integer id);

    List<Product> getProductList(Integer currentPage, Integer pageSize);

    Integer addProduct(Product product);

    Integer deleteProductById(Integer id);

    Integer updateProduct(Product product);
}
