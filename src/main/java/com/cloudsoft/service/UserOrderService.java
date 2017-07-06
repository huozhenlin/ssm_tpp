package com.cloudsoft.service;

import java.util.List;

import com.cloudsoft.entity.UserOrder;

public interface UserOrderService {
	
	public int save(UserOrder order);
	
	public int deleteByPrimaryKey(Integer id);
	
	public List<UserOrder> findAllOrders();
	
	public List<UserOrder> findByUserId(Integer id);
}
