package org.jeecg.modules.trustchain.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 学历信息
 * @Author: Rade
 * @Date: 2021-03-26
 * @Version: V1.0
 */
@Data
@TableName("chain_education_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "chain_education_info对象", description = "学历信息")
public class ChainEducationInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**
     * 哈希值
     */
    @Excel(name = "哈希值", width = 15)
    @ApiModelProperty(value = "哈希值")
    private java.lang.String chainHash;
    /**
     * 用户
     */
    @Excel(name = "用户", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @ApiModelProperty(value = "用户")
    private java.lang.String userId;
    /**
     * 用户名
     */
    @Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private java.lang.String userName;
    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名", width = 15)
    @ApiModelProperty(value = "真实姓名")
    private java.lang.String realName;
    /**
     * 性别
     */
    @Excel(name = "性别", width = 15, dicCode = "sex")
    @Dict(dicCode = "sex")
    @ApiModelProperty(value = "性别")
    private java.lang.String sex;
    /**
     * 出生日期
     */
    @Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private java.util.Date birthDate;
    /**
     * 民族
     */
    @Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private java.lang.String nation;
    /**
     * 证件号码
     */
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private java.lang.String idNumber;
    /**
     * 所属学校
     */
    @Excel(name = "所属学校", width = 15, dictTable = "chain_school_info", dicText = "school_name", dicCode = "id")
    @Dict(dictTable = "chain_school_info", dicText = "school_name", dicCode = "id")
    @ApiModelProperty(value = "所属学校", required = true)
    private java.lang.String schoolId;
    /**
     * 层次
     */
    @Excel(name = "层次", width = 15, dicCode = "chain_education_level")
    @Dict(dicCode = "chain_education_level")
    @ApiModelProperty(value = "层次", required = true)
    private java.lang.String educationLevel;
    /**
     * 专业
     */
    @Excel(name = "专业", width = 15)
    @ApiModelProperty(value = "专业", required = true)
    private java.lang.String major;
    /**
     * 学制
     */
    @Excel(name = "学制", width = 15)
    @ApiModelProperty(value = "学制", required = true)
    private java.lang.String educationYear;
    /**
     * 学历类别
     */
    @Excel(name = "学历类别", width = 15, dicCode = "education_type")
    @Dict(dicCode = "education_type")
    @ApiModelProperty(value = "学历类别", required = true)
    private java.lang.String educationType;
    /**
     * 学习形式
     */
    @Excel(name = "学习形式", width = 15, dicCode = "chain_learn_type")
    @Dict(dicCode = "chain_learn_type")
    @ApiModelProperty(value = "学习形式", required = true)
    private java.lang.String learnType;
    /**
     * 学号
     */
    @Excel(name = "学号", width = 15)
    @ApiModelProperty(value = "学号", required = true)
    private java.lang.String schoolNumber;
    /**
     * 班级
     */
    @Excel(name = "班级", width = 15)
    @ApiModelProperty(value = "班级", required = true)
    private java.lang.String classNumber;
    /**
     * 入学时间
     */
    @Excel(name = "入学时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "入学时间", required = true)
    private java.util.Date admissionDate;
    /**
     * 毕业时间
     */
    @Excel(name = "毕业时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "毕业时间", required = true)
    private java.util.Date graduationDate;
    /**
     * 学籍状态
     */
    @Excel(name = "学籍状态", width = 15, dicCode = "education_state")
    @Dict(dicCode = "education_state")
    @ApiModelProperty(value = "学籍状态", required = true)
    private java.lang.String educationState;
    /**
     * 审核状态
     */
    @Excel(name = "审核状态", width = 15, dicCode = "examine_state")
    @Dict(dicCode = "examine_state")
    @ApiModelProperty(value = "审核状态")
    private java.lang.String examineState;
    /**
     * 核验时间
     */
    @Excel(name = "核验时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "核验时间")
    private java.util.Date checkTime;
}
