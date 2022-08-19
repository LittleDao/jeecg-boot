package org.jeecg.modules.demo.report.service;

import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 投资计划关联年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
public interface IInvestPlanRelationDetailService extends IService<InvestPlanRelationDetail> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<InvestPlanRelationDetail>
	 */
	public List<InvestPlanRelationDetail> selectByMainId(String mainId);
}
