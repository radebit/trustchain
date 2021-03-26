package org.jeecg.modules.trustchain.service.impl;

import cn.hutool.core.lang.Assert;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.constant.ExamineStateConstants;
import org.jeecg.common.constant.enums.DeptEnum;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.mapper.ChainEducationInfoMapper;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 学历信息
 * @Author: Rade
 * @Date: 2021-03-26
 * @Version: V1.0
 */
@Service
public class ChainEducationInfoServiceImpl extends ServiceImpl<ChainEducationInfoMapper, ChainEducationInfo> implements IChainEducationInfoService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    /**
     * 申请学历
     *
     * @param chainEducationInfo
     * @return
     */
    @Override
    public boolean applyEducation(ChainEducationInfo chainEducationInfo) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        // 判断是否是学生用户发起的
        if (!sysBaseAPI.getDepartIdsByUsername(loginUser.getUsername()).contains(DeptEnum.STUDENT.getDeptId())) {
            throw new JeecgBootException("只有学生用户才能申请学历认证！");
        }
        SysUser sysUser = sysUserService.getUserByName(loginUser.getUsername());
        chainEducationInfo.setUserId(sysUser.getId());
        chainEducationInfo.setUserName(sysUser.getUsername());
        chainEducationInfo.setSex(sysUser.getSex().toString());
        chainEducationInfo.setRealName(sysUser.getRealname());
        chainEducationInfo.setBirthDate(sysUser.getBirthday());
        chainEducationInfo.setNation(sysUser.getNation());
        chainEducationInfo.setIdNumber(sysUser.getIdNumber());
        chainEducationInfo.setEducationState("1");  // 待审核
        return save(chainEducationInfo);
    }

    /**
     * 审核学历
     *
     * @param chainEducationInfo
     * @return
     */
    @Override
    public boolean examineEducation(ChainEducationInfo chainEducationInfo) {
        // 断言
        Assert.notNull(chainEducationInfo.getId(), "学历证书ID不能为空！");
        Assert.notNull(chainEducationInfo.getExamineState(), "审核状态不能为空！");
        // 判断审核状态
        if (chainEducationInfo.getExamineState().equals(ExamineStateConstants.APPROVED)) {
            // 审核通过，进入上链流程
        } else if (chainEducationInfo.getExamineState().equals(ExamineStateConstants.FAIL_TO_AUDIT)) {
            // 审核不通过，打回
        } else {
            throw new JeecgBootException("审核状态有误！");
        }
        return false;
    }
}
