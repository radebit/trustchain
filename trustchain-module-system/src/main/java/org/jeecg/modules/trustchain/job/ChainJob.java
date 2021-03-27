package org.jeecg.modules.trustchain.job;

import cn.hutool.core.util.HexUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.constant.ExamineStateConstants;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.entity.ChainProcessRecord;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;
import org.jeecg.modules.trustchain.service.IChainProcessRecordService;
import org.jeecg.modules.trustchain.service.IChainService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author Rade
 * @Date 2021/3/27 13:42:42
 * @Description 上链审查核验定时任务
 */
public class ChainJob implements Job {
    @Autowired
    private IChainEducationInfoService chainEducationInfoService;

    @Autowired
    private IChainService chainService;

    @Autowired
    private IChainProcessRecordService chainProcessRecordService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 查询所有上链中的学历证书信息
        ChainEducationInfo chainEducationInfoSearch = new ChainEducationInfo();
        chainEducationInfoSearch.setExamineState(ExamineStateConstants.CHAINING);
        QueryWrapper<ChainEducationInfo> queryWrapper = new QueryWrapper<>(chainEducationInfoSearch);
        List<ChainEducationInfo> list = chainEducationInfoService.list(queryWrapper);
        // 遍历待处理列表，核验上链是否成功
        list.forEach(item -> {
            if (item.getChainHash() != null) {
                JSONObject findResult = chainService.findTransactionByBlockHash(item.getChainHash());
                String blockHash = findResult.getString("blockHash");
                String input = "0x" + HexUtil.encodeHexStr(item.getId());
                if (blockHash != null && input.equals(findResult.getString("input"))) {
                    // blockHash不为空，则代表转账成功
                    // 更新状态
                    ChainEducationInfo chainEducationInfoResult = chainEducationInfoService.getById(item.getId());
                    chainEducationInfoResult.setExamineState(ExamineStateConstants.CHAINED);
                    chainEducationInfoResult.setBlockHash(blockHash);
                    // 更新check时间
                    chainEducationInfoResult.setCheckTime(new Date());
                    if (!chainEducationInfoService.updateById(chainEducationInfoResult)) {
                        throw new JeecgBootException("学历ID：" + item.getId() + "更新异常！");
                    }
                    // 保存记录
                    ChainProcessRecord chainProcessRecord = new ChainProcessRecord();
                    chainProcessRecord.setEducationId(item.getId());
                    chainProcessRecord.setProcessInfo("学历证书信息已上链成功,区块地址Hash:" + blockHash);
                    chainProcessRecord.setOldState(ExamineStateConstants.CHAINING);
                    chainProcessRecord.setNewState(ExamineStateConstants.CHAINED);
                    if (!chainProcessRecordService.save(chainProcessRecord)) {
                        throw new JeecgBootException("新增流程信息出错！");
                    }
                }
            }
        });
    }
}
