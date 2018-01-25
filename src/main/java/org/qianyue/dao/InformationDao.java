package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.Information;

public interface InformationDao {

	/**
	 * �������ж�̬��Ѷ��Ϣ
	 * 
	 * @return
	 */
	List<Information> selectByPage(Information information);

	/**
	 * ���ݱ����������Ѷ
	 * 
	 * @param information
	 * @return
	 */
	int add(Information information);

	/**
	 * ���ұ���id���ֵ
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * ���½ڵ�����
	 * 
	 * @return
	 */
	int update(Information information);

	/**
	 * ɾ��ĳ�ڵ�
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * ����id��ѯ
	 * 
	 * @param id
	 * @return
	 */
	Information selectById(Long id);

}
