package com.hd.controller;

import com.github.pagehelper.PageInfo;
import com.hd.entity.Product;
import com.hd.entity.ProductCategory;
import com.hd.queryVo.ProductCategoryQueryVo;
import com.hd.service.ProductCategoryService;
import com.hd.service.ProductService;
import com.hd.util.Pager;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productCategoryService")
    private ProductCategoryService productCategoryService;

    /**
     * 商品分类查询
     * @param category
     * @param level
     * @param keywords
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("/product/list")
    public String list(Integer category, Integer level, String keywords, Integer currentPage, Model model){
        System.out.println("%%%%%%%%%%: "+category +"*****"+level + "*****"+keywords);
        if (currentPage == null){
            currentPage = 1;
        }
        Integer pageSize = 5;
        List<Product> productList = productService.getProductListByCategoryId(category, level, keywords, currentPage,
                pageSize);
        PageInfo<Product>pageInfo = new PageInfo<>(productList);
        if (category == null){
            category = -1;
            level = -1;
        }
        String url = "product/list.action"+"?category="+category+"&level="+level;

        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("productList",productList);

        List<ProductCategoryQueryVo> productCategoryVoList = productCategoryService.selectProductCategory();
        model.addAttribute("productCategoryVoList",productCategoryVoList);
        return "/jsp/pre/product/queryProductList";
    }
    @RequestMapping("/product/productDetail")
    public String productDetail(Integer id, Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product",product);
        return "jsp/pre/product/productDeatil";
    }

    /**
     * 后台管理的商品管理
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping("sys/getProductList")
    public String getProductList(Integer currentPage, Model model){
        if (null == currentPage){
            currentPage = 1;
        }
        Integer pageSize = 5;
        List<Product> productList = productService.getProductList(currentPage, pageSize);
        PageInfo<Product>pageInfo = new PageInfo<>(productList);
        String url = "sys/getProductList.action?pageSize=5";

        Pager pager = new Pager(currentPage,pageInfo.getPages(),url);
        model.addAttribute("pager",pager);
        model.addAttribute("menu",5);
        model.addAttribute("productList",productList);

        return "jsp/backend/product/productList";
    }

    /**
     * 跳转到添加商品页面
     * @return
     */

    @RequestMapping("sys/toAddProduct")
    public String toAddProduct(Model model){
        List<ProductCategory> productCategoryList1 = productCategoryService.getProductCategoryByType(1);
        model.addAttribute("menu",6);
        model.addAttribute("productCategoryList1",productCategoryList1);
        return "jsp/backend/product/toAddProduct";
    }

    /**
     * 维护（增加和修改）商品过的信息
     *
     * @param product
     * @param model
     * @return
     */
    @RequestMapping("sys/maintainProduct.action")
    public String maintainProduct(Product product, Model model, HttpServletRequest request) throws IOException {
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String expresion = "";
                    int i = file.getOriginalFilename().lastIndexOf(".");
                    if (i != -1){
                        expresion = file.getOriginalFilename().substring(i + 1);
                    }
                    String path=UUID.randomUUID().toString()+"."+expresion;
                    //上传
                    file.transferTo(new File(request.getServletContext().getRealPath("/statics/files/"+
                            path)));
                    product.setFileName(path);
                    if (product.getId()==null || product.getId().equals("")){
                        Integer row = productService.addProduct(product);
                    }else {
                        Integer row = productService.updateProduct(product);
                    }
                }

            }

        }

        return "redirect:/sys/getProductList.action";
    }

    /**
     * 删除商品，虚拟删除，将字段isDelete改为1
     * @param id
     * @return
     */
    @RequestMapping("sys/deleteProductById")
    public @ResponseBody Object deleteProductById(Integer id){
        Map map = new HashMap();
       Integer status = productService.deleteProductById(id);
       map.put("status",status);
        return map;
    }

    /**
     * 跳转到更新商品页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("sys/toUpdateProduct")
    public String toUpdateProduct(Integer id, Model model){
        Product product = productService.getProductById(id);
        List<ProductCategory> productCategoryList1 = productCategoryService.getProductCategoryByType(1);
        ProductCategory productCategoryById = productCategoryService.getProductCategoryById(product.getCategoryLevel2Id());
        List<ProductCategory> productCategoryList2 = productCategoryService.getProductCategoryByParentId
                (productCategoryById.getParentId());
        ProductCategory productCategoryById2 = productCategoryService.getProductCategoryById(product
                .getCategoryLevel3Id());
        List<ProductCategory> productCategoryList3 = productCategoryService.getProductCategoryByParentId
                (productCategoryById2.getParentId());

        model.addAttribute("productCategoryList1",productCategoryList1);
        model.addAttribute("productCategoryList2",productCategoryList2);
        model.addAttribute("productCategoryList3",productCategoryList3);
        model.addAttribute("product",product);
        model.addAttribute("menu",6);

        return "jsp/backend/product/toAddProduct";

    }
}
