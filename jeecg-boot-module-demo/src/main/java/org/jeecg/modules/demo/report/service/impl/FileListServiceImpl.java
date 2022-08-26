package org.jeecg.modules.demo.report.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.demo.report.entity.FileList;
import org.jeecg.modules.demo.report.mapper.FileListMapper;
import org.jeecg.modules.demo.report.service.IFileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 附件列表
 * @Author: jeecg-boot
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Service
public class FileListServiceImpl extends ServiceImpl<FileListMapper, FileList> implements IFileListService {
	
	@Autowired
	private FileListMapper fileListMapper;
	
	@Override
	public List<FileList> selectByMainId(String mainId) {
		return fileListMapper.selectByMainId(mainId);
	}

	@Override
	public List<FileList> selectByIds(List<String> ids) {
		return  fileListMapper.selectBatchIds(ids);
	}
}
