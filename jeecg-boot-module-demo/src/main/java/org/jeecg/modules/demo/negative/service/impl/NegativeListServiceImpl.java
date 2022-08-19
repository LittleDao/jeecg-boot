package org.jeecg.modules.demo.negative.service.impl;

import org.jeecg.modules.demo.negative.entity.NegativeList;
import org.jeecg.modules.demo.negative.mapper.NegativeListMapper;
import org.jeecg.modules.demo.negative.service.INegativeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 负面清单核对表
 * @Author: jeecg-boot
 * @Date:   2022-08-16
 * @Version: V1.0
 */
@Service
public class NegativeListServiceImpl extends ServiceImpl<NegativeListMapper, NegativeList> implements INegativeListService {

    @Autowired
    NegativeListMapper negativeListMapper;
    @Override
    public List<NegativeList> listByType() {
        return negativeListMapper.listByType();
    }
}
