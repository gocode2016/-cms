package org.qianyue.service;

import java.util.List;

import org.qianyue.ForApiDto.PictureForApiDto;
import org.qianyue.dto.PictureDto;

public interface PictureService {

	/**
	 * 添加图片
	 * 
	 * @param pictureDto
	 * @return
	 */
	boolean addPicture(PictureDto pictureDto);

	/**
	 * 查询picture所有对象 TODO待添加分页
	 * 
	 * @return
	 */
	List<PictureDto> selectAllOrFolder(PictureDto pictureDto);

	/**
	 * 根据id删除一张图片
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOnePicture(Long id);

	/**
	 * 根据文件夹id查找图片id集合并删除本地文件
	 * 
	 * 
	 * @return
	 */
	void findAndDeleteIdByFolderId(Long id);

	/**
	 * 查找文件夹下所有图片
	 * 
	 * @param id
	 * @return图片集合
	 */
	PictureForApiDto selectPicByFoldId(Long id);

	/**
	 * 查找文件夹下图片 分某一页的查询
	 * 
	 * @param id
	 * @param currentPage
	 * @return
	 */
	PictureForApiDto selectPicByFoldIdByPage(Long id, int currentPage, int pageNumber);

}
