package com.cloudsoft.service;

import com.cloudsoft.entity.UserOrder;

import java.util.List;

public interface UserOrderService {
	
	public int save(UserOrder order);
	
	public int deleteByPrimaryKey(Integer id);
	
	public List<UserOrder> findAllOrders();
	
	public List<UserOrder> findByUserId(Integer id);
}
