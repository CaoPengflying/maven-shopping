import com.hd.entity.User;
import com.hd.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class UserTest {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    /**
     * 测试主键查找
     */
    @Test
    public void fun(){
        User user = userMapper.selectByPrimaryKey(2);
        System.out.println(user);
    }

    /**
     * 测试通过userName查找
     */
    @Test
    public void fun1(){
        User user = userMapper.selectByUserName("admin");
        System.out.println("***********"+user);
    }
}
