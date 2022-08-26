package org.jeecg.modules.demo.authority.service.impl;

import org.jeecg.modules.demo.authority.entity.AuthorityRuleTest;
import org.jeecg.modules.demo.authority.entity.AuthorityRuleTestSub;
import org.jeecg.modules.demo.authority.mapper.AuthorityRuleTestSubMapper;
import org.jeecg.modules.demo.authority.mapper.AuthorityRuleTestMapper;
import org.jeecg.modules.demo.authority.service.IAuthorityRuleTestService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 权限控制演示
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
@Service
public class AuthorityRuleTestServiceImpl extends ServiceImpl<AuthorityRuleTestMapper, AuthorityRuleTest> implements IAuthorityRuleTestService {

	@Autowired
	private AuthorityRuleTestMapper authorityRuleTestMapper;
	@Autowired
	private AuthorityRuleTestSubMapper authorityRuleTestSubMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(AuthorityRuleTest authorityRuleTest, List<AuthorityRuleTestSub> authorityRuleTestSubList) {
		authorityRuleTestMapper.insert(authorityRuleTest);
		if(authorityRuleTestSubList!=null && authorityRuleTestSubList.size()>0) {
			for(AuthorityRuleTestSub entity:authorityRuleTestSubList) {
				//外键设置
				entity.setAuthorityId(authorityRuleTest.getId());
				authorityRuleTestSubMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(AuthorityRuleTest authorityRuleTest,List<AuthorityRuleTestSub> authorityRuleTestSubList) {
		authorityRuleTestMapper.updateById(authorityRuleTest);
		
		//1.先删除子表数据
		authorityRuleTestSubMapper.deleteByMainId(authorityRuleTest.getId());
		
		//2.子表数据重新插入
		if(authorityRuleTestSubList!=null && authorityRuleTestSubList.size()>0) {
			for(AuthorityRuleTestSub entity:authorityRuleTestSubList) {
				//外键设置
				entity.setAuthorityId(authorityRuleTest.getId());
				authorityRuleTestSubMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		authorityRuleTestSubMapper.deleteByMainId(id);
		authorityRuleTestMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			authorityRuleTestSubMapper.deleteByMainId(id.toString());
			authorityRuleTestMapper.deleteById(id);
		}
	}
	
}
