package org.qianyue.dao;

import java.util.List;

import org.qianyue.bean.User;

public interface UserDao {

	/**
	 * ����User���ҷ��ϵ�User����
	 * 
	 * @param user
	 * @return
	 */
	List<User> select(User user);

	/**
	 * �޸�����
	 * 
	 * @param user
	 * @return
	 */
	int modifyPw(User user);

}
