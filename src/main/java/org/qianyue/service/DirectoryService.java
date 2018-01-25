package org.qianyue.service;

import java.util.List;

import org.qianyue.dto.DirectoryDto;

public interface DirectoryService {

	/**
	 * 加载所有树节点
	 * 
	 * @return
	 */
	List<DirectoryDto> selectAll();

	/**
	 * 更新树节点
	 * 
	 * @param DirectoryDto
	 * @return
	 */
	boolean update(DirectoryDto DirectoryDto);

	/**
	 * 修改树节点属性 使自身为父节点 true
	 * 
	 * @param isParent
	 * @return
	 */
	boolean updateIsParent(Long isParent);

	/**
	 * 新添加子节点
	 * 
	 * @param DirectoryDto
	 * @return 新添加子节点id
	 */
	int addChild(DirectoryDto DirectoryDto);

	/**
	 * 查找最大id 最新填进去的id
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * 删除所有节点
	 * 
	 * @return true false
	 */
	boolean deleteAllChild(Long id);

	/**
	 * 判断自身是否为父节点
	 * 
	 * @param id
	 * @return
	 */
	boolean judgeIsParent(Long id);

	/**
	 * 根据所属文件夹id获取完整路径
	 * 
	 * @param id
	 * @return 文件路径(String)
	 */
	String allPath(Long id);

	/**
	 * 根据文件夹id查找出所有子文件夹 前提是里面要有子文件夹
	 * 
	 * @param id
	 * @return
	 */
	public List<DirectoryDto> selectChildsByParentId(Long id);

	/**
	 * 根据ID查询某文件夹信息
	 * 
	 * @param id
	 * @return
	 */
	DirectoryDto selectById(Long id);

	/**
	 * 修改树节点属性 使自身不为父节点
	 * 
	 * @param isParent
	 * @return
	 */
	boolean updateIsNotParent(Long isParent);

	/**
	 * @param 添加首图
	 * @return
	 */
	boolean addHeadImage(DirectoryDto DirectoryDto);

	/**
	 * 目录列表获取
	 * 
	 * @return
	 */
	public List<DirectoryDto> selectAllDirForList(DirectoryDto directoryDto);

}
