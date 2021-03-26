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
 * @Description: 学历审核流程记录
 * @Author: Rade
 * @Date:   2021-03-26
 * @Version: V1.0
 */
@Data
@TableName("chain_process_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="chain_process_record对象", description="学历审核流程记录")
public class ChainProcessRecord implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**所属学历ID*/
	@Excel(name = "所属学历ID", width = 15)
    @ApiModelProperty(value = "所属学历ID")
    private java.lang.String educationId;
	/**流程信息*/
	@Excel(name = "流程信息", width = 15)
    @ApiModelProperty(value = "流程信息")
    private java.lang.String processInfo;
	/**原始状态*/
	@Excel(name = "原始状态", width = 15, dicCode = "examine_state")
	@Dict(dicCode = "examine_state")
    @ApiModelProperty(value = "原始状态")
    private java.lang.String oldState;
	/**新状态*/
	@Excel(name = "新状态", width = 15, dicCode = "examine_state")
	@Dict(dicCode = "examine_state")
    @ApiModelProperty(value = "新状态")
    private java.lang.String newState;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
}
