package org.jeecg.modules.trustchain.service.impl;

import org.jeecg.modules.trustchain.service.IChainService;
import org.springframework.stereotype.Service;

/**
 * @Author Rade
 * @Date 2021/3/26 19:52:52
 * @Description
 */
@Service
public class ChainServiceImpl implements IChainService {
    /**
     * 学历证书信息上链
     *
     * @param chainEducationInfoId
     * @return
     */
    @Override
    public boolean onChain(String chainEducationInfoId) {

        return false;
    }
}
