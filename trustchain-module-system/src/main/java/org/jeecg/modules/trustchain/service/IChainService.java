package org.jeecg.modules.trustchain.service;


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
}
