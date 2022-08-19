package org.jeecg.modules.demo.report.mapper;

import java.util.List;
import org.jeecg.modules.demo.report.entity.InvestPlanRelationDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 投资计划关联年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
public interface InvestPlanRelationDetailMapper extends BaseMapper<InvestPlanRelationDetail> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<InvestPlanRelationDetail>
   */
	public List<InvestPlanRelationDetail> selectByMainId(@Param("mainId") String mainId);
}
