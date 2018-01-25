package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Picture;

public interface PictureDao {

	/**
	 * ���һ����ͼƬ
	 * 
	 * @param picture
	 * @return
	 */
	int add(Picture picture);

	/**
	 * ��ѯ����ͼƬ TODO����ӷ�ҳ
	 * 
	 * @return
	 */
	List<Picture> selectByPage(Picture picture);

	/**
	 * ����idɾ��ָ����һ��ͼƬ
	 * 
	 * @param id
	 * @return Ӱ������
	 */
	int deleteOnePicture(Long id);

	/**
	 * �����ļ���id����ͼƬ����
	 * 
	 * @param id
	 * @return
	 */
	List<Picture> findPicByFolderId(Long folderId);

}
