package org.jeecg.modules.trustchain;

import org.jeecg.JeecgSystemApplication;
import org.jeecg.config.GeetestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Rade
 * @Date 2021/3/27 10:41:41
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgSystemApplication.class)
public class CommonTest {
    @Autowired
    private GeetestConfig geetestConfig;

    @Test
    public void test01(){
        System.out.println(geetestConfig.getCaptchaId());
        System.out.println(geetestConfig.getCaptchaKey());
    }
}
