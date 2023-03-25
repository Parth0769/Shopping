package com.ecommerce.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.shopping.model.Orders;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderService service;

	@Override
	public List<Orders> getAllOrders() {
		Orders order = new Orders();

		List<Orders> orders = new ArrayList<>();
		orders.add(order);
		return orders;
	}

	@Override
	public Orders getOrderDetails(Long id) {
		Orders order = new Orders();
		return order;
	}

	@Override
	public String placeOrder(Orders orders) {
		return "Order Placed Successfully";
	}

}
