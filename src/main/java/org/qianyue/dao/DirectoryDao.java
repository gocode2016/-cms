package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Directory;

public interface DirectoryDao {

	/**
	 * ��ʼ��ҳ������������ڵ�
	 * 
	 * @return
	 */
	List<Directory> selectAll();

	/**
	 * ���½ڵ�����
	 * 
	 * @return
	 */
	int update(Directory directory);

	/**
	 * ����id��ѯĳ�ڵ�
	 * 
	 * @param id
	 * @return
	 */
	Directory selectById(Long id);

	/**
	 * �޸Ľڵ����԰�����Ϊ���ڵ���ӽڵ�
	 * 
	 * @param isParent
	 * @return
	 */
	int updateIsParentOrNot(Directory directory);

	/**
	 * ����һ���ӽڵ�
	 * 
	 * @param directory
	 * @return
	 */
	int addChild(Directory directory);

	/**
	 * ���ұ���id���ֵ
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * ɾ��ĳ�ڵ�
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * @param �����Ƿ����ӽڵ㲢�����ӽڵ�
	 * @return
	 */
	List<Integer> selectIdByParentId(Long id);

	/**
	 * @param �ж�id�Ƿ����
	 * @return
	 */
	int judgeIdExists(Long id);

	/**
	 * �ж������Ƿ�Ϊ���ڵ�
	 * 
	 * @param id
	 * @return
	 */
	int judgeIsParent(Long id);

	/**
	 * �����ļ���id�������ļ��м���
	 * 
	 * @param id
	 * @return
	 */
	List<Directory> returnChildFolders(Long parentId);

	/**
	 * @param �����ͼ
	 * @return
	 */
	int headImage(Directory directory);

	/**
	 * ��ʼ��ҳ������������ڵ�
	 * 
	 * @return
	 */
	List<Directory> selectAllByPage(Directory directory);

}
