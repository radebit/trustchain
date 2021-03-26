package org.jeecg.modules.trustchain.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.trustchain.entity.ChainEducationInfo;
import org.jeecg.modules.trustchain.service.IChainEducationInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 学历信息
 * @Author: Rade
 * @Date: 2021-03-26
 * @Version: V1.0
 */
@Api(tags = "学历信息")
@RestController
@RequestMapping("/trustchain/chainEducationInfo")
@Slf4j
public class ChainEducationInfoController extends JeecgController<ChainEducationInfo, IChainEducationInfoService> {
    @Autowired
    private IChainEducationInfoService chainEducationInfoService;

    /**
     * 分页列表查询
     *
     * @param chainEducationInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "学历信息-分页列表查询")
    @ApiOperation(value = "学历信息-分页列表查询", notes = "学历信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ChainEducationInfo chainEducationInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ChainEducationInfo> queryWrapper = QueryGenerator.initQueryWrapper(chainEducationInfo, req.getParameterMap());
        Page<ChainEducationInfo> page = new Page<ChainEducationInfo>(pageNo, pageSize);
        IPage<ChainEducationInfo> pageList = chainEducationInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param chainEducationInfo
     * @return
     */
    @AutoLog(value = "学历信息-添加")
    @ApiOperation(value = "学历信息-添加", notes = "学历信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ChainEducationInfo chainEducationInfo) {
        chainEducationInfoService.save(chainEducationInfo);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param chainEducationInfo
     * @return
     */
    @AutoLog(value = "学历信息-编辑")
    @ApiOperation(value = "学历信息-编辑", notes = "学历信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ChainEducationInfo chainEducationInfo) {
        chainEducationInfoService.updateById(chainEducationInfo);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "学历信息-通过id删除")
    @ApiOperation(value = "学历信息-通过id删除", notes = "学历信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        chainEducationInfoService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "学历信息-批量删除")
    @ApiOperation(value = "学历信息-批量删除", notes = "学历信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.chainEducationInfoService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "学历信息-通过id查询")
    @ApiOperation(value = "学历信息-通过id查询", notes = "学历信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ChainEducationInfo chainEducationInfo = chainEducationInfoService.getById(id);
        if (chainEducationInfo == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(chainEducationInfo);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param chainEducationInfo
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ChainEducationInfo chainEducationInfo) {
        return super.exportXls(request, chainEducationInfo, ChainEducationInfo.class, "学历信息");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ChainEducationInfo.class);
    }

    /**
     * 学生申请学历认证
     *
     * @param chainEducationInfo
     * @return
     */
    @AutoLog(value = "学历信息-申请")
    @ApiOperation(value = "学历信息-申请", notes = "学历信息-申请")
    @PostMapping(value = "/applyEducation")
    public Result<?> applyEducation(@RequestBody ChainEducationInfo chainEducationInfo) {
        chainEducationInfoService.applyEducation(chainEducationInfo);
        return Result.OK("申请成功！请耐心等待系统审核！");
    }

}
