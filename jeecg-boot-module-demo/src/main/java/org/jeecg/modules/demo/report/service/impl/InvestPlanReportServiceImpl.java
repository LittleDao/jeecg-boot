package org.jeecg.modules.demo.report.service.impl;


import org.jeecg.modules.demo.report.entity.FileCatetory;
import org.jeecg.modules.demo.report.entity.InvestPlanReport;
import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import org.jeecg.modules.demo.report.entity.FileList;
import org.jeecg.modules.demo.report.mapper.FileCatetoryMapper;
import org.jeecg.modules.demo.report.mapper.InvestPlanSubRelationDetailMapper;
import org.jeecg.modules.demo.report.mapper.InvestPlanRelationDetailMapper;
import org.jeecg.modules.demo.report.mapper.FileListMapper;
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
	private InvestPlanSubRelationDetailMapper investPlanSubRelationDetailMapper;
	@Autowired
	private InvestPlanRelationDetailMapper investPlanRelationDetailMapper;
	@Autowired
	private FileListMapper fileListMapper;
	@Autowired
	private FileCatetoryMapper fileCatetoryMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(InvestPlanReport investPlanReport, List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<FileList> fileListList) {
		investPlanReportMapper.insert(investPlanReport);
		if(investPlanSubRelationDetailList!=null && investPlanSubRelationDetailList.size()>0) {
			for(InvestPlanSubRelationDetail entity:investPlanSubRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanSubRelationDetailMapper.insert(entity);
			}
		}
		if(investPlanRelationDetailList!=null && investPlanRelationDetailList.size()>0) {
			for(InvestPlanRelationDetail entity:investPlanRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanRelationDetailMapper.insert(entity);
			}
		}
		if(fileListList!=null && fileListList.size()>0) {
			for(FileList entity:fileListList) {
				//外键设置
				entity.setMainId(investPlanReport.getId());
				fileListMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(InvestPlanReport investPlanReport,List<InvestPlanSubRelationDetail> investPlanSubRelationDetailList,List<InvestPlanRelationDetail> investPlanRelationDetailList,List<FileList> fileListList) {
		investPlanReportMapper.updateById(investPlanReport);
		
		//1.先删除子表数据
		investPlanSubRelationDetailMapper.deleteByMainId(investPlanReport.getId());
		investPlanRelationDetailMapper.deleteByMainId(investPlanReport.getId());
		fileListMapper.deleteByMainId(investPlanReport.getId());
		
		//2.子表数据重新插入
		if(investPlanSubRelationDetailList!=null && investPlanSubRelationDetailList.size()>0) {
			for(InvestPlanSubRelationDetail entity:investPlanSubRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanSubRelationDetailMapper.insert(entity);
			}
		}
		if(investPlanRelationDetailList!=null && investPlanRelationDetailList.size()>0) {
			for(InvestPlanRelationDetail entity:investPlanRelationDetailList) {
				//外键设置
				entity.setReportId(investPlanReport.getId());
				investPlanRelationDetailMapper.insert(entity);
			}
		}

		if(fileListList!=null && fileListList.size()>0) {
			for(FileList entity:fileListList) {
				//外键设置
				entity.setMainId(investPlanReport.getId());
				fileListMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		investPlanSubRelationDetailMapper.deleteByMainId(id);
		investPlanRelationDetailMapper.deleteByMainId(id);
		fileListMapper.deleteByMainId(id);
		investPlanReportMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			investPlanSubRelationDetailMapper.deleteByMainId(id.toString());
			investPlanRelationDetailMapper.deleteByMainId(id.toString());
			fileListMapper.deleteByMainId(id.toString());
			investPlanReportMapper.deleteById(id);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void savePlaceFile(List<FileCatetory> investPlanSubRelationDetailList) {
		if(investPlanSubRelationDetailList!=null && investPlanSubRelationDetailList.size()>0) {
			for(FileCatetory entity:investPlanSubRelationDetailList) {
				fileCatetoryMapper.insert(entity);
			}
		}
	}
}
