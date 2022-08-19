package org.jeecg.modules.demo.report.service.impl;

import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import org.jeecg.modules.demo.report.mapper.InvestPlanSubRelationDetailMapper;
import org.jeecg.modules.demo.report.service.IInvestPlanSubRelationDetailService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 关联下级年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
@Service
public class InvestPlanSubRelationDetailServiceImpl extends ServiceImpl<InvestPlanSubRelationDetailMapper, InvestPlanSubRelationDetail> implements IInvestPlanSubRelationDetailService {
	
	@Autowired
	private InvestPlanSubRelationDetailMapper investPlanSubRelationDetailMapper;
	
	@Override
	public List<InvestPlanSubRelationDetail> selectByMainId(String mainId) {
		return investPlanSubRelationDetailMapper.selectByMainId(mainId);
	}
}
