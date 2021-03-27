package org.jeecg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Rade
 * @Date 2021/3/27 10:38:38
 * @Description 极验配置类
 */
@Component
public class GeetestConfig {
    @Value("${geetest.captcha-id}")
    private String captchaId;

    @Value("${geetest.captcha-key}")
    private String captchaKey;

    @Value("${geetest.url}")
    private String geetestUrl;

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getGeetestUrl() {
        return geetestUrl;
    }

    public void setGeetestUrl(String geetestUrl) {
        this.geetestUrl = geetestUrl;
    }
}
