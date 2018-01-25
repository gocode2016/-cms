package org.qianyue.service;

import java.util.List;

import org.qianyue.bean.Information;
import org.qianyue.dto.InformationDto;

public interface InformationService {

	/**
	 * 查找所有 列表资讯
	 * 
	 * @return
	 */
	List<InformationDto> select(InformationDto informationDto);

	/**
	 * 添加一条新资讯
	 * 
	 * @param Information
	 * @return
	 */
	boolean add(InformationDto informationDto, String fileNameUrl);

	/**
	 * 查找最大id 最新填进去的id
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * 更新树节点
	 * 
	 * @param DirectoryDto
	 * @return
	 */
	boolean update(Information Information);

	/**
	 * 根据id删除一篇文章
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);

	/**
	 * 根据id查询文章
	 * 
	 * @param id
	 * @return
	 */
	InformationDto selectById(Long id);

}
