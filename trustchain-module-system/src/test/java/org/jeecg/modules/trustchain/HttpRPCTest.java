package org.jeecg.modules.trustchain;

import cn.hutool.core.util.HexUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.JeecgSystemApplication;
import org.jeecg.common.constant.ChainAddressConstant;
import org.jeecg.modules.trustchain.service.IChainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Rade
 * @Date 2021/3/26 21:34:34
 * @Description ETC远程RPC请求
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JeecgSystemApplication.class)
public class HttpRPCTest {
    @Autowired
    private IChainService chainService;

    @Test
    public void testFindAddress() {
        String body = HttpRequest
                .post(ChainAddressConstant.CHAIN_URL)
                .body("{\"jsonrpc\":\"2.0\",\"method\":\"eth_accounts\",\"params\":[],\"id\":1}")
                .timeout(50000)
                .execute()
                .body();
        String result = JSONObject.parseObject(body).getString("result");
        JSONArray objects = JSONObject.parseArray(result);
        objects.forEach(System.out::println);
        System.out.println(body);
    }

    @Test
    public void testSendTrade() {
        String body = HttpRequest
                .post(ChainAddressConstant.CHAIN_URL)
                .body("{\n" +
                        "    \"method\": \"personal_sendTransaction\",\n" +
                        "    \"params\": [\n" +
                        "        {\n" +
                        "            \"from\": \"" + ChainAddressConstant.FROM_ADDRESS + "\",\n" +
                        "            \"to\": \"" + ChainAddressConstant.TO_ADDRESS + "\",\n" +
                        "            \"value\": \"0x100000\"\n" +
                        "        },\n" +
                        "        \"outman\"\n" +
                        "    ],\n" +
                        "    \"id\": 1\n" +
                        "}")
                .timeout(50000)
                .execute()
                .body();
        String result = JSONObject.parseObject(body).getString("result");
        System.out.println(body);
        System.out.println("交易Hash值：" + result);
    }

    @Test
    public void testFindTrade() {
        String body = HttpRequest
                .post(ChainAddressConstant.CHAIN_URL)
                .body("{\n" +
                        "    \"jsonrpc\": \"2.0\",\n" +
                        "    \"method\": \"eth_getTransactionByHash\",\n" +
                        "    \"params\": [\n" +
                        "        \"" + "0x666569d240f83f96b47758a1041691a70626f6c9fc28e774e607b466cf4ade3e" + "\"\n" +
                        "    ],\n" +
                        "    \"id\": 1\n" +
                        "}")
                .timeout(50000)
                .execute()
                .body();
        String result = JSONObject.parseObject(body).getString("result");
        System.out.println(body);
        System.out.println("result：" + result);
    }

    @Test
    public void testServiceSend(){
        String s = chainService.sendTransaction("1111");
        System.out.println(s);
    }

    @Test
    public void testFindService(){
        System.out.println(chainService.findTransactionByBlockHash("0x244ca73131866ba9dd35690338456ff4db0bbe582abbede9e3e44095b277cb23").toString());
    }

    @Test
    public void testHex(){
        System.out.println(HexUtil.encodeHexStr("123123"));
    }
}
