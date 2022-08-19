package org.jeecg.modules.demo.investmentNegative.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;

/**
 * @Description: 项目负面清单列表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Mapper
public interface FixedAssetsInvestmentNegativeMapper extends BaseMapper<FixedAssetsInvestmentNegative> {

    @Delete("delete from fixed_assets_investment_negative where investment_id=#{investmentId} ")
    void deleteByInvestmentId(String investmentId);
}
