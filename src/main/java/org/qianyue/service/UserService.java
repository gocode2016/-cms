package org.qianyue.service;

import org.qianyue.dto.ModifyPwDto;
import org.qianyue.dto.UserDto;

public interface UserService {

	/**
	 * 校验用户名/密码是否正确
	 * 
	 * @param userDto
	 *            待校验dto对象
	 * @return true：用户名/密码正确，如果正确，将dto对象里其他属性补齐 false：用户名/密码错误
	 */
	boolean validatie(UserDto userDto);

	/**
	 * 修改密码
	 * 
	 * @param userDto
	 * @return
	 */
	boolean modyfyPw(ModifyPwDto modifyPwDto);

	/**
	 * 根据原密码判断账户是否存在
	 * 
	 * @param userDto
	 * @return
	 */
	boolean JudgePwExist(ModifyPwDto modifyPwDto);

	/**
	 * 根据用户名获得用户对象
	 * 
	 * @param name
	 * @return
	 */
	UserDto findUserByName(String name);
}
