package com.capgemini.restfulProject.service;

import java.util.List;

import com.capgemini.restfulProject.entity.LineItem;

public interface LineItemService {
	
	
	public LineItem addLineItem(LineItem lineItem,int itemId);
	
	public void removeLineItem(LineItem lineItem,int itemId);
	
	public List<LineItem> getLineItems(int itemId);
	

}
