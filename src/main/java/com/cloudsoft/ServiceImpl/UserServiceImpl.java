package com.cloudsoft.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsoft.dao.UserMapper;
import com.cloudsoft.entity.User;
import com.cloudsoft.service.UserService;

import javax.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	
	/**
	 * 插入数据
	 */
	public int save(User user) {
		
		return userMapper.insert(user);
		
	}
	
	/**
	 * 根据用户名和密码查找用户信息
	 */
	public User findByUsernameAndPassword(User user) {
		
		return userMapper.selectByUsernameAndPassword(user);
	}

	/**
	 * 根据用户名和密码查找用户信息
	 */
	public User findByNameAndPwd(String username, String password) {
		
		return userMapper.selectByNameAndPwd(username, password);
	}

	/**
	 * 查出所有用户信息
	 */
	public List<User> findAllUsers() {
		
		return userMapper.selectAllUsers();
	}

	/**
	 * 根据id删除用户信息
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id){
		
		return userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取用户信息
	 */
	public User findById(Integer id) {
		System.out.println(id);
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据id修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUserById(User user) {
		return userMapper.updateUserById(user);
	}


}
