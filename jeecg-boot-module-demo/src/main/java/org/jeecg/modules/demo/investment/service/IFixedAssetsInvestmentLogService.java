package org.jeecg.modules.demo.investment.service;

import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 固定资产投资表日志
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
public interface IFixedAssetsInvestmentLogService extends IService<FixedAssetsInvestmentLog> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<FixedAssetsInvestmentLog>
	 */
	public List<FixedAssetsInvestmentLog> selectByMainId(String mainId);
}
