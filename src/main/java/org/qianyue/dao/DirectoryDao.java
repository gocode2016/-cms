package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Directory;

public interface DirectoryDao {

	/**
	 * 初始化页面加载所有树节点
	 * 
	 * @return
	 */
	List<Directory> selectAll();

	/**
	 * 更新节点名称
	 * 
	 * @return
	 */
	int update(Directory directory);

	/**
	 * 根据id查询某节点
	 * 
	 * @param id
	 * @return
	 */
	Directory selectById(Long id);

	/**
	 * 修改节点属性把自身定为父节点或子节点
	 * 
	 * @param isParent
	 * @return
	 */
	int updateIsParentOrNot(Directory directory);

	/**
	 * 新增一个子节点
	 * 
	 * @param directory
	 * @return
	 */
	int addChild(Directory directory);

	/**
	 * 查找表中id最大值
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * 删除某节点
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * @param 根据是否含有子节点并返回子节点
	 * @return
	 */
	List<Integer> selectIdByParentId(Long id);

	/**
	 * @param 判断id是否存在
	 * @return
	 */
	int judgeIdExists(Long id);

	/**
	 * 判断自身是否为父节点
	 * 
	 * @param id
	 * @return
	 */
	int judgeIsParent(Long id);

	/**
	 * 根据文件夹id返回子文件夹集合
	 * 
	 * @param id
	 * @return
	 */
	List<Directory> returnChildFolders(Long parentId);

	/**
	 * @param 添加首图
	 * @return
	 */
	int headImage(Directory directory);

	/**
	 * 初始化页面加载所有树节点
	 * 
	 * @return
	 */
	List<Directory> selectAllByPage(Directory directory);

}
