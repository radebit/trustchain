package org.jeecg.modules.trustchain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.JeecgSystemApplication;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Rade
 * @Date 2021/3/30 13:24:24
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgSystemApplication.class)
public class ChainEducationTest {
    @Autowired
    private IChainEducationInfoService chainEducationInfoService;

    @Test
    public void test1(){
        ChainEducationInfo one = chainEducationInfoService.getOne(new LambdaQueryWrapper<ChainEducationInfo>().eq(ChainEducationInfo::getId, "1375698844683096065"));
        if (one==null){
            System.out.println("无结果");
        }else {
            System.out.println(one.toString());
        }
    }
}
