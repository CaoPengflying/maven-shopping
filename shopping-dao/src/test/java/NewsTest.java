import com.github.pagehelper.PageHelper;
import com.hd.entity.News;
import com.hd.mapper.NewsMapper;
import com.hd.queryVo.NewsQueryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_dao.xml")
public class NewsTest {
    @Resource(name = "newsMapper")
    private NewsMapper newsMapper;

    @Test
    public void fun(){
        PageHelper.startPage(1,5);
        List<News> news = newsMapper.selectNewsList();
//        News news = newsMapper.selectByPrimaryKey(531);
        System.out.println(news);
    }
}
