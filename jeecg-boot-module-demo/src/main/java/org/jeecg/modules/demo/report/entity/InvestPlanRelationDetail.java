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
 * @Description: 投资计划关联年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
@ApiModel(value="invest_plan_relation_detail对象", description="投资计划关联年度投资计划")
@Data
@TableName("invest_plan_relation_detail")
public class InvestPlanRelationDetail implements Serializable {
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
	/**上报单位*/
	@Excel(name = "上报单位", width = 15)
    @ApiModelProperty(value = "上报单位")
    private java.lang.String reportUnitName;
	/**上报单位ID*/
	@Excel(name = "上报单位ID", width = 15)
    @ApiModelProperty(value = "上报单位ID")
    private java.lang.String reportUnitId;
	/**项目编码*/
	@Excel(name = "项目编码", width = 15)
    @ApiModelProperty(value = "项目编码")
    private java.lang.String projectCode;
	/**项目阶段*/
	@Excel(name = "项目阶段", width = 15)
    @ApiModelProperty(value = "项目阶段")
    private java.lang.String projectState;
	/**项目数量*/
	@Excel(name = "项目数量", width = 15)
    @ApiModelProperty(value = "项目数量")
    private java.lang.Integer projectCount;
	/**投资类型*/
	@Excel(name = "投资类型", width = 15)
    @ApiModelProperty(value = "投资类型")
    private java.lang.String investmentType;
	/**年度投资计划总额*/
	@Excel(name = "年度投资计划总额", width = 15)
    @ApiModelProperty(value = "年度投资计划总额")
    private java.math.BigDecimal estimatesInvestmentVolume;
	/**版本号*/
	@Excel(name = "版本号", width = 15)
    @ApiModelProperty(value = "版本号")
    private java.lang.String planVersionNo;
	/**编制状态*/
	@Excel(name = "编制状态", width = 15)
    @ApiModelProperty(value = "编制状态")
    private java.lang.String prereCodeStatus;
	/**计划提交人*/
	@Excel(name = "计划提交人", width = 15)
    @ApiModelProperty(value = "计划提交人")
    private java.lang.String planSubmitterName;
	/**计划提交时间*/
	@Excel(name = "计划提交时间", width = 15)
    @ApiModelProperty(value = "计划提交时间")
    private java.lang.String lastSubmitTime;
	/**主表id*/
    @ApiModelProperty(value = "主表id")
    private java.lang.String reportId;
}
