package org.qianyue.service;

import java.util.List;

import org.qianyue.bean.Information;
import org.qianyue.dto.InformationDto;

public interface InformationService {

	/**
	 * �������� �б���Ѷ
	 * 
	 * @return
	 */
	List<InformationDto> select(InformationDto informationDto);

	/**
	 * ���һ������Ѷ
	 * 
	 * @param Information
	 * @return
	 */
	boolean add(InformationDto informationDto, String fileNameUrl);

	/**
	 * �������id �������ȥ��id
	 * 
	 * @return
	 */
	int selectMaxId();

	/**
	 * �������ڵ�
	 * 
	 * @param DirectoryDto
	 * @return
	 */
	boolean update(Information Information);

	/**
	 * ����idɾ��һƪ����
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);

	/**
	 * ����id��ѯ����
	 * 
	 * @param id
	 * @return
	 */
	InformationDto selectById(Long id);

}
