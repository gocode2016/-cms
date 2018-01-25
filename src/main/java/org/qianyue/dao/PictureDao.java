package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Picture;

public interface PictureDao {

	/**
	 * 添加一张新图片
	 * 
	 * @param picture
	 * @return
	 */
	int add(Picture picture);

	/**
	 * 查询所有图片 TODO待添加分页
	 * 
	 * @return
	 */
	List<Picture> selectByPage(Picture picture);

	/**
	 * 根据id删除指定的一张图片
	 * 
	 * @param id
	 * @return 影响行数
	 */
	int deleteOnePicture(Long id);

	/**
	 * 根据文件夹id查找图片集合
	 * 
	 * @param id
	 * @return
	 */
	List<Picture> findPicByFolderId(Long folderId);

}
