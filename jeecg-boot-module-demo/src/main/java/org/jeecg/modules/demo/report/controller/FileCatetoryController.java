package org.jeecg.modules.demo.report.controller;

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
import org.jeecg.modules.demo.report.entity.FileCatetory;
import org.jeecg.modules.demo.report.service.IFileCatetoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
* @Description: 文件归档中间表
* @Author: jeecg-boot
* @Date:   2022-08-24
* @Version: V1.0
*/
@Api(tags="文件归档中间表")
@RestController
@RequestMapping("/catatory/fileCatetory")
@Slf4j
public class FileCatetoryController extends JeecgController<FileCatetory, IFileCatetoryService> {
   @Autowired
   private IFileCatetoryService fileCatetoryService;

   /**
    * 分页列表查询
    *
    * @param fileCatetory
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   //@AutoLog(value = "文件归档中间表-分页列表查询")
   @ApiOperation(value="文件归档中间表-分页列表查询", notes="文件归档中间表-分页列表查询")
   @GetMapping(value = "/list")
   public Result<IPage<FileCatetory>> queryPageList(FileCatetory fileCatetory,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<FileCatetory> queryWrapper = QueryGenerator.initQueryWrapper(fileCatetory, req.getParameterMap());
       Page<FileCatetory> page = new Page<FileCatetory>(pageNo, pageSize);
       IPage<FileCatetory> pageList = fileCatetoryService.page(page, queryWrapper);
       return Result.OK(pageList);
   }

   /**
    *   添加
    *
    * @param fileCatetory
    * @return
    */
   @AutoLog(value = "文件归档中间表-添加")
   @ApiOperation(value="文件归档中间表-添加", notes="文件归档中间表-添加")
   //@RequiresPermissions("org.jeecg.modules.demo:file_catetory:add")
   @PostMapping(value = "/add")
   public Result<String> add(@RequestBody FileCatetory fileCatetory) {
       fileCatetoryService.save(fileCatetory);
       return Result.OK("添加成功！");
   }

   /**
    *  编辑
    *
    * @param fileCatetory
    * @return
    */
   @AutoLog(value = "文件归档中间表-编辑")
   @ApiOperation(value="文件归档中间表-编辑", notes="文件归档中间表-编辑")
   //@RequiresPermissions("org.jeecg.modules.demo:file_catetory:edit")
   @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
   public Result<String> edit(@RequestBody FileCatetory fileCatetory) {
       fileCatetoryService.updateById(fileCatetory);
       return Result.OK("编辑成功!");
   }

   /**
    *   通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "文件归档中间表-通过id删除")
   @ApiOperation(value="文件归档中间表-通过id删除", notes="文件归档中间表-通过id删除")
   //@RequiresPermissions("org.jeecg.modules.demo:file_catetory:delete")
   @DeleteMapping(value = "/delete")
   public Result<String> delete(@RequestParam(name="id",required=true) String id) {
       fileCatetoryService.removeById(id);
       return Result.OK("删除成功!");
   }

   /**
    *  批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "文件归档中间表-批量删除")
   @ApiOperation(value="文件归档中间表-批量删除", notes="文件归档中间表-批量删除")
   //@RequiresPermissions("org.jeecg.modules.demo:file_catetory:deleteBatch")
   @DeleteMapping(value = "/deleteBatch")
   public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.fileCatetoryService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.OK("批量删除成功!");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   //@AutoLog(value = "文件归档中间表-通过id查询")
   @ApiOperation(value="文件归档中间表-通过id查询", notes="文件归档中间表-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<FileCatetory> queryById(@RequestParam(name="id",required=true) String id) {
       FileCatetory fileCatetory = fileCatetoryService.getById(id);
       if(fileCatetory==null) {
           return Result.error("未找到对应数据");
       }
       return Result.OK(fileCatetory);
   }

   /**
   * 导出excel
   *
   * @param request
   * @param fileCatetory
   */
   //@RequiresPermissions("org.jeecg.modules.demo:file_catetory:exportXls")
   @RequestMapping(value = "/exportXls")
   public ModelAndView exportXls(HttpServletRequest request, FileCatetory fileCatetory) {
       return super.exportXls(request, fileCatetory, FileCatetory.class, "文件归档中间表");
   }

   /**
     * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
   //@RequiresPermissions("file_catetory:importExcel")
   @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
   public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
       return super.importExcel(request, response, FileCatetory.class);
   }

}
