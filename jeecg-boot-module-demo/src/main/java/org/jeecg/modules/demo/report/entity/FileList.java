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
 * @Description: 附件列表
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@ApiModel(value="file_list对象", description="附件列表")
@Data
@TableName("file_list")
public class FileList implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**主表id*/
    @ApiModelProperty(value = "主表id")
    private String mainId;
	/**文件上传*/
	@Excel(name = "文件上传", width = 15)
    @ApiModelProperty(value = "文件上传")
    private String fileUp;
	/**归档地址id*/
	@Excel(name = "归档地址id", width = 15, dicCode = "B03")
    @ApiModelProperty(value = "归档地址id")
    private String categroyId;
	/**归档地址名称*/
	@Excel(name = "归档地址名称", width = 15)
    @ApiModelProperty(value = "归档地址名称")
    private String categroyName;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
    private String filePath;
	/**文件名称*/
	@Excel(name = "文件名称", width = 15)
    @ApiModelProperty(value = "文件名称")
    private String fileName;
}
