package com.hexaware.ordersystem.service;

import java.util.List;

import com.hexaware.ordersystem.dao.IOrderManagementDao;
import com.hexaware.ordersystem.dao.OrderManagementDaoImp;
import com.hexaware.ordersystem.entity.Product;
import com.hexaware.ordersystem.entity.User;
import com.hexaware.ordersystem.exceptions.UserNotFound;

public class OrderManagementServiceImp implements IOrderManagementService{
	private IOrderManagementDao dao;
	public OrderManagementServiceImp()
	{
		dao=new OrderManagementDaoImp();
		
	}
	@Override
	public List<Product> createOrder(User user,List<Product> products) {
		
		return dao.createOrder(user, products);
	}
	
	@Override
	public int cancelOrder(int userId, int orderId) throws UserNotFound {
		return dao.cancelOrder(userId, orderId);
		
	}
	@Override
	public int createProduct(Product product) {
		return dao.createProduct( product);
	}
	@Override
	public int createUser(User user) {
		return dao.createUser(user);
	}
	@Override
	public List<Product> getAllProducts() {
		return dao.getAllProducts();
	}
	@Override
	public List<Product> getOrderByUser(User user) {
	    return dao.getOrderByUser(user);
	}
	
	public static boolean vaildateAddUser(User user) {
boolean flag=false;
		
		if(user.getUserId()>0 && user.getUsername().length()>3 && user.getPassword().length()>3 && user.getRole().length()>3 ) {
			
			flag=true;
		}
		return flag;
		
	}
	
	public static boolean vaildateAddProduct(Product product) {
		boolean flag=false;
				
				if(product.getProductId()>0 && product.getProductName().length()>3 && product.getDescription().length()>3 ) {
					
					flag=true;
				}
				return flag;
				
			}

}
