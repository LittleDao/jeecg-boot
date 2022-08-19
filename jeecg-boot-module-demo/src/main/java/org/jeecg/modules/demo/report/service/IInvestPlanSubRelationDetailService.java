package org.jeecg.modules.demo.report.service;

import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 关联下级年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
public interface IInvestPlanSubRelationDetailService extends IService<InvestPlanSubRelationDetail> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<InvestPlanSubRelationDetail>
	 */
	public List<InvestPlanSubRelationDetail> selectByMainId(String mainId);
}
