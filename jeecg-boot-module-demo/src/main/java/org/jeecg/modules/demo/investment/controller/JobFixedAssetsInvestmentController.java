package org.jeecg.modules.demo.investment.controller;

import com.xxl.job.core.biz.model.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentLogService;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentService;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description: 固定资产投资表
* @Author: jeecg-boot
* @Date:   2022-08-11
* @Version: V1.0
*/
@RestController
@RequestMapping("/investment/fixedAssetsInvestment/job")
@Slf4j
public class JobFixedAssetsInvestmentController {
   @Autowired
   private IFixedAssetsInvestmentService fixedAssetsInvestmentService;
   @Autowired
   private IFixedAssetsInvestmentSubService fixedAssetsInvestmentSubService;
   @Autowired
   private IFixedAssetsInvestmentLogService fixedAssetsInvestmentLogService;

   /**
    * 定时提醒
    */
   //@AutoLog(value = "固定资产投资表-分页列表查询")
//   @XxlJob(value = "testJob")
   @GetMapping(value = "/testJob")
   public ReturnT<String> testJob() {
       //1 查询需要处理的数据，逐个处理，并且将数据的处理状态
       System.out.println("XX-Job处理任务");
       return ReturnT.SUCCESS;
   }

}
