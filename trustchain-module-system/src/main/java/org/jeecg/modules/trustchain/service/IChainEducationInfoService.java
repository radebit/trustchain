package org.jeecg.modules.trustchain.service;

import org.jeecg.modules.trustchain.dto.ChainEducationInfoDTO;
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

    /**
     * 审核学历
     *
     * @param chainEducationInfoDTO
     * @return
     */
    public boolean examineEducation(ChainEducationInfoDTO chainEducationInfoDTO);

    /**
     * 核验学历
     *
     * @param chainEducationInfo
     * @return
     */
    public ChainEducationInfo checkEducation(ChainEducationInfo chainEducationInfo);

    /**
     * 学生修改学历信息
     *
     * @param chainEducationInfo
     * @return
     */
    public ChainEducationInfo studentEdit(ChainEducationInfo chainEducationInfo);
}
