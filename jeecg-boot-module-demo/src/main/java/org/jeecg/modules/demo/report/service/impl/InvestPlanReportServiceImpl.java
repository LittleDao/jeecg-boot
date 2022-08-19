package org.jeecg.modules.demo.report.service.impl;

import org.jeecg.modules.demo.report.entity.InvestPlanReport;
import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import org.jeecg.modules.demo.report.mapper.InvestPlanRelationDetailMapper;
import org.jeecg.modules.demo.report.mapper.InvestPlanSubRelationDetailMapper;
import org.jeecg.modules.demo.report.mapper.InvestPlanReportMapper;
import org.jeecg.modules.demo.report.service.IInvestPlanReportService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 投资计划上报
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
@Service
public class InvestPlanReportServiceImpl extends ServiceImpl<InvestPlanReportMapper, InvestPlanReport> implements IInvestPlanReportService {

	@Autowired
	private InvestPlanReportMapper investPlanReportMapper;
	@Autowired
	private InvestPlanRelationDetailMapper investPlanRelationDetailMapper;
	@Autowired
	private InvestPlanSubRelationDetailMapper investPlanSubRelationDetailMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(InvestPlanReport investPlanReport, List<InvestPlanRelationDetail> investPlanRelationDetailList,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList) {
		investPlanReportMapper.insert(investPlanReport);
		if(investPlanRelationDetailList!=null && investPlanRelationDetailList.size()>0) {
			for(InvestPlanRelationDetail entity:investPlanRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanRelationDetailMapper.insert(entity);
			}
		}
		if(investPlanSubRelationDetailList!=null && investPlanSubRelationDetailList.size()>0) {
			for(InvestPlanSubRelationDetail entity:investPlanSubRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanSubRelationDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(InvestPlanReport investPlanReport,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList) {
		investPlanReportMapper.updateById(investPlanReport);
		
		//1.先删除子表数据
		investPlanRelationDetailMapper.deleteByMainId(investPlanReport.getId());
		investPlanSubRelationDetailMapper.deleteByMainId(investPlanReport.getId());
		
		//2.子表数据重新插入
		if(investPlanRelationDetailList!=null && investPlanRelationDetailList.size()>0) {
			for(InvestPlanRelationDetail entity:investPlanRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanRelationDetailMapper.insert(entity);
			}
		}
		if(investPlanSubRelationDetailList!=null && investPlanSubRelationDetailList.size()>0) {
			for(InvestPlanSubRelationDetail entity:investPlanSubRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanSubRelationDetailMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		investPlanRelationDetailMapper.deleteByMainId(id);
		investPlanSubRelationDetailMapper.deleteByMainId(id);
		investPlanReportMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			investPlanRelationDetailMapper.deleteByMainId(id.toString());
			investPlanSubRelationDetailMapper.deleteByMainId(id.toString());
			investPlanReportMapper.deleteById(id);
		}
	}
	
}
