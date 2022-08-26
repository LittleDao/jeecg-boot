package org.jeecg.modules.demo.authority.mapper;

import java.util.List;
import org.jeecg.modules.demo.authority.entity.AuthorityRuleTestSub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 权限控制演示子表
 * @Author: jeecg-boot
 * @Date:   2022-08-25
 * @Version: V1.0
 */
public interface AuthorityRuleTestSubMapper extends BaseMapper<AuthorityRuleTestSub> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId 主表id
   * @return List<AuthorityRuleTestSub>
   */
	public List<AuthorityRuleTestSub> selectByMainId(@Param("mainId") String mainId);
}
