package org.qianyue.service;

import org.qianyue.dto.ModifyPwDto;
import org.qianyue.dto.UserDto;

public interface UserService {

	/**
	 * У���û���/�����Ƿ���ȷ
	 * 
	 * @param userDto
	 *            ��У��dto����
	 * @return true���û���/������ȷ�������ȷ����dto�������������Բ��� false���û���/�������
	 */
	boolean validatie(UserDto userDto);

	/**
	 * �޸�����
	 * 
	 * @param userDto
	 * @return
	 */
	boolean modyfyPw(ModifyPwDto modifyPwDto);

	/**
	 * ����ԭ�����ж��˻��Ƿ����
	 * 
	 * @param userDto
	 * @return
	 */
	boolean JudgePwExist(ModifyPwDto modifyPwDto);

	/**
	 * �����û�������û�����
	 * 
	 * @param name
	 * @return
	 */
	UserDto findUserByName(String name);
}
