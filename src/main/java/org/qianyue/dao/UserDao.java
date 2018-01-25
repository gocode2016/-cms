package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.User;

public interface UserDao {

	/**
	 * 根据User查找符合的User集合
	 * 
	 * @param user
	 * @return
	 */
	List<User> select(User user);

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	int modifyPw(User user);

}
