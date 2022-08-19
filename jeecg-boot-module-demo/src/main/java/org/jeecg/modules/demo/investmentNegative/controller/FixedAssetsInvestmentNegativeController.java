package org.jeecg.modules.demo.investmentNegative.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;
import org.jeecg.modules.demo.investmentNegative.service.IFixedAssetsInvestmentNegativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 项目负面清单列表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Api(tags="项目负面清单列表")
@RestController
@RequestMapping("/negative/fixedAssetsInvestmentNegative")
@Slf4j
public class FixedAssetsInvestmentNegativeController extends JeecgController<FixedAssetsInvestmentNegative, IFixedAssetsInvestmentNegativeService> {
	@Autowired
	private IFixedAssetsInvestmentNegativeService fixedAssetsInvestmentNegativeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param fixedAssetsInvestmentNegative
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "项目负面清单列表-分页列表查询")
	@ApiOperation(value="项目负面清单列表-分页列表查询", notes="项目负面清单列表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<FixedAssetsInvestmentNegative>> queryPageList(FixedAssetsInvestmentNegative fixedAssetsInvestmentNegative,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<FixedAssetsInvestmentNegative> queryWrapper = QueryGenerator.initQueryWrapper(fixedAssetsInvestmentNegative, req.getParameterMap());
		Page<FixedAssetsInvestmentNegative> page = new Page<FixedAssetsInvestmentNegative>(pageNo, pageSize);
		IPage<FixedAssetsInvestmentNegative> pageList = fixedAssetsInvestmentNegativeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param fixedAssetsInvestmentNegative
	 * @return
	 */
	@AutoLog(value = "项目负面清单列表-添加")
	@ApiOperation(value="项目负面清单列表-添加", notes="项目负面清单列表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:fixed_assets_investment_negative:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody FixedAssetsInvestmentNegative fixedAssetsInvestmentNegative) {
		fixedAssetsInvestmentNegativeService.save(fixedAssetsInvestmentNegative);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param fixedAssetsInvestmentNegative
	 * @return
	 */
	@AutoLog(value = "项目负面清单列表-编辑")
	@ApiOperation(value="项目负面清单列表-编辑", notes="项目负面清单列表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:fixed_assets_investment_negative:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody FixedAssetsInvestmentNegative fixedAssetsInvestmentNegative) {
		fixedAssetsInvestmentNegativeService.updateById(fixedAssetsInvestmentNegative);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "项目负面清单列表-通过id删除")
	@ApiOperation(value="项目负面清单列表-通过id删除", notes="项目负面清单列表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:fixed_assets_investment_negative:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		fixedAssetsInvestmentNegativeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "项目负面清单列表-批量删除")
	@ApiOperation(value="项目负面清单列表-批量删除", notes="项目负面清单列表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:fixed_assets_investment_negative:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.fixedAssetsInvestmentNegativeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "项目负面清单列表-通过id查询")
	@ApiOperation(value="项目负面清单列表-通过id查询", notes="项目负面清单列表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<FixedAssetsInvestmentNegative> queryById(@RequestParam(name="id",required=true) String id) {
		FixedAssetsInvestmentNegative fixedAssetsInvestmentNegative = fixedAssetsInvestmentNegativeService.getById(id);
		if(fixedAssetsInvestmentNegative==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(fixedAssetsInvestmentNegative);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param fixedAssetsInvestmentNegative
    */
    //@RequiresPermissions("org.jeecg.modules.demo:fixed_assets_investment_negative:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, FixedAssetsInvestmentNegative fixedAssetsInvestmentNegative) {
        return super.exportXls(request, fixedAssetsInvestmentNegative, FixedAssetsInvestmentNegative.class, "项目负面清单列表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("fixed_assets_investment_negative:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, FixedAssetsInvestmentNegative.class);
    }

	 /**
	  *  批量确认负面清单
	  *
	  * @param ids
	  * @return
	  */
	 @AutoLog(value = "项目负面清单列表-批量检查")
	 @ApiOperation(value="项目负面清单列表-批量检查", notes="项目负面清单列表-批量检查")
	 @DeleteMapping(value = "/checkBatch")
	 public Result<String> checkBatch(@RequestParam(name="ids",required=true) String ids) {
		 this.fixedAssetsInvestmentNegativeService.removeByIds(Arrays.asList(ids.split(",")));
		 return Result.OK("批量删除成功!");
	 }

}
