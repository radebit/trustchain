package org.jeecg.modules.trustchain.service;


import com.alibaba.fastjson.JSONObject;

/**
 * @Author Rade
 * @Date 2021/3/26 19:52:52
 * @Description
 */
public interface IChainService {
    /**
     * 学历证书信息上链
     *
     * @param chainEducationInfoId
     * @return
     */
    public boolean onChain(String chainEducationInfoId);

    /**
     * 发送交易
     *
     * @param chainEducationInfoId
     * @return
     */
    public String sendTransaction(String chainEducationInfoId);

    /**
     * 根据区块Hash查询交易信息
     *
     * @param blockHash
     * @return
     */
    public JSONObject findTransactionByBlockHash(String blockHash);

}
