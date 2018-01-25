package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Video;

public interface VideoDao {

	/**
	 * ���һ����ͼƬ
	 * 
	 * @param picture
	 * @return
	 */
	int add(Video video);

	/**
	 * ��������video��¼TODO����ӷ�ҳ
	 * 
	 * @return
	 */
	List<Video> selectByPage(Video video);

	/**
	 * ����idɾ��ָ����һ����Ƶ
	 * 
	 * @param id
	 * @return Ӱ������
	 */
	int deleteOneVideo(Long id);

	/**
	 * �����ļ���id������Ƶ����
	 * 
	 * @param id
	 * @return
	 */
	List<Video> findVidByFolderId(Long folderId);
}
