package com.hd.service.impl;

import com.github.pagehelper.PageHelper;
import com.hd.entity.Product;
import com.hd.mapper.ProductMapper;
import com.hd.queryVo.ProductQueryVo;
import com.hd.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource(name = "productMapper")
    private ProductMapper productMapper;
    @Override
    public List<Product> getProductListByCategoryId(Integer categoryId, Integer level, String keywords, Integer
            currentPage, Integer pageSize) {
        ProductQueryVo productQueryVo = new ProductQueryVo();
        productQueryVo.setCategoryId(categoryId);
        productQueryVo.setLevel(level);
        productQueryVo.setName(keywords);
        PageHelper.startPage(currentPage,pageSize);
        List<Product> products = productMapper.selectByCategoryIdOrKeywords(productQueryVo);
        return products;
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> getProductList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        return productMapper.selectByCategoryIdOrKeywords(new ProductQueryVo());
    }

    @Override
    public Integer addProduct(Product product) {
        return productMapper.insertSelective(product);
    }

    @Override
    public Integer deleteProductById(Integer id) {
        return productMapper.deleteById(id);
    }

    @Override
    public Integer updateProduct(Product product) {
       return productMapper.updateByPrimaryKeySelective(product);
    }
}
