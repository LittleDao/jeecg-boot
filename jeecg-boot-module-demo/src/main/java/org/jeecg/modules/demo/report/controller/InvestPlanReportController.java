package org.jeecg.modules.demo.report.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.dto.message.BusMessageDTO;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.SysAnnmentTypeEnum;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestment;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentService;
import org.jeecg.modules.demo.report.entity.FileCatetory;
import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import org.jeecg.modules.demo.report.entity.FileList;
import org.jeecg.modules.demo.report.entity.InvestPlanReport;
import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import org.jeecg.modules.demo.report.service.IFileListService;
import org.jeecg.modules.demo.report.service.IInvestPlanRelationDetailService;
import org.jeecg.modules.demo.report.service.IInvestPlanReportService;
import org.jeecg.modules.demo.report.service.IInvestPlanSubRelationDetailService;
import org.jeecg.modules.demo.report.vo.InvestPlanReportPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 投资计划上报
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
@Api(tags="投资计划上报")
@RestController
@RequestMapping("/report/investPlanReport")
@Slf4j
public class InvestPlanReportController {
	@Autowired
	private IInvestPlanReportService investPlanReportService;
	@Autowired
	private IInvestPlanRelationDetailService investPlanRelationDetailService;
	@Autowired
	private IInvestPlanSubRelationDetailService investPlanSubRelationDetailService;
	 @Autowired
	 private IFileListService fileListService;
	
	/**
	 * 分页列表查询
	 *
	 * @param investPlanReport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "投资计划上报-分页列表查询")
	@ApiOperation(value="投资计划上报-分页列表查询", notes="投资计划上报-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<InvestPlanReport>> queryPageList(InvestPlanReport investPlanReport,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InvestPlanReport> queryWrapper = QueryGenerator.initQueryWrapper(investPlanReport, req.getParameterMap());
		Page<InvestPlanReport> page = new Page<InvestPlanReport>(pageNo, pageSize);
		IPage<InvestPlanReport> pageList = investPlanReportService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param investPlanReportPage
	 * @return
	 */
	@AutoLog(value = "投资计划上报-添加")
	@ApiOperation(value="投资计划上报-添加", notes="投资计划上报-添加")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody InvestPlanReportPage investPlanReportPage) {
		InvestPlanReport investPlanReport = new InvestPlanReport();
		BeanUtils.copyProperties(investPlanReportPage, investPlanReport);
		investPlanReportService.saveMain(investPlanReport, investPlanReportPage.getInvestPlanSubRelationDetailList(),investPlanReportPage.getInvestPlanRelationDetailList(),investPlanReportPage.getFileListList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param investPlanReportPage
	 * @return
	 */
	@AutoLog(value = "投资计划上报-编辑")
	@ApiOperation(value="投资计划上报-编辑", notes="投资计划上报-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody InvestPlanReportPage investPlanReportPage) {
		InvestPlanReport investPlanReport = new InvestPlanReport();
		BeanUtils.copyProperties(investPlanReportPage, investPlanReport);
		InvestPlanReport investPlanReportEntity = investPlanReportService.getById(investPlanReport.getId());
		if(investPlanReportEntity==null) {
			return Result.error("未找到对应数据");
		}
		investPlanReportService.updateMain(investPlanReport, investPlanReportPage.getInvestPlanSubRelationDetailList(),investPlanReportPage.getInvestPlanRelationDetailList(),investPlanReportPage.getFileListList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "投资计划上报-通过id删除")
	@ApiOperation(value="投资计划上报-通过id删除", notes="投资计划上报-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		investPlanReportService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "投资计划上报-批量删除")
	@ApiOperation(value="投资计划上报-批量删除", notes="投资计划上报-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.investPlanReportService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "投资计划上报-通过id查询")
	@ApiOperation(value="投资计划上报-通过id查询", notes="投资计划上报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<InvestPlanReport> queryById(@RequestParam(name="id",required=true) String id) {
		InvestPlanReport investPlanReport = investPlanReportService.getById(id);
		if(investPlanReport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(investPlanReport);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "投资计划关联年度投资计划通过主表ID查询")
	@ApiOperation(value="投资计划关联年度投资计划主表ID查询", notes="投资计划关联年度投资计划-通主表ID查询")
	@GetMapping(value = "/queryInvestPlanRelationDetailByMainId")
	public Result<List<InvestPlanRelationDetail>> queryInvestPlanRelationDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<InvestPlanRelationDetail> investPlanRelationDetailList = investPlanRelationDetailService.selectByMainId(id);
		return Result.OK(investPlanRelationDetailList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "关联下级年度投资计划通过主表ID查询")
	@ApiOperation(value="关联下级年度投资计划主表ID查询", notes="关联下级年度投资计划-通主表ID查询")
	@GetMapping(value = "/queryInvestPlanSubRelationDetailByMainId")
	public Result<List<InvestPlanSubRelationDetail>> queryInvestPlanSubRelationDetailListByMainId(@RequestParam(name="id",required=true) String id) {
		List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList = investPlanSubRelationDetailService.selectByMainId(id);
		return Result.OK(investPlanSubRelationDetailList);
	}
	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 //@AutoLog(value = "附件列表通过主表ID查询")
	 @ApiOperation(value="附件列表主表ID查询", notes="附件列表-通主表ID查询")
	 @GetMapping(value = "/queryFileListByMainId")
	 public Result<List<FileList>> queryFileListListByMainId(@RequestParam(name="id",required=true) String id) {
		 List<FileList> fileListList = fileListService.selectByMainId(id);
		 return Result.OK(fileListList);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param investPlanReport
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InvestPlanReport investPlanReport) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<InvestPlanReport> queryWrapper = QueryGenerator.initQueryWrapper(investPlanReport, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
       String selections = request.getParameter("selections");
       if(oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id",selectionList);
       }
       //Step.2 获取导出数据
       List<InvestPlanReport> investPlanReportList = investPlanReportService.list(queryWrapper);

      // Step.3 组装pageList
      List<InvestPlanReportPage> pageList = new ArrayList<InvestPlanReportPage>();
      for (InvestPlanReport main : investPlanReportList) {
          InvestPlanReportPage vo = new InvestPlanReportPage();
          BeanUtils.copyProperties(main, vo);
          List<InvestPlanRelationDetail> investPlanRelationDetailList = investPlanRelationDetailService.selectByMainId(main.getId());
          vo.setInvestPlanRelationDetailList(investPlanRelationDetailList);
          List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList = investPlanSubRelationDetailService.selectByMainId(main.getId());
          vo.setInvestPlanSubRelationDetailList(investPlanSubRelationDetailList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "投资计划上报列表");
      mv.addObject(NormalExcelConstants.CLASS, InvestPlanReportPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("投资计划上报数据", "导出人:"+sysUser.getRealname(), "投资计划上报"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<InvestPlanReportPage> list = ExcelImportUtil.importExcel(file.getInputStream(), InvestPlanReportPage.class, params);
              for (InvestPlanReportPage page : list) {
                  InvestPlanReport po = new InvestPlanReport();
                  BeanUtils.copyProperties(page, po);
				  investPlanReportService.saveMain(po, page.getInvestPlanSubRelationDetailList(),page.getInvestPlanRelationDetailList(),page.getFileListList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }
	@Resource
	 IFixedAssetsInvestmentService fixedAssetsInvestmentService;
	 @Resource
	 private ISysBaseAPI sysBaseAPI;

	 /**
	  * 一键通知
	  *
	  * @param projectIds
	  * @return
	  */
	 //@AutoLog(value = "关联下级年度投资计划通过主表ID查询")
	 @ApiOperation(value="一键通知", notes="一键通知")
	 @PostMapping(value = "/noticeAll")
	 public Result<String> noticeAll(@RequestParam("projectIds") String projectIds) {
//		 investPlanReportService.delMain(reportId);
		 if(StringUtils.isNotEmpty(projectIds)){
			 String[] split = projectIds.split(",");
			 for (String s : split) {
				 FixedAssetsInvestment investment = fixedAssetsInvestmentService.getById(s);
				 if(ObjectUtils.isNotEmpty(investment)){
					 String personInCharge = investment.getPersonInCharge();
					 //发送消息，设置为工作流消息
					 BusMessageDTO busMessageDTO = new BusMessageDTO("system", personInCharge, "一键通知", "投资计划！", "1", SysAnnmentTypeEnum.INVESTMENT.getType(), investment.getId());
					 sysBaseAPI.sendBusAnnouncement( busMessageDTO);
				 }
			 }
		 }
		 return Result.OK();
	 }


	 /**
	  * 归档功能
	  *
	  * @param fileIds
	  * @param cateId
	  * @return
	  */
	 //@AutoLog(value = "附件列表通过主表ID查询")
	 @ApiOperation(value="归档功能", notes="归档功能")
	 @GetMapping(value = "/placeToFile")
	 public Result<String> placeToFile(@RequestParam(name="fileIds",required=true) String fileIds, @RequestParam(name="cateId",required=true) String cateId) {

		 if(StringUtils.isNotEmpty(fileIds) && StringUtils.isNotEmpty(cateId)){
			 String[] fileIdsSplit = fileIds.split(",");
			 List<FileCatetory> list = new ArrayList<>();
			 //新建归档记录
			 for(String s : fileIdsSplit){
				 FileCatetory fileCatetory = new FileCatetory();
				 fileCatetory.setFileName(s);
				 fileCatetory.setCateId(cateId);
				 list.add(fileCatetory);
			 }
			 investPlanReportService.savePlaceFile(list);
		 }

		 return Result.OK();
	 }

}
