package org.jeecg.modules.demo.investmentNegative.service;

import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 项目负面清单列表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
public interface IFixedAssetsInvestmentNegativeService extends IService<FixedAssetsInvestmentNegative> {

    /**
     * 初始化负面清单数据
     * @param investmentId
     */
    void addBatch(String investmentId);
}
