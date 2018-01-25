package org.qianyue.service.serviceImpl;

import java.util.List;

import org.qianyue.Util.CommonUtil;
import org.qianyue.bean.User;
import org.qianyue.dao.UserDao;
import org.qianyue.dto.ModifyPwDto;
import org.qianyue.dto.UserDto;
import org.qianyue.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public boolean validatie(UserDto userDto) {
		if (userDto != null && !CommonUtil.isEmpty(userDto.getName()) && !CommonUtil.isEmpty(userDto.getPassword())) {
			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			List<User> list = userDao.select(user);
			if (list.size() == 1) {
				BeanUtils.copyProperties(list.get(0), userDto);
				return true;
			}
			return false;
		} else {
			return false;
		}

	}

	public boolean modyfyPw(ModifyPwDto modifyPwDto) {
		if (modifyPwDto != null && !CommonUtil.isEmpty(modifyPwDto.getOldPassword())
				&& !CommonUtil.isEmpty(modifyPwDto.getNewPassword())
				&& !CommonUtil.isEmpty(modifyPwDto.getNewPasswordAgain())) {
			User user = new User();
			user.setPassword(modifyPwDto.getOldPassword());
			List<User> list = userDao.select(user);
			if (list.size() == 1) {
				BeanUtils.copyProperties(list.get(0), user);
				user.setPassword(modifyPwDto.getNewPassword());
				if (userDao.modifyPw(user) == 1) {
					return true;
				} else {
					return false;
				}

			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public boolean JudgePwExist(ModifyPwDto modifyPwDto) {
		User user = new User();
		user.setPassword(modifyPwDto.getOldPassword());
		List<User> list = userDao.select(user);
		if (list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public UserDto findUserByName(String name) {
		User user = new User();
		UserDto userDto = new UserDto();
		user.setName(name);
		List<User> list = userDao.select(user);
		BeanUtils.copyProperties(list.get(0), userDto);
		return userDto;
	}

}
