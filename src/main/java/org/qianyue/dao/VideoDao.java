package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Video;

public interface VideoDao {

	/**
	 * 添加一张新图片
	 * 
	 * @param picture
	 * @return
	 */
	int add(Video video);

	/**
	 * 查找所有video记录TODO待添加分页
	 * 
	 * @return
	 */
	List<Video> selectByPage(Video video);

	/**
	 * 根据id删除指定的一个视频
	 * 
	 * @param id
	 * @return 影响行数
	 */
	int deleteOneVideo(Long id);

	/**
	 * 根据文件夹id查找视频集合
	 * 
	 * @param id
	 * @return
	 */
	List<Video> findVidByFolderId(Long folderId);
}
