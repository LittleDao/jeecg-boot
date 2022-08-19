package org.jeecg.modules.demo.investment.service.impl;

import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentSub;
import org.jeecg.modules.demo.investment.mapper.FixedAssetsInvestmentSubMapper;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentSubService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 关联信息
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Service
public class FixedAssetsInvestmentSubServiceImpl extends ServiceImpl<FixedAssetsInvestmentSubMapper, FixedAssetsInvestmentSub> implements IFixedAssetsInvestmentSubService {
	
	@Autowired
	private FixedAssetsInvestmentSubMapper fixedAssetsInvestmentSubMapper;
	
	@Override
	public List<FixedAssetsInvestmentSub> selectByMainId(String mainId) {
		return fixedAssetsInvestmentSubMapper.selectByMainId(mainId);
	}
}
