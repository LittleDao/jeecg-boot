package org.jeecg.modules.demo.report.service.impl;

import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import org.jeecg.modules.demo.report.mapper.InvestPlanRelationDetailMapper;
import org.jeecg.modules.demo.report.service.IInvestPlanRelationDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 投资计划关联年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
@Service
public class InvestPlanRelationDetailServiceImpl extends ServiceImpl<InvestPlanRelationDetailMapper, InvestPlanRelationDetail> implements IInvestPlanRelationDetailService {
	
	@Autowired
	private InvestPlanRelationDetailMapper investPlanRelationDetailMapper;
	
	@Override
	public List<InvestPlanRelationDetail> selectByMainId(String mainId) {
		return investPlanRelationDetailMapper.selectByMainId(mainId);
	}
}
