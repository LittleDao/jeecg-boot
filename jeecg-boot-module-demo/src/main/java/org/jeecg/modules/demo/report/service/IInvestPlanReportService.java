package org.jeecg.modules.demo.report.service;

import org.jeecg.modules.demo.report.entity.FileCatetory;
import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import org.jeecg.modules.demo.report.entity.FileList;
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
	 * @param investPlanSubRelationDetailList
	 * @param investPlanRelationDetailList
	 * @param fileListList
	 */
	public void saveMain(InvestPlanReport investPlanReport,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<FileList> fileListList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param investPlanReport
	 * @param investPlanSubRelationDetailList
	 * @param investPlanRelationDetailList
	 * @param fileListList
	 */
	public void updateMain(InvestPlanReport investPlanReport,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<FileList> fileListList);
	
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

	/**
	 * 归档功能
	 *
	 * @param investPlanSubRelationDetailList
	 */
	public void savePlaceFile(List<FileCatetory> investPlanSubRelationDetailList);
	
}
