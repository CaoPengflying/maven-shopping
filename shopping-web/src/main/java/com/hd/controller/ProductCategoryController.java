package com.hd.controller;

import com.github.pagehelper.PageInfo;
import com.hd.entity.News;
import com.hd.entity.ProductCategory;
import com.hd.queryVo.ProductCategoryQueryVo;
import com.hd.service.NewsService;
import com.hd.service.ProductCategoryService;
import com.hd.util.Pager;
import com.hd.view.ProductCategoryView;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductCategoryController {
    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;
    @Resource(name = "newsService")
    private NewsService newsService;

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        List<ProductCategoryQueryVo> productCategoryVoList = productCategoryService.selectProductCategory();
        model.addAttribute("productCategoryVoList",productCategoryVoList);
        Integer page = 1, pageSize = 5;
        List<News> news = newsService.getNews(page, pageSize);
        model.addAttribute("news",news);
        return "jsp/pre/index";
    }

    /**
     * 管理员分类管理页面
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("/sys/getProductCategoryList")
    public String getProductCategoryList(Integer currentPage, Model model){
        if (currentPage == null){
            currentPage = 1;
        }
        Integer pageSize = 8;
        List<ProductCategoryView>productCategoryList = productCategoryService.getProductCategoryList(currentPage,pageSize);
        model.addAttribute("productCategoryList",productCategoryList);
        PageInfo<ProductCategoryView>pageInfo = new PageInfo<>(productCategoryList);
        String url = "sys/getProductCategoryList.action?pageSize=0";
        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("menu",4);
        return "jsp/backend/productCategory/productCategoryList";
    }


    /**
     * 添加分类
     * @return
     */
    @RequestMapping("/sys/addProductCategory")
    public @ResponseBody Object addProductCategory(ProductCategory productCategory, Integer productCategoryLevel1,
                                     Integer productCategoryLevel2){
        Map map = new HashMap();
        productCategory = setProductCategoryParentId(productCategory,productCategoryLevel1,productCategoryLevel2);
        Integer status = productCategoryService.addProductCategory(productCategory);
        map.put("status",status);
        return map;
}
    @RequestMapping("/sys/toAddProductCategory")
    public String toAddProductCategory(Model model){
        List<ProductCategory> productCategoryList1 = productCategoryService.getProductCategoryByType(1);
        model.addAttribute("productCategoryList1",productCategoryList1);

        return "jsp/backend/productCategory/toAddProductCategory";
    }

    /**
     * 修改商品分类
     * @param productCategory
     * @param productCategoryLevel1
     * @param productCategoryLevel2
     * @return
     */
    @RequestMapping("/sys/modifyProductCategory")
public @ResponseBody Object modifyProductCategory(ProductCategory productCategory, Integer productCategoryLevel1,
                                                  Integer productCategoryLevel2){
        Map map = new HashMap();
        productCategory = setProductCategoryParentId(productCategory,productCategoryLevel1,productCategoryLevel2);
        Integer status = productCategoryService.updateProductCategory(productCategory);
        map.put("status",status);
        return map;
}

    /**
     * 通过父类id查找商品分类列表
     * @param parentId
     * @return
     */
    @RequestMapping("sys/queryProductCategoryList")
public @ResponseBody Object queryProductCategoryList(Integer parentId){
    Map map = new HashMap();
    try {
        List<ProductCategory> productCategoryByParentId = productCategoryService.getProductCategoryByParentId(parentId);
        map.put("status",1);
        map.put("data",productCategoryByParentId);
    } catch (Exception e) {
        e.printStackTrace();
        map.put("status",0);
    }
    return map;
    }
@RequestMapping("/sys/getProductCategoryById")
    public String getProductCategoryById(Integer id, Model model){
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        model.addAttribute("productCategory",productCategory);
    List<ProductCategory> productCategoryList1 = productCategoryService.getProductCategoryByType(1);
    Integer parentId = productCategory.getParentId();
    ProductCategory parentProductCategory = productCategoryService.getProductCategoryById(parentId);
    if(parentProductCategory != null){
        List<ProductCategory> productCategoryList2 = productCategoryService.getProductCategoryByParentId
                (parentProductCategory.getParentId());
        model.addAttribute("productCategoryList2",productCategoryList2);
    }

    model.addAttribute("parentProductCategory",parentProductCategory);
    model.addAttribute("productCategoryList1",productCategoryList1);
    return "jsp/backend/productCategory/toAddProductCategory";
}
@RequestMapping("sys/deleteProductCategory")
public @ResponseBody Object deleteProductCategory(Integer id, Integer type){
        Map map = new HashMap();
        Integer status = productCategoryService.deleteProductCategoryById(id);
        map.put("status",status);
        return map;
}



    /**
     * 设置商品管理的级别
     * @param productCategory
     * @param productCategoryLevel1
     * @param productCategoryLevel2
     * @return
     */
    public ProductCategory setProductCategoryParentId(ProductCategory productCategory, Integer productCategoryLevel1,
                                       Integer productCategoryLevel2){
    if ( productCategory.getType() == 1){
        productCategory.setParentId(0);
    }else if(productCategory.getType() == 2){
        productCategory.setParentId(productCategoryLevel1);
    }else{
        productCategory.setParentId(productCategoryLevel2);
    }
    return productCategory;
}


}
