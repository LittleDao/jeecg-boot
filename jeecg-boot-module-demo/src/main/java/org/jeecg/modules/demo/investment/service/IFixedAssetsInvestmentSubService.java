package org.jeecg.modules.demo.investment.service;

import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentSub;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 关联信息
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
public interface IFixedAssetsInvestmentSubService extends IService<FixedAssetsInvestmentSub> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<FixedAssetsInvestmentSub>
	 */
	public List<FixedAssetsInvestmentSub> selectByMainId(String mainId);
}
