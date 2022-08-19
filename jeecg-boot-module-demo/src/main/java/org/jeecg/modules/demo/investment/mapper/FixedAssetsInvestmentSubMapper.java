package org.jeecg.modules.demo.investment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentSub;

import java.util.List;

/**
 * @Description: 关联信息
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Mapper
public interface FixedAssetsInvestmentSubMapper extends BaseMapper<FixedAssetsInvestmentSub> {

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
   * @return List<FixedAssetsInvestmentSub>
   */
	public List<FixedAssetsInvestmentSub> selectByMainId(@Param("mainId") String mainId);
}
