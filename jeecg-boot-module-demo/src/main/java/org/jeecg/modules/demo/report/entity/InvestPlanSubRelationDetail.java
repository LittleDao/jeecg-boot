package org.jeecg.modules.demo.report.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 关联下级年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
@ApiModel(value="invest_plan_sub_relation_detail对象", description="关联下级年度投资计划")
@Data
@TableName("invest_plan_sub_relation_detail")
public class InvestPlanSubRelationDetail implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**审批状态*/
	@Excel(name = "审批状态", width = 15)
    @ApiModelProperty(value = "审批状态")
    private java.lang.String state;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private java.lang.String code;
	/**上报单位*/
	@Excel(name = "上报单位", width = 15)
    @ApiModelProperty(value = "上报单位")
    private java.lang.String reportUnitName;
	/**计划总投资*/
	@Excel(name = "计划总投资", width = 15)
    @ApiModelProperty(value = "计划总投资")
    private java.math.BigDecimal planTotalAmount;
	/**项目数*/
	@Excel(name = "项目数", width = 15)
    @ApiModelProperty(value = "项目数")
    private java.lang.Integer projectCount;
	/**年度投资计划总额*/
	@Excel(name = "年度投资计划总额", width = 15)
    @ApiModelProperty(value = "年度投资计划总额")
    private java.math.BigDecimal yearPlanAmount;
	/**上报时间*/
	@Excel(name = "上报时间", width = 15)
    @ApiModelProperty(value = "上报时间")
    private java.lang.String reportTime;
	/**主表id*/
    @ApiModelProperty(value = "主表id")
    private java.lang.String reportId;
	/**计划id*/
	@Excel(name = "计划id", width = 15)
    @ApiModelProperty(value = "计划id")
    private java.lang.String planId;
}
