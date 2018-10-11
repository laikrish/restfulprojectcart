package com.capgemini.restfulProject.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.restfulProject.entity.LineItem;
import com.capgemini.restfulProject.entity.Order;
import com.capgemini.restfulProject.repository.OrderRepository;
import com.capgemini.restfulProject.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order submitOrder(Order order) {
		order.setStatus("Processing");
		/*Set<LineItem> cartItems = order.getItems();
		double totalPrice = 0;
		for (LineItem item : cartItems) {
			totalPrice += item.getPrice();
		}
		order.setTotal(totalPrice);
		order.setDate(LocalDate.now());*/
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		Optional<Order> optional = orderRepository.findById(order.getOrderId());
		if (optional.isPresent()) {
			optional.get().setStatus("Completed");
			return orderRepository.save(optional.get());
		}
		return null;
	}

	@Override
	public Order cancelOrder(int orderId,Order order) {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			optional.get().setStatus("Cancelled");
			return orderRepository.save(optional.get());
		}
		return null;
	}

	@Override
	public Order deleteOrder(int orderId,Order order) {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			optional.get().setStatus("Deleted");
			 orderRepository.delete(optional.get());
			
		}
	return null;
	}
	

	@Override
	public Order getOrderByOrderId(int orderId) {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Order> getOrderByCustomerId(int customerId) {
		return orderRepository.findByCustomerId(customerId);
	}

	@Override
	public List<Order> getOrderByDate(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

}