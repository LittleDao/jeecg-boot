package org.jeecg.modules.demo.report.mapper;

import java.util.List;
import org.jeecg.modules.demo.report.entity.FileList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 附件列表
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
public interface FileListMapper extends BaseMapper<FileList> {

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
   * @return List<FileList>
   */
	public List<FileList> selectByMainId(@Param("mainId") String mainId);
}
