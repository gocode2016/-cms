package org.qianyue.service;

import java.util.List;

import org.qianyue.dto.DirectoryDto;

public interface DirectoryService {

	/**
	 * �����������ڵ�
	 * 
	 * @return
	 */
	List<DirectoryDto> selectAll();

	/**
	 * �������ڵ�
	 * 
	 * @param DirectoryDto
	 * @return
	 */
	boolean update(DirectoryDto DirectoryDto);

	/**
	 * �޸����ڵ����� ʹ����Ϊ���ڵ� true
	 * 
	 * @param isParent
	 * @return
	 */
	boolean updateIsParent(Long isParent);

	/**
	 * ������ӽڵ�
	 * 
	 * @param DirectoryDto
	 * @return ������ӽڵ�id
	 */
	int addChild(DirectoryDto DirectoryDto);

	/**
	 * �������id �������ȥ��id
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * ɾ�����нڵ�
	 * 
	 * @return true false
	 */
	boolean deleteAllChild(Long id);

	/**
	 * �ж������Ƿ�Ϊ���ڵ�
	 * 
	 * @param id
	 * @return
	 */
	boolean judgeIsParent(Long id);

	/**
	 * ���������ļ���id��ȡ����·��
	 * 
	 * @param id
	 * @return �ļ�·��(String)
	 */
	String allPath(Long id);

	/**
	 * �����ļ���id���ҳ��������ļ��� ǰ��������Ҫ�����ļ���
	 * 
	 * @param id
	 * @return
	 */
	public List<DirectoryDto> selectChildsByParentId(Long id);

	/**
	 * ����ID��ѯĳ�ļ�����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	DirectoryDto selectById(Long id);

	/**
	 * �޸����ڵ����� ʹ����Ϊ���ڵ�
	 * 
	 * @param isParent
	 * @return
	 */
	boolean updateIsNotParent(Long isParent);

	/**
	 * @param �����ͼ
	 * @return
	 */
	boolean addHeadImage(DirectoryDto DirectoryDto);

	/**
	 * Ŀ¼�б��ȡ
	 * 
	 * @return
	 */
	public List<DirectoryDto> selectAllDirForList(DirectoryDto directoryDto);

}
