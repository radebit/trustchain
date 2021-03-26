package org.jeecg.modules.trustchain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Author Rade
 * @Date 2021/3/26 16:23:23
 * @Description
 */
@Data
public class ChainEducationInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**
     * 所属学校
     */
    @Dict(dictTable = "chain_school_info", dicText = "school_name", dicCode = "id")
    @ApiModelProperty(value = "所属学校", required = true)
    private java.lang.String schoolId;
    /**
     * 层次
     */
    @Dict(dicCode = "chain_education_level")
    @ApiModelProperty(value = "层次", required = true)
    private java.lang.String educationLevel;
    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", required = true)
    private java.lang.String major;
    /**
     * 学制
     */
    @ApiModelProperty(value = "学制", required = true)
    private java.lang.String educationYear;
    /**
     * 学历类别
     */
    @Dict(dicCode = "education_type")
    @ApiModelProperty(value = "学历类别", required = true)
    private java.lang.String educationType;
    /**
     * 学习形式
     */
    @Dict(dicCode = "chain_learn_type")
    @ApiModelProperty(value = "学习形式", required = true)
    private java.lang.String learnType;
    /**
     * 学号
     */
    @ApiModelProperty(value = "学号", required = true)
    private java.lang.String schoolNumber;
    /**
     * 班级
     */
    @ApiModelProperty(value = "班级", required = true)
    private java.lang.String classNumber;
    /**
     * 入学时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "入学时间", required = true)
    private java.util.Date admissionDate;
    /**
     * 毕业时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "毕业时间", required = true)
    private java.util.Date graduationDate;
    /**
     * 学籍状态
     */
    @Dict(dicCode = "education_state")
    @ApiModelProperty(value = "学籍状态", required = true)
    private java.lang.String educationState;
    /**
     * 审核状态
     */
    @Dict(dicCode = "examine_state")
    @ApiModelProperty(value = "审核状态")
    private java.lang.String examineState;
    /**
     * 流程信息
     */
    @ApiModelProperty(value = "流程信息")
    private java.lang.String processInfo;
}
