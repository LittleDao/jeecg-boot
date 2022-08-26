package org.jeecg.modules.demo.authority.service.impl;

import org.jeecg.modules.demo.authority.entity.AuthorityRuleTestSub;
import org.jeecg.modules.demo.authority.mapper.AuthorityRuleTestSubMapper;
import org.jeecg.modules.demo.authority.service.IAuthorityRuleTestSubService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 权限控制演示子表
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class AuthorityRuleTestSubServiceImpl extends ServiceImpl<AuthorityRuleTestSubMapper, AuthorityRuleTestSub> implements IAuthorityRuleTestSubService {
	
	@Autowired
	private AuthorityRuleTestSubMapper authorityRuleTestSubMapper;
	
	@Override
	public List<AuthorityRuleTestSub> selectByMainId(String mainId) {
		return authorityRuleTestSubMapper.selectByMainId(mainId);
	}
}
