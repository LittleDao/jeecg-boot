package org.jeecg.modules.demo.investment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestment;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentLog;
import org.jeecg.modules.demo.investment.entity.FixedAssetsInvestmentSub;
import org.jeecg.modules.demo.investment.mapper.FixedAssetsInvestmentLogMapper;
import org.jeecg.modules.demo.investment.mapper.FixedAssetsInvestmentMapper;
import org.jeecg.modules.demo.investment.mapper.FixedAssetsInvestmentSubMapper;
import org.jeecg.modules.demo.investment.service.IFixedAssetsInvestmentService;
import org.jeecg.modules.demo.investmentNegative.entity.FixedAssetsInvestmentNegative;
import org.jeecg.modules.demo.investmentNegative.mapper.FixedAssetsInvestmentNegativeMapper;
import org.jeecg.modules.demo.investmentNegative.service.IFixedAssetsInvestmentNegativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 固定资产投资表
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Service
public class FixedAssetsInvestmentServiceImpl extends ServiceImpl<FixedAssetsInvestmentMapper, FixedAssetsInvestment> implements IFixedAssetsInvestmentService {

	@Autowired
	private FixedAssetsInvestmentMapper fixedAssetsInvestmentMapper;
	@Autowired
	private FixedAssetsInvestmentSubMapper fixedAssetsInvestmentSubMapper;
	@Autowired
	private FixedAssetsInvestmentLogMapper fixedAssetsInvestmentLogMapper;
	@Autowired
	private FixedAssetsInvestmentNegativeMapper fixedAssetsInvestmentNegativeMapper;

	@Resource
	IFixedAssetsInvestmentNegativeService fixedAssetsInvestmentNegativeService;


	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(FixedAssetsInvestment fixedAssetsInvestment, List<FixedAssetsInvestmentSub> fixedAssetsInvestmentSubList,List<FixedAssetsInvestmentLog> fixedAssetsInvestmentLogList) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		fixedAssetsInvestmentMapper.insert(fixedAssetsInvestment);
		if(fixedAssetsInvestmentSubList!=null && fixedAssetsInvestmentSubList.size()>0) {
			for(FixedAssetsInvestmentSub entity:fixedAssetsInvestmentSubList) {
				//外键设置
				entity.setInvestmentId(fixedAssetsInvestment.getId());
				fixedAssetsInvestmentSubMapper.insert(entity);
			}
		}

		FixedAssetsInvestmentLog entity = new FixedAssetsInvestmentLog();
		//外键设置
		entity.setInvestmentId(fixedAssetsInvestment.getId());
		entity.setOperation("项目入库");
		entity.setOperationUser(sysUser.getUsername());
		fixedAssetsInvestmentLogMapper.insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(FixedAssetsInvestment fixedAssetsInvestment,List<FixedAssetsInvestmentSub> fixedAssetsInvestmentSubList,List<FixedAssetsInvestmentNegative> fixedAssetsInvestmentNegativeList) {
		fixedAssetsInvestmentMapper.updateById(fixedAssetsInvestment);
		
		//1.先删除子表数据
		fixedAssetsInvestmentSubMapper.deleteByMainId(fixedAssetsInvestment.getId());
//		fixedAssetsInvestmentLogMapper.deleteByMainId(fixedAssetsInvestment.getId());
		fixedAssetsInvestmentNegativeMapper.deleteByInvestmentId(fixedAssetsInvestment.getId());
		
		//2.子表数据重新插入
		if(fixedAssetsInvestmentSubList!=null && fixedAssetsInvestmentSubList.size()>0) {
			for(FixedAssetsInvestmentSub entity:fixedAssetsInvestmentSubList) {
				//外键设置
				entity.setInvestmentId(fixedAssetsInvestment.getId());
				fixedAssetsInvestmentSubMapper.insert(entity);
			}
		}
//		if(fixedAssetsInvestmentLogList!=null && fixedAssetsInvestmentLogList.size()>0) {
//			for(FixedAssetsInvestmentLog entity:fixedAssetsInvestmentLogList) {
//				//外键设置
//				entity.setInvestmentId(fixedAssetsInvestment.getId());
//				fixedAssetsInvestmentLogMapper.insert(entity);
//			}
//		}
		//3.负面清单校验信息
		if(fixedAssetsInvestmentNegativeList!=null && fixedAssetsInvestmentNegativeList.size()>0) {
			for(FixedAssetsInvestmentNegative entity:fixedAssetsInvestmentNegativeList) {
				//外键设置
				entity.setInvestmentId(fixedAssetsInvestment.getId());
				fixedAssetsInvestmentNegativeMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		fixedAssetsInvestmentSubMapper.deleteByMainId(id);
		fixedAssetsInvestmentLogMapper.deleteByMainId(id);
		fixedAssetsInvestmentMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			fixedAssetsInvestmentSubMapper.deleteByMainId(id.toString());
			fixedAssetsInvestmentLogMapper.deleteByMainId(id.toString());
			fixedAssetsInvestmentMapper.deleteById(id);
		}
	}

	@Override
	public List<FixedAssetsInvestment> selectUserNeedNotice(String nowDate) {
		return fixedAssetsInvestmentMapper.selectUserNeedNotice(nowDate);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void initiation(FixedAssetsInvestment fixedAssetsInvestmentEntity) {
		fixedAssetsInvestmentEntity.setProjectStatus(1);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		fixedAssetsInvestmentMapper.updateById(fixedAssetsInvestmentEntity);
		fixedAssetsInvestmentNegativeService.addBatch(fixedAssetsInvestmentEntity.getId());
		FixedAssetsInvestmentLog entity = new FixedAssetsInvestmentLog();
		//外键设置
		entity.setInvestmentId(fixedAssetsInvestmentEntity.getId());
		entity.setOperation("立项");
		entity.setOperationUser(sysUser.getUsername());
		fixedAssetsInvestmentLogMapper.insert(entity);
	}

	@Override
	@Transactional
	public void initiationSubmit(FixedAssetsInvestment fixedAssetsInvestmentEntity) {
		fixedAssetsInvestmentEntity.setProjectStatus(2);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		fixedAssetsInvestmentMapper.updateById(fixedAssetsInvestmentEntity);
		FixedAssetsInvestmentLog entity = new FixedAssetsInvestmentLog();
		//外键设置
		entity.setInvestmentId(fixedAssetsInvestmentEntity.getId());
		entity.setOperation("立项完成");
		entity.setOperationUser(sysUser.getUsername());
		fixedAssetsInvestmentLogMapper.insert(entity);
	}

}
