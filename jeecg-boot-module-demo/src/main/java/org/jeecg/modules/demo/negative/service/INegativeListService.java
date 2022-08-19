package org.jeecg.modules.demo.negative.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.negative.entity.NegativeList;

import java.util.List;

/**
 * @Description: 负面清单核对表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
public interface INegativeListService extends IService<NegativeList> {

    /**
     * 负面清单列表
     * @return
     */
    List<NegativeList> listByType();
}
