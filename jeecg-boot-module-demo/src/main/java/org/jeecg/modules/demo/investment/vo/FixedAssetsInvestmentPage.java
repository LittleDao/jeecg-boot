package org.jeecg.modules.demo.investment.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentLog;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentSub;
import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description: 固定资产投资表
 * @Author: jeecg-boot
 * @Date:   2022-08-15
 * @Version: V1.0
 */
@Data
@ApiModel(value="fixed_assets_investmentPage对象", description="固定资产投资表")
public class FixedAssetsInvestmentPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**租户Id*/
	@Excel(name = "租户Id", width = 15)
	@ApiModelProperty(value = "租户Id")
    private Integer tenantId;
	/**项目编码*/
	@Excel(name = "项目编码", width = 15)
	@ApiModelProperty(value = "项目编码")
    private String projectCode;
	/**项目简称*/
	@Excel(name = "项目简称", width = 15)
	@ApiModelProperty(value = "项目简称")
    private String projectShortName;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
	@ApiModelProperty(value = "项目名称")
    private String projectName;
	/**项目阶段*/
	@Excel(name = "项目阶段", width = 15)
	@ApiModelProperty(value = "项目阶段")
    private Integer projectStatus;
	/**开工状态*/
	@Excel(name = "开工状态", width = 15)
	@ApiModelProperty(value = "开工状态")
    private String startStatus;
	/**预计开始时间*/
	@Excel(name = "预计开始时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "预计开始时间")
    private Date preStartTime;
	/**预计结束时间*/
	@Excel(name = "预计结束时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "预计结束时间")
    private Date preFinishTime;
	/**国家地区*/
	@Excel(name = "国家地区", width = 15)
	@ApiModelProperty(value = "国家地区")
    private String area;
	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
    private Double longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
    private Double latitude;
	/**责任人*/
	@Excel(name = "责任人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@ApiModelProperty(value = "责任人")
    private String personInCharge;
	/**计划立项时间*/
	@Excel(name = "计划立项时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "计划立项时间")
    private Date projectInititationTime;
	/**项目地址*/
	@Excel(name = "项目地址", width = 15)
	@ApiModelProperty(value = "项目地址")
    private String projectAddr;
	/**项目决策单位*/
	@Excel(name = "项目决策单位", width = 15)
	@ApiModelProperty(value = "项目决策单位")
    private String projectDecision;
	/**项目实施单位/部门*/
	@Excel(name = "项目实施单位/部门", width = 15)
	@ApiModelProperty(value = "项目实施单位/部门")
    private String projectConstruction;
	/**项目主管部门*/
	@Excel(name = "项目主管部门", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@ApiModelProperty(value = "项目主管部门")
    private String projectCharge;
	/**项目简介*/
	@Excel(name = "项目简介", width = 15)
	@ApiModelProperty(value = "项目简介")
    private String projectDescription;
	/**投资类别*/
	@Excel(name = "投资类别", width = 15)
	@ApiModelProperty(value = "投资类别")
    private Integer investmentCategory;
	/**是否对外投资 0否 1 是*/
	@Excel(name = "是否对外投资 0否 1 是", width = 15)
	@ApiModelProperty(value = "是否对外投资 0否 1 是")
    private String extProjetFlag;
	/**重点项目类型*/
	@Excel(name = "重点项目类型", width = 15, dicCode = "key_project_type")
    @Dict(dicCode = "key_project_type")
	@ApiModelProperty(value = "重点项目类型")
    private String keyProjectType;

	@ApiModelProperty(value = "项目图片")
	private String picFile;

	@ApiModelProperty(value = "已完成工作")
	private String alreadyWork;

	@ExcelCollection(name="关联信息")
	@ApiModelProperty(value = "关联信息")
	private List<FixedAssetsInvestmentSub> fixedAssetsInvestmentSubList;
	@ExcelCollection(name="固定资产投资表日志")
	@ApiModelProperty(value = "固定资产投资表日志")
	private List<FixedAssetsInvestmentLog> fixedAssetsInvestmentLogList;

	@ExcelCollection(name="固定资产投资负面清单信息")
	@ApiModelProperty(value = "固定资产投资负面清单信息")
	private List<FixedAssetsInvestmentNegative> fixedAssetsInvestmentNegativeList;

}
