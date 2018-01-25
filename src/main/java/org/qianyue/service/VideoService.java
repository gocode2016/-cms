package org.qianyue.service;

import java.util.List;

import org.qianyue.ForApiDto.VideoForApiDto;
import org.qianyue.dto.VideoDto;

public interface VideoService {

	/**
	 * 向指定文件夹 添加视频
	 * 
	 * @param video
	 * @return
	 */
	boolean addVideo(VideoDto vdeoDto);

	/**
	 * 查询video所有对象 TODO待添加分页
	 * 
	 * @return
	 */
	List<VideoDto> selectAllOrFolder(VideoDto videoDto);

	/**
	 * 根据id删除一部视频
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOneVideo(Long id);

	/**
	 * 根据文件夹id查找视频id集合
	 * 
	 * @return
	 */
	void findAndDeleteIdByFolderId(Long id);

	/**
	 * 查找文件夹下所有视频
	 * 
	 * @return
	 */
	VideoForApiDto selectVidByFoldId(Long id);

	/**
	 * 查找文件夹下视频 选取某一页
	 * 
	 * @param id
	 * @param currentPage
	 * @return
	 */
	VideoForApiDto selectVidByFoldIdByPage(Long id, int currentPage, int pageNumber);
}
