package org.jeecg.modules.trustchain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Rade
 * @Date 2021/3/24 20:01:01
 * @Description
 */
@Api(tags="测试模块")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation("测试接口")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.OK("hello");
    }
}
