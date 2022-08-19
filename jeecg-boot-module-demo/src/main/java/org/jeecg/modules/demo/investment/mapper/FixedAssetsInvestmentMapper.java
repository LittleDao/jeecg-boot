package org.jeecg.modules.demo.investment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestment;

import java.util.List;

/**
 * @Description: 固定资产投资表
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Mapper
public interface FixedAssetsInvestmentMapper extends BaseMapper<FixedAssetsInvestment> {

    @Select("select * from fixed_assets_investment where  project_status=0 and project_inititation_time <= #{nowTime} ")
    List<FixedAssetsInvestment> selectUserNeedNotice(String nowTime);
}
