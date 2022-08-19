package org.jeecg.modules.demo.report.service;

import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import org.jeecg.modules.demo.report.entity.InvestPlanReport;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 投资计划上报
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
public interface IInvestPlanReportService extends IService<InvestPlanReport> {

	/**
	 * 添加一对多
	 *
	 * @param investPlanReport
	 * @param investPlanRelationDetailList
	 * @param investPlanSubRelationDetailList
	 */
	public void saveMain(InvestPlanReport investPlanReport,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param investPlanReport
	 * @param investPlanRelationDetailList
	 * @param investPlanSubRelationDetailList
	 */
	public void updateMain(InvestPlanReport investPlanReport,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
