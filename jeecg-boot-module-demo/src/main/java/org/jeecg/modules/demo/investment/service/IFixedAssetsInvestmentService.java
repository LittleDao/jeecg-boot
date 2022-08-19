package org.jeecg.modules.demo.investment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestment;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentLog;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentSub;
import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 固定资产投资表
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
public interface IFixedAssetsInvestmentService extends IService<FixedAssetsInvestment> {

	/**
	 * 添加一对多
	 *
	 * @param fixedAssetsInvestment
	 * @param fixedAssetsInvestmentSubList
	 * @param fixedAssetsInvestmentLogList
	 */
	public void saveMain(FixedAssetsInvestment fixedAssetsInvestment,List<FixedAssetsInvestmentSub> fixedAssetsInvestmentSubList,List<FixedAssetsInvestmentLog> fixedAssetsInvestmentLogList) ;
	
	/**
	 * 修改一对多
	 *
	 * @param fixedAssetsInvestment
	 * @param fixedAssetsInvestmentSubList
	 * @param fixedAssetsInvestmentNegativeList
	 */
	public void updateMain(FixedAssetsInvestment fixedAssetsInvestment,List<FixedAssetsInvestmentSub> fixedAssetsInvestmentSubList,List<FixedAssetsInvestmentNegative> fixedAssetsInvestmentNegativeList);
	
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

	/**
	 * 查询需要通知的用户
	 */
	public List<FixedAssetsInvestment> selectUserNeedNotice(String nowDate);

	/**
	 * 立项
	 * @param fixedAssetsInvestmentEntity
	 */
	void initiation(FixedAssetsInvestment fixedAssetsInvestmentEntity);

	/**
	 * 立项提交
	 * @param fixedAssetsInvestmentEntity
	 */
    void initiationSubmit(FixedAssetsInvestment fixedAssetsInvestmentEntity);
}
