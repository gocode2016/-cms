package org.qianyue.service;

import java.util.List;

import org.qianyue.ForApiDto.PictureForApiDto;
import org.qianyue.dto.PictureDto;

public interface PictureService {

	/**
	 * ���ͼƬ
	 * 
	 * @param pictureDto
	 * @return
	 */
	boolean addPicture(PictureDto pictureDto);

	/**
	 * ��ѯpicture���ж��� TODO����ӷ�ҳ
	 * 
	 * @return
	 */
	List<PictureDto> selectAllOrFolder(PictureDto pictureDto);

	/**
	 * ����idɾ��һ��ͼƬ
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOnePicture(Long id);

	/**
	 * �����ļ���id����ͼƬid���ϲ�ɾ�������ļ�
	 * 
	 * 
	 * @return
	 */
	void findAndDeleteIdByFolderId(Long id);

	/**
	 * �����ļ���������ͼƬ
	 * 
	 * @param id
	 * @returnͼƬ����
	 */
	PictureForApiDto selectPicByFoldId(Long id);

	/**
	 * �����ļ�����ͼƬ ��ĳһҳ�Ĳ�ѯ
	 * 
	 * @param id
	 * @param currentPage
	 * @return
	 */
	PictureForApiDto selectPicByFoldIdByPage(Long id, int currentPage, int pageNumber);

}
