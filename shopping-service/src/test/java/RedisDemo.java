import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_redis.xml")
public class RedisDemo {
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;
    @Test
    public void fun(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name","zhangsan");
        String name = (String) valueOperations.get("name");
        System.out.println(name);



    }
}
