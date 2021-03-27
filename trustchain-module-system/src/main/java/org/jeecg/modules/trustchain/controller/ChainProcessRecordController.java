package org.jeecg.modules.trustchain.controller;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.lang.Assert;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.enums.DeptEnum;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.entity.ChainProcessRecord;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;
import org.jeecg.modules.trustchain.service.IChainProcessRecordService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 学历审核流程记录
 * @Author: Rade
 * @Date: 2021-03-27
 * @Version: V1.0
 */
@Api(tags = "学历审核流程记录")
@RestController
@RequestMapping("/trustchain/chainProcessRecord")
@Slf4j
public class ChainProcessRecordController extends JeecgController<ChainProcessRecord, IChainProcessRecordService> {
    @Autowired
    private IChainProcessRecordService chainProcessRecordService;

    @Autowired
    private IChainEducationInfoService chainEducationInfoService;

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    /**
     * 分页列表查询
     *
     * @param chainProcessRecord
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "学历审核流程记录-分页列表查询")
    @ApiOperation(value = "学历审核流程记录-分页列表查询", notes = "学历审核流程记录-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ChainProcessRecord chainProcessRecord,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Assert.notNull(chainProcessRecord.getEducationId(), "学历证书ID不能为空！");
        // 判断权限
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ChainEducationInfo chainEducationInfo = chainEducationInfoService.getById(chainProcessRecord.getEducationId());
        if (chainEducationInfo == null) {
            return Result.error("学历证书不存在！");
        }
        if (sysBaseAPI.getDepartIdsByUsername(loginUser.getUsername()).contains(DeptEnum.STUDENT.getDeptId()) && !chainEducationInfo.getUserId().equals(loginUser.getId())) {
            return Result.error("不是您的学历证书，无权查询！");
        }
        QueryWrapper<ChainProcessRecord> queryWrapper = QueryGenerator.initQueryWrapper(chainProcessRecord, req.getParameterMap());
        Page<ChainProcessRecord> page = new Page<ChainProcessRecord>(pageNo, pageSize);
        IPage<ChainProcessRecord> pageList = chainProcessRecordService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

//    /**
//     * 通过id查询
//     *
//     * @param id
//     * @return
//     */
//    @AutoLog(value = "学历审核流程记录-通过id查询")
//    @ApiOperation(value = "学历审核流程记录-通过id查询", notes = "学历审核流程记录-通过id查询")
//    @GetMapping(value = "/queryById")
//    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
//        ChainProcessRecord chainProcessRecord = chainProcessRecordService.getById(id);
//        if (chainProcessRecord == null) {
//            return Result.error("未找到对应数据");
//        }
//        return Result.OK(chainProcessRecord);
//    }

    /**
     * 导出excel
     *
     * @param request
     * @param chainProcessRecord
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ChainProcessRecord chainProcessRecord) {
        return super.exportXls(request, chainProcessRecord, ChainProcessRecord.class, "学历审核流程记录");
    }

}
