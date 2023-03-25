package com.ecommerce.shopping.service;

import java.util.List;

import com.ecommerce.shopping.model.Orders;

public interface OrderService {

	public List<Orders> getAllOrders();

	public Orders getOrderDetails(Long id);

	public String placeOrder(Orders orders);

}
