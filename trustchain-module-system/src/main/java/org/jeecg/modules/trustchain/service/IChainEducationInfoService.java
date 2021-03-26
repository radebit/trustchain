package org.jeecg.modules.trustchain.service;

import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 学历信息
 * @Author: Rade
 * @Date: 2021-03-26
 * @Version: V1.0
 */
public interface IChainEducationInfoService extends IService<ChainEducationInfo> {
    /**
     * 申请学历
     *
     * @param chainEducationInfo
     * @return
     */
    public boolean applyEducation(ChainEducationInfo chainEducationInfo);
}
