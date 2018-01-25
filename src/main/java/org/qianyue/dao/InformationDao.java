package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Information;

public interface InformationDao {

	/**
	 * 查找所有动态资讯信息
	 * 
	 * @return
	 */
	List<Information> selectByPage(Information information);

	/**
	 * 根据标题插入新资讯
	 * 
	 * @param information
	 * @return
	 */
	int add(Information information);

	/**
	 * 查找表中id最大值
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * 更新节点名称
	 * 
	 * @return
	 */
	int update(Information information);

	/**
	 * 删除某节点
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Information selectById(Long id);

}
