package org.jeecg.modules.demo.report.mapper;

import java.util.List;
import org.jeecg.modules.demo.report.entity.InvestPlanSubRelationDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 关联下级年度投资计划
 * @Author: jeecg-boot
 * @Date:   2022-08-19
 * @Version: V1.0
 */
public interface InvestPlanSubRelationDetailMapper extends BaseMapper<InvestPlanSubRelationDetail> {

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
   * @return List<InvestPlanSubRelationDetail>
   */
	public List<InvestPlanSubRelationDetail> selectByMainId(@Param("mainId") String mainId);
}
