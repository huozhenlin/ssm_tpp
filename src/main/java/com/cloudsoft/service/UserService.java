package com.cloudsoft.service;

import java.util.List;

import com.cloudsoft.entity.User;


public interface UserService {
	
	public int save(User user);
	
	public User findByUsernameAndPassword(User user);
	
	public User findByNameAndPwd(String username,String password);
	
	public List<User> findAllUsers();
	
	public int deleteByPrimaryKey(Integer id);
	
	public User findById(Integer id);

	public int updateUserById(User user);



}
