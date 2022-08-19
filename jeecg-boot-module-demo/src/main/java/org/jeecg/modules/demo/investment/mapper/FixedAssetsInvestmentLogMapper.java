package org.jeecg.modules.demo.investment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentLog;

import java.util.List;

/**
 * @Description: 固定资产投资表日志
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Mapper
public interface FixedAssetsInvestmentLogMapper extends BaseMapper<FixedAssetsInvestmentLog> {

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
   * @return List<FixedAssetsInvestmentLog>
   */
	public List<FixedAssetsInvestmentLog> selectByMainId(@Param("mainId") String mainId);
}
