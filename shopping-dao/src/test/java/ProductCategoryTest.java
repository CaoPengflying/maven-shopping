import com.hd.entity.ProductCategory;
import com.hd.entity.User;
import com.hd.mapper.ProductCategoryMapper;
import com.hd.mapper.UserMapper;
import com.hd.view.ProductCategoryView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class ProductCategoryTest {
    @Resource(name = "productCategoryMapper")
    private ProductCategoryMapper productCategoryMapper;

    @Test
    public void fun(){
        List<ProductCategory> productCategories = productCategoryMapper.selectByParentId(0);
        System.out.println(productCategories);
    }
    @Test
    public void fun1(){
        List<ProductCategoryView> productCategories = productCategoryMapper.selectList();
        System.out.println(productCategories);
    }
}
