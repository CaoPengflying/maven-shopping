import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hd.entity.Product;
import com.hd.mapper.ProductMapper;
import com.hd.queryVo.ProductQueryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class ProductTest {
    @Resource(name = "productMapper")
    private ProductMapper productMapper;
//根据分类查找商品
    @Test
    public void fun(){
        ProductQueryVo vo = new ProductQueryVo();
        vo.setCategoryId(672);
        vo.setLevel(3);
        List<Product> productList = productMapper.selectByCategoryIdOrKeywords(vo);
        System.out.println(productList);
    }
//    分页查找商品
    @Test
    public void fun1(){
        ProductQueryVo vo = new ProductQueryVo();
        vo.setName("");
        PageHelper.startPage(1,5);
        List<Product> productList = productMapper.selectByCategoryIdOrKeywords(vo);
        PageInfo<Product>productPageInfo = new PageInfo<>(productList);
        System.out.println(productPageInfo);
        System.out.println(productList);
    }
}
