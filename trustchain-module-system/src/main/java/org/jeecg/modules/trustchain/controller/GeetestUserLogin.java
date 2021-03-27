package org.jeecg.modules.trustchain.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.api.vo.Result;
import org.jeecg.config.GeetestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Rade
 * @Date 2021/3/27 10:49:49
 * @Description 通过滑动验证登录
 */
@RestController
@RequestMapping("/geetest")
@Api(tags = "通过滑动验证登录")
public class GeetestUserLogin {

    @Autowired
    private GeetestConfig geetestConfig;

    @GetMapping("/register")
    @ApiOperation(value = "获取验证code", notes = "获取验证code")
    public Result<?> register(String clientType) {
        String result = HttpRequest
                .get(geetestConfig.getGeetestUrl()
                        + "/register.php?client_type="
                        + clientType
                        + "&digestmod=md5&gt="
                        + geetestConfig.getCaptchaId()
                        + "&json_format=1")
                .execute()
                .body();
        // md5加密处理
        String originChallenge = JSONObject.parseObject(result).getString("challenge");
        if (originChallenge == null) {
            return Result.error("获取验证失败！");
        }
        return Result.OK(SecureUtil.md5(originChallenge + geetestConfig.getCaptchaKey()));
    }
}
