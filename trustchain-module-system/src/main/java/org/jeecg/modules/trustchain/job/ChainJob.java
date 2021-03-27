package org.jeecg.modules.trustchain.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.common.constant.ExamineStateConstants;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Rade
 * @Date 2021/3/27 13:42:42
 * @Description 上链审查核验定时任务
 */
public class ChainJob implements Job {
    @Autowired
    private IChainEducationInfoService chainEducationInfoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("====开始执行ChainJob=====");
        // 查询所有上链中的学历证书信息
        ChainEducationInfo chainEducationInfoSearch = new ChainEducationInfo();
        chainEducationInfoSearch.setExamineState(ExamineStateConstants.CHAINING);
        QueryWrapper<ChainEducationInfo> queryWrapper =new QueryWrapper<>(chainEducationInfoSearch);
        List<ChainEducationInfo> list = chainEducationInfoService.list(queryWrapper);
        list.forEach(System.out::println);
    }
}
