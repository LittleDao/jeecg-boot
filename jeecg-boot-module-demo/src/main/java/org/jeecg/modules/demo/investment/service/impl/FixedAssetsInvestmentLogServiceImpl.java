package org.jeecg.modules.demo.investment.service.impl;

import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentLog;
import org.jeecg.modules.demo.investment.mapper.FixedAssetsInvestmentLogMapper;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentLogService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 固定资产投资表日志
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Service
public class FixedAssetsInvestmentLogServiceImpl extends ServiceImpl<FixedAssetsInvestmentLogMapper, FixedAssetsInvestmentLog> implements IFixedAssetsInvestmentLogService {
	
	@Autowired
	private FixedAssetsInvestmentLogMapper fixedAssetsInvestmentLogMapper;
	
	@Override
	public List<FixedAssetsInvestmentLog> selectByMainId(String mainId) {
		return fixedAssetsInvestmentLogMapper.selectByMainId(mainId);
	}
}
