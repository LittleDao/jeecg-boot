package org.jeecg.modules.demo.negative.controller;

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
import org.jeecg.modules.demo.negative.entity.NegativeList;
import org.jeecg.modules.demo.negative.service.INegativeListService;

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
 * @Description: 负面清单核对表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Api(tags="负面清单核对表")
@RestController
@RequestMapping("/negative/negativeList")
@Slf4j
public class NegativeListController extends JeecgController<NegativeList, INegativeListService> {
	@Autowired
	private INegativeListService negativeListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param negativeList
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "负面清单核对表-分页列表查询")
	@ApiOperation(value="负面清单核对表-分页列表查询", notes="负面清单核对表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<NegativeList>> queryPageList(NegativeList negativeList,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<NegativeList> queryWrapper = QueryGenerator.initQueryWrapper(negativeList, req.getParameterMap());
		Page<NegativeList> page = new Page<NegativeList>(pageNo, pageSize);
		IPage<NegativeList> pageList = negativeListService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param negativeList
	 * @return
	 */
	@AutoLog(value = "负面清单核对表-添加")
	@ApiOperation(value="负面清单核对表-添加", notes="负面清单核对表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:negative_list:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody NegativeList negativeList) {
		negativeListService.save(negativeList);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param negativeList
	 * @return
	 */
	@AutoLog(value = "负面清单核对表-编辑")
	@ApiOperation(value="负面清单核对表-编辑", notes="负面清单核对表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:negative_list:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody NegativeList negativeList) {
		negativeListService.updateById(negativeList);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "负面清单核对表-通过id删除")
	@ApiOperation(value="负面清单核对表-通过id删除", notes="负面清单核对表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:negative_list:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		negativeListService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "负面清单核对表-批量删除")
	@ApiOperation(value="负面清单核对表-批量删除", notes="负面清单核对表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:negative_list:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.negativeListService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "负面清单核对表-通过id查询")
	@ApiOperation(value="负面清单核对表-通过id查询", notes="负面清单核对表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<NegativeList> queryById(@RequestParam(name="id",required=true) String id) {
		NegativeList negativeList = negativeListService.getById(id);
		if(negativeList==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(negativeList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param negativeList
    */
    //@RequiresPermissions("org.jeecg.modules.demo:negative_list:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, NegativeList negativeList) {
        return super.exportXls(request, negativeList, NegativeList.class, "负面清单核对表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("negative_list:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, NegativeList.class);
    }

}
