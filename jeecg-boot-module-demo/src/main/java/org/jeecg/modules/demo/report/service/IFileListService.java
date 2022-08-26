package org.jeecg.modules.demo.report.service;

import org.jeecg.modules.demo.report.entity.FileList;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 附件列表
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
public interface IFileListService extends IService<FileList> {

	/**
	 * 通过主表id查询子表数据
	 *
	 * @param mainId 主表id
	 * @return List<FileList>
	 */
	public List<FileList> selectByMainId(String mainId);
	/**
	 * 根据Id列表查询列表
	 */
	List<FileList> selectByIds(List<String> ids);
}
