package org.jeecg.modules.demo.authority.service;

import org.jeecg.modules.demo.authority.entity.AuthorityRuleTestSub;
import org.jeecg.modules.demo.authority.entity.AuthorityRuleTest;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 权限控制演示
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IAuthorityRuleTestService extends IService<AuthorityRuleTest> {

	/**
	 * 添加一对多
	 *
	 * @param authorityRuleTest
	 * @param authorityRuleTestSubList
	 */
	public void saveMain(AuthorityRuleTest authorityRuleTest,List<AuthorityRuleTestSub> authorityRuleTestSubList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param authorityRuleTest
	 * @param authorityRuleTestSubList
	 */
	public void updateMain(AuthorityRuleTest authorityRuleTest,List<AuthorityRuleTestSub> authorityRuleTestSubList);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
