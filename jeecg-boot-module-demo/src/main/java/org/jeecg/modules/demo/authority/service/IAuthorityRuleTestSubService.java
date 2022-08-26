package org.jeecg.modules.demo.authority.service;

import org.jeecg.modules.demo.authority.entity.AuthorityRuleTestSub;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 权限控制演示子表
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface IAuthorityRuleTestSubService extends IService<AuthorityRuleTestSub> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<AuthorityRuleTestSub>
	 */
	public List<AuthorityRuleTestSub> selectByMainId(String mainId);
}
