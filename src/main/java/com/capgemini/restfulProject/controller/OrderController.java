package com.capgemini.restfulProject.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.restfulProject.entity.LineItem;
import com.capgemini.restfulProject.entity.Order;
import com.capgemini.restfulProject.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	private HashMap<Integer, Set<LineItem>> cartLineItems;

	public OrderController() {
		cartLineItems = new HashMap<>();
	}

	@PostMapping("/cart/{customerId}")
	public ResponseEntity<Set<LineItem>> addToCart(@RequestBody LineItem LineItem, @PathVariable int customerId) {
		Set<LineItem> sampleLineItem = cartLineItems.get(customerId);
		if (sampleLineItem == null) {
			sampleLineItem = new HashSet<>();
			sampleLineItem.add(LineItem);
			cartLineItems.put(customerId, sampleLineItem);
		} else {
			sampleLineItem.add(LineItem);
			cartLineItems.put(customerId, sampleLineItem);
		}
		return new ResponseEntity<Set<LineItem>>(sampleLineItem, HttpStatus.OK);
	}

	@GetMapping("/cart/{customerId}")
	public ResponseEntity<Set<LineItem>> getCartLineItems(@PathVariable int customerId) {
		Set<LineItem> sampleLineItem = cartLineItems.get(customerId);
		return new ResponseEntity<Set<LineItem>>(sampleLineItem, HttpStatus.OK);
	}

	@DeleteMapping("/cart/{customerId}")
	public ResponseEntity<Set<LineItem>> deleteCartLineItems(@PathVariable int customerId, @RequestBody LineItem LineItem) {
		Set<LineItem> sampleLineItem = cartLineItems.get(customerId);
		if (sampleLineItem != null) {
			sampleLineItem.remove(LineItem);
			cartLineItems.put(customerId, sampleLineItem);
		}
		return new ResponseEntity<Set<LineItem>>(sampleLineItem, HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.submitOrder(order), HttpStatus.OK);
	}

	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.updateOrder(order), HttpStatus.OK);
	}

	@DeleteMapping("/order/del/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId,@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.deleteOrder(orderId,order),HttpStatus.OK);
	}

	@PutMapping("/order/{orderId}")
	public ResponseEntity<Order> cancelOrder(@PathVariable int orderId,@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.cancelOrder(orderId,order), HttpStatus.OK);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
		return new ResponseEntity<Order>(orderService.getOrderByOrderId(orderId), HttpStatus.OK);

	}

	@GetMapping("/allorders")
	public ResponseEntity<List<Order>> getOrders() {
		return new ResponseEntity<List<Order>>(orderService.getOrders(),HttpStatus.OK);

	}
	
	@GetMapping("/orders/{customerId}")
	public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable int customerId) {
		return new ResponseEntity<List<Order>>(orderService.getOrderByCustomerId(customerId),HttpStatus.OK);

	}
	
}