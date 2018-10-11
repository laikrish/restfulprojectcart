package com.capgemini.restfulProject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.capgemini.restfulProject.entity.LineItem;
import com.capgemini.restfulProject.entity.Order;


public interface OrderService {

	public Order submitOrder(Order order);

	public Order updateOrder(Order order);

	public Order cancelOrder(int orderId,Order order);

	public Order deleteOrder(int orderId,Order order);

	public Order getOrderByOrderId(int orderId);

	public List<Order> getOrderByCustomerId(int customerId);

	public List<Order> getOrderByDate(LocalDate date);

	public List<Order> getOrders();
}