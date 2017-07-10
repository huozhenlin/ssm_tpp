package com.cloudsoft.ServiceImpl;

import java.util.List;

import com.cloudsoft.dao.UserOrderMapper;
import com.cloudsoft.entity.UserOrder;
import com.cloudsoft.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrderServiceImpl implements UserOrderService {
	
	
	@Autowired
	UserOrderMapper orderMapper;

	/**
	 * 添加订单
	 */
	public int save(UserOrder order) {
		
		return orderMapper.insert(order);
	}

	/**
	 * 根据id删除订单
	 */
	public int deleteByPrimaryKey(Integer id) {

		return orderMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 查出所有订单信息
	 */
	public List<UserOrder> findAllOrders() {
		
		return orderMapper.selectAllOrders();
	}



	public List<UserOrder> findByUserId(Integer id) {
		return orderMapper.selectByUserId(id);
	}


}
