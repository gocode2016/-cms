package org.qianyue.service;

import java.util.List;

import org.qianyue.ForApiDto.VideoForApiDto;
import org.qianyue.dto.VideoDto;

public interface VideoService {

	/**
	 * ��ָ���ļ��� �����Ƶ
	 * 
	 * @param video
	 * @return
	 */
	boolean addVideo(VideoDto vdeoDto);

	/**
	 * ��ѯvideo���ж��� TODO����ӷ�ҳ
	 * 
	 * @return
	 */
	List<VideoDto> selectAllOrFolder(VideoDto videoDto);

	/**
	 * ����idɾ��һ����Ƶ
	 * 
	 * @param id
	 * @return
	 */
	boolean deleteOneVideo(Long id);

	/**
	 * �����ļ���id������Ƶid����
	 * 
	 * @return
	 */
	void findAndDeleteIdByFolderId(Long id);

	/**
	 * �����ļ�����������Ƶ
	 * 
	 * @return
	 */
	VideoForApiDto selectVidByFoldId(Long id);

	/**
	 * �����ļ�������Ƶ ѡȡĳһҳ
	 * 
	 * @param id
	 * @param currentPage
	 * @return
	 */
	VideoForApiDto selectVidByFoldIdByPage(Long id, int currentPage, int pageNumber);
}
