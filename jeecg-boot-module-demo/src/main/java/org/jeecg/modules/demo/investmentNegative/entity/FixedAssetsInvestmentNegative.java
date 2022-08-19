package org.jeecg.modules.demo.investmentNegative.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 项目负面清单列表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Data
@TableName("fixed_assets_investment_negative")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="fixed_assets_investment_negative对象", description="项目负面清单列表")
public class FixedAssetsInvestmentNegative implements Serializable {
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
	/**主表主键*/
	@Excel(name = "主表主键", width = 15)
    @ApiModelProperty(value = "主表主键")
    private java.lang.String investmentId;
	/**负面清单编码*/
	@Excel(name = "负面清单编码", width = 15)
    @ApiModelProperty(value = "负面清单编码")
    private java.lang.String negativeCode;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**境内境外*/
	@Excel(name = "境内境外", width = 8)
    @ApiModelProperty(value = "境内境外")
    private java.lang.Integer inOutArea;
	/**类型*/
	@Excel(name = "类型", width = 8)
    @ApiModelProperty(value = "类型")
    private java.lang.Integer negativeType;

    /**类型*/
    @ApiModelProperty(value = "检查标记")
    private java.lang.Integer checkFlag;
}
