package org.wefine.spring;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.wefine.spring.config.core.SpringMvcConfig;
import org.wefine.spring.config.core.SpringRootConfig;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = {
//        SpringRootConfig.class,
//        SpringMvcConfig.class
//})
public class AbstractSpringTest {

    @Resource
    protected ApplicationContext context;


}
