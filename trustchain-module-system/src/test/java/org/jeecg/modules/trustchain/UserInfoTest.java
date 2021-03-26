package org.jeecg.modules.trustchain;

/**
 * @Author Rade
 * @Date 2021/3/26 14:07:07
 * @Description
 */

import org.jeecg.JeecgSystemApplication;
import org.jeecg.common.constant.enums.DeptEnum;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 系统用户单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgSystemApplication.class)
public class UserInfoTest {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    @Test
    public void testDept() {
        SysUser sysUser = sysUserService.getUserByName("zhangsan");
        System.out.println(sysUser.toString());
        System.out.println("=====");
        List<String> departIdsByUsername = sysBaseAPI.getDepartIdsByUsername(sysUser.getUsername());
        System.out.println(departIdsByUsername.contains(DeptEnum.STUDENT.getDeptId()));

    }
}
