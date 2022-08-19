package org.jeecg.modules.demo.negative.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.demo.negative.entity.NegativeList;

import java.util.List;

/**
 * @Description: 负面清单核对表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Mapper
public interface NegativeListMapper extends BaseMapper<NegativeList> {

    @Select("select * from negative_list where 1=1 ")
    List<NegativeList> listByType();

}
