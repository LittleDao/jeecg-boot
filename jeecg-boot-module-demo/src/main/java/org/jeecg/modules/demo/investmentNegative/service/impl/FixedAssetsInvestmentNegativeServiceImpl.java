package org.jeecg.modules.demo.investmentNegative.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;
import org.jeecg.modules.demo.investmentNegative.mapper.FixedAssetsInvestmentNegativeMapper;
import org.jeecg.modules.demo.investmentNegative.service.IFixedAssetsInvestmentNegativeService;
import org.jeecg.modules.demo.negative.entity.NegativeList;
import org.jeecg.modules.demo.negative.service.INegativeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 项目负面清单列表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Service
public class FixedAssetsInvestmentNegativeServiceImpl extends ServiceImpl<FixedAssetsInvestmentNegativeMapper, FixedAssetsInvestmentNegative> implements IFixedAssetsInvestmentNegativeService {

    @Autowired
    FixedAssetsInvestmentNegativeMapper fixedAssetsInvestmentNegativeMapper;

    @Resource
    INegativeListService negativeListService;

    @Override
    public void addBatch(String investmentId) {
        //先删除原表数据
        fixedAssetsInvestmentNegativeMapper.deleteByInvestmentId(investmentId);
        //新增数据
        List<NegativeList> negativeLists = negativeListService.listByType();
        negativeLists.stream().forEach(V->{
            FixedAssetsInvestmentNegative negative = new FixedAssetsInvestmentNegative();
            negative.setInvestmentId(investmentId);
            negative.setNegativeCode(V.getNegativeCode());
            negative.setNegativeType(V.getNegativeType());
            negative.setDescription(V.getDescription());
            negative.setInOutArea(V.getInOutArea());
            fixedAssetsInvestmentNegativeMapper.insert(negative);
        });

    }
}
