package com.hexaware.ordersystem.service;

import java.util.List;

import com.hexaware.ordersystem.entity.Product;
import com.hexaware.ordersystem.entity.User;
import com.hexaware.ordersystem.exceptions.UserNotFound;

public interface IOrderManagementService {
	List<Product>  createOrder(User user,List<Product> products);
	int cancelOrder(int userId, int orderId) throws UserNotFound; //throws exception
	int createProduct( Product product);
	int createUser(User user);
	List<Product> getAllProducts();
	 List<Product> getOrderByUser(User user) ;

}
