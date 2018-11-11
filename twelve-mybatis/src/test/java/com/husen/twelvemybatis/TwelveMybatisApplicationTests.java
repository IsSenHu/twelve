package com.husen.twelvemybatis;

import com.husen.twelvemybatis.config.EnableTestSelfStarter;
import com.husen.twelvemybatis.config.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTestSelfStarter
public class TwelveMybatisApplicationTests {
    @Autowired
    private TestBean testBean;
    @Test
    public void contextLoads() {
        testBean.out();
    }

}
