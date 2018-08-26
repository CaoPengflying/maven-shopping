import com.hd.entity.Order;
import com.hd.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class OrderTest {

    @Resource(name = "orderMapper")
    private OrderMapper orderMapper;

    @Test
    public void fun(){
        List<Order> orderList = orderMapper.selectByUserId(2);
        System.out.println(orderList);
    }
}
