package org.jeecg.modules.demo.alertSetting.controller;

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
import org.jeecg.modules.demo.alertSetting.entity.BusAlertSettings;
import org.jeecg.modules.demo.alertSetting.service.IBusAlertSettingsService;

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
 * @Description: 预警设置
 * @Author: jeecg-boot
 * @Date:   2022-08-15
 * @Version: V1.0
 */
@Api(tags="预警设置")
@RestController
@RequestMapping("/alertSetting/busAlertSettings")
@Slf4j
public class BusAlertSettingsController extends JeecgController<BusAlertSettings, IBusAlertSettingsService> {
	@Autowired
	private IBusAlertSettingsService busAlertSettingsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param busAlertSettings
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "预警设置-分页列表查询")
	@ApiOperation(value="预警设置-分页列表查询", notes="预警设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BusAlertSettings>> queryPageList(BusAlertSettings busAlertSettings,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BusAlertSettings> queryWrapper = QueryGenerator.initQueryWrapper(busAlertSettings, req.getParameterMap());
		Page<BusAlertSettings> page = new Page<BusAlertSettings>(pageNo, pageSize);
		IPage<BusAlertSettings> pageList = busAlertSettingsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param busAlertSettings
	 * @return
	 */
	@AutoLog(value = "预警设置-添加")
	@ApiOperation(value="预警设置-添加", notes="预警设置-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:bus_alert_settings:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BusAlertSettings busAlertSettings) {
		busAlertSettingsService.save(busAlertSettings);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param busAlertSettings
	 * @return
	 */
	@AutoLog(value = "预警设置-编辑")
	@ApiOperation(value="预警设置-编辑", notes="预警设置-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:bus_alert_settings:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BusAlertSettings busAlertSettings) {
		busAlertSettingsService.updateById(busAlertSettings);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "预警设置-通过id删除")
	@ApiOperation(value="预警设置-通过id删除", notes="预警设置-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:bus_alert_settings:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		busAlertSettingsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "预警设置-批量删除")
	@ApiOperation(value="预警设置-批量删除", notes="预警设置-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:bus_alert_settings:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.busAlertSettingsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "预警设置-通过id查询")
	@ApiOperation(value="预警设置-通过id查询", notes="预警设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BusAlertSettings> queryById(@RequestParam(name="id",required=true) String id) {
		BusAlertSettings busAlertSettings = busAlertSettingsService.getById(id);
		if(busAlertSettings==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(busAlertSettings);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param busAlertSettings
    */
    //@RequiresPermissions("org.jeecg.modules.demo:bus_alert_settings:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BusAlertSettings busAlertSettings) {
        return super.exportXls(request, busAlertSettings, BusAlertSettings.class, "预警设置");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("bus_alert_settings:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BusAlertSettings.class);
    }

}
