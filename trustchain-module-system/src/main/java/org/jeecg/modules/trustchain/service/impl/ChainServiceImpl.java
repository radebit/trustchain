package org.jeecg.modules.trustchain.service.impl;

import cn.hutool.core.util.HexUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.jeecg.common.constant.ChainAddressConstant;
import org.jeecg.common.constant.ExamineStateConstants;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.entity.ChainProcessRecord;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;
import org.jeecg.modules.trustchain.service.IChainProcessRecordService;
import org.jeecg.modules.trustchain.service.IChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Rade
 * @Date 2021/3/26 19:52:52
 * @Description
 */
@Service
public class ChainServiceImpl implements IChainService {
    @Autowired
    private IChainEducationInfoService chainEducationInfoService;

    @Autowired
    private IChainProcessRecordService chainProcessRecordService;

    /**
     * 学历证书信息上链
     *
     * @param chainEducationInfoId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean onChain(String chainEducationInfoId) {
        // 验证学历证书是否存在
        ChainEducationInfo chainEducationInfoResult = chainEducationInfoService.getById(chainEducationInfoId);
        if (chainEducationInfoResult == null) {
            throw new JeecgBootException("学历证书不存在！");
        }
        // 创建过程记录
        ChainProcessRecord chainProcessRecord = new ChainProcessRecord();
        chainProcessRecord.setEducationId(chainEducationInfoId);
        chainProcessRecord.setProcessInfo("学历证书等待上链");
        chainProcessRecord.setOldState(chainEducationInfoResult.getExamineState());
        chainProcessRecord.setNewState(ExamineStateConstants.TO_BE_CHAIN);
        chainProcessRecordService.save(chainProcessRecord);
        // 创建交易
        String blockHash = sendTransaction(chainEducationInfoResult.getId());
        // 更新学历证书信息
        chainEducationInfoResult.setExamineState(ExamineStateConstants.CHAINING);
        chainEducationInfoResult.setChainHash(blockHash);
        if (!chainEducationInfoService.updateById(chainEducationInfoResult)) {
            throw new JeecgBootException("更新学历证书信息失败！");
        }
        // 创建过程记录
        ChainProcessRecord chainProcessRecordAfter = new ChainProcessRecord();
        chainProcessRecordAfter.setEducationId(chainEducationInfoId);
        chainProcessRecordAfter.setProcessInfo("学历证书正在上链中,交易Hash:" + blockHash);
        chainProcessRecordAfter.setOldState(ExamineStateConstants.TO_BE_CHAIN);
        chainProcessRecordAfter.setNewState(ExamineStateConstants.CHAINING);
        chainProcessRecordService.save(chainProcessRecordAfter);
        return true;
    }

    /**
     * 发送交易
     *
     * @param chainEducationInfoId
     * @return
     */
    @Override
    public String sendTransaction(String chainEducationInfoId) {
        String request = HttpRequest
                .post(ChainAddressConstant.CHAIN_URL)
                .body("{\n" +
                        "    \"method\": \"personal_sendTransaction\",\n" +
                        "    \"params\": [\n" +
                        "        {\n" +
                        "            \"from\": \"" + ChainAddressConstant.FROM_ADDRESS + "\",\n" +
                        "            \"to\": \"" + ChainAddressConstant.TO_ADDRESS + "\",\n" +
                        "            \"value\": \"0x100000\",\n" +
                        "            \"data\":\"" + "0x" + HexUtil.encodeHexStr(chainEducationInfoId) + "\"" +
                        "        },\n" +
                        "        \"outman\"\n" +
                        "    ],\n" +
                        "    \"id\": 1\n" +
                        "}")
                .timeout(50000)
                .execute()
                .body();
        String result = JSONObject.parseObject(request).getString("result");
        if (result == null) {
            throw new JeecgBootException("上链交易异常！请联系客服！");
        }
        return result;
    }

    /**
     * 根据区块Hash查询交易信息
     *
     * @param blockHash
     * @return
     */
    @Override
    public JSONObject findTransactionByBlockHash(String blockHash) {
        String body = HttpRequest
                .post(ChainAddressConstant.CHAIN_URL)
                .body("{\n" +
                        "    \"jsonrpc\": \"2.0\",\n" +
                        "    \"method\": \"eth_getTransactionByHash\",\n" +
                        "    \"params\": [\n" +
                        "        \"" + "" + blockHash + "" + "\"\n" +
                        "    ],\n" +
                        "    \"id\": 1\n" +
                        "}")
                .timeout(50000)
                .execute()
                .body();
        String result = JSONObject.parseObject(body).getString("result");
        if (result == null) {
            throw new JeecgBootException("上链交易异常！请联系客服！");
        }
        return JSONObject.parseObject(result);
    }
}
