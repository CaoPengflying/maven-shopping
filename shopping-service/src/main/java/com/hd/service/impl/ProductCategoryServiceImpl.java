package com.hd.service.impl;

import com.github.pagehelper.PageHelper;
import com.hd.entity.ProductCategory;
import com.hd.mapper.ProductCategoryMapper;
import com.hd.queryVo.ProductCategoryQueryVo;
import com.hd.service.ProductCategoryService;
import com.hd.view.ProductCategoryView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource(name = "productCategoryMapper")
    private ProductCategoryMapper productCategoryMapper;
    @Override
    public List<ProductCategoryQueryVo> selectProductCategory() {
        List<ProductCategoryQueryVo>productCategoryQueryVos = new ArrayList<>();
//        获得所有的一级分类
        List<ProductCategory> productCategories = productCategoryMapper.selectByParentId(0);

        for (ProductCategory p1:productCategories) {

            ProductCategoryQueryVo productCategoryQueryVo1 = new ProductCategoryQueryVo();
            productCategoryQueryVo1.setProductCategory(p1);
            List<ProductCategory> productCategories2 = productCategoryMapper.selectByParentId(p1.getId());
            for (ProductCategory p2 : productCategories2) {
                ProductCategoryQueryVo productCategoryQueryVo2 = new ProductCategoryQueryVo();

                List<ProductCategory> productCategories3 = productCategoryMapper.selectByParentId(p2.getId());
                for (ProductCategory p3 : productCategories3) {
                    ProductCategoryQueryVo productCategoryQueryVo3 = new ProductCategoryQueryVo();
                    productCategoryQueryVo3.setProductCategory(p3);
                    productCategoryQueryVo2.getProductCategoryVoList().add(productCategoryQueryVo3);

                }
                productCategoryQueryVo2.setProductCategory(p2);
                productCategoryQueryVo1.getProductCategoryVoList().add(productCategoryQueryVo2);
            }


            productCategoryQueryVos.add(productCategoryQueryVo1);



        }
        return productCategoryQueryVos;
    }

    @Override
    public List<ProductCategoryView> getProductCategoryList(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
       return productCategoryMapper.selectList();
    }

    @Override
    public ProductCategory getProductCategoryById(Integer id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductCategory> getProductCategoryByType(int i) {
        return productCategoryMapper.selectByType(i);
    }

    @Override
    public List<ProductCategory> getProductCategoryByParentId(Integer parentId) {
        return productCategoryMapper.selectByParentId(parentId);
    }

    @Override
    public Integer updateProductCategory(ProductCategory productCategory) {
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public Integer addProductCategory(ProductCategory productCategory) {
        return productCategoryMapper.insertSelective(productCategory);
    }

    @Override
    public Integer deleteProductCategoryById(Integer id) {
        return productCategoryMapper.deleteByPrimaryKey(id);
    }


}
