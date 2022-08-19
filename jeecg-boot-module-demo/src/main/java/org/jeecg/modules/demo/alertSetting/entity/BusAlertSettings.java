package org.jeecg.modules.demo.alertSetting.entity;

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
 * @Description: 预警设置
 * @Author: jeecg-boot
 * @Date:   2022-08-15
 * @Version: V1.0
 */
@Data
@TableName("bus_alert_settings")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bus_alert_settings对象", description="预警设置")
public class BusAlertSettings implements Serializable {
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
	/**预警类型*/
	@Excel(name = "预警类型", width = 15, dicCode = "alert_type")
	@Dict(dicCode = "alert_type")
    @ApiModelProperty(value = "预警类型")
    private java.lang.Integer alertType;
	/**预警月*/
	@Excel(name = "预警月", width = 15)
    @ApiModelProperty(value = "预警月")
    private java.lang.Integer alertMonth;
	/**预警日*/
	@Excel(name = "预警日", width = 15)
    @ApiModelProperty(value = "预警日")
    private java.lang.Integer alertDay;
}
