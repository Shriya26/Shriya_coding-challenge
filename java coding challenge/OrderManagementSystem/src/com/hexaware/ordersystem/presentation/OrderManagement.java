package com.hexaware.ordersystem.presentation;

import java.util.List;
import java.util.Scanner;

import com.hexaware.ordersystem.entity.Product;
import com.hexaware.ordersystem.entity.User;
import com.hexaware.ordersystem.exceptions.UserNotFound;
import com.hexaware.ordersystem.service.IOrderManagementService;
import com.hexaware.ordersystem.service.OrderManagementServiceImp;

public class OrderManagement {
	static Scanner scanner=new Scanner(System.in);

	public static void main(String[] args) {
		boolean flag=true;
		IOrderManagementService service=new OrderManagementServiceImp();
		while(flag) {
			System.out.println("Welcome to Order Management System");
			System.out.println("1.Create Order:");
			System.out.println("2.Cancel Order:");
			System.out.println("3.Create Product:");
			System.out.println("4.Create User:");
			System.out.println("5.Get Products:");
			System.out.println("6.Get Order By User:");
			
			System.out.println("0.Exit");
			
			int choice=scanner.nextInt();
			switch(choice) {
			case 1:
				break;
			case 2:
				System.out.print("Enter User ID : ");
		        int userId = scanner.nextInt();
		        System.out.print("Enter Order ID : ");
		        int orderId = scanner.nextInt();

		        try {
		            int rowsAffected = service.cancelOrder(userId, orderId);
		            if (rowsAffected > 0) {
		                System.out.println("Order canceled successfully.");
		            } else {
		                System.out.println("Order cancellation failed. No such order found.");
		            }
		        } catch (UserNotFound e) {
		            System.out.println("User not found. Cannot cancel order.");
		        }
				break;
			case 3:
				Product product=readProduct();
				boolean isValid3=OrderManagementServiceImp.vaildateAddProduct(product);
				if(isValid3) {
					int count = service.createProduct(product);
					if(count>0) {
						System.out.println("Product added");
					}
					else {
						System.out.println("Product Add failed");
					}
				}else{
						System.out.println("Invalid Product");
					}
				break;
			case 4:
				User user=readUser();
				boolean isValid4=OrderManagementServiceImp.vaildateAddUser(user);
				if(isValid4) {
					int count = service.createUser(user);
					if(count>0) {
						System.out.println("User added");
					}
					else {
						System.out.println("User Add failed");
					}
				}else{
						System.out.println("Invalid user");
					}
				
				break;
				
			case 5:
				List<Product> list = service.getAllProducts();

				for (Product product1 : list) {

					System.out.println(product1);

				}

				break;
			case 6:
				System.out.println("Enter User ID :");
			    int userIdForOrders = scanner.nextInt();
			    
			    User orderUser = new User();
			    orderUser.setUserId(userIdForOrders);

			    List<Product> orderedProducts = service.getOrderByUser(orderUser);
			    
			    if (!orderedProducts.isEmpty()) {
			        System.out.println("Ordered Products for User ID " + userIdForOrders + ":");
			        for (Product product2 : orderedProducts) {
			            System.out.println(product2); 
			        }
			    } else {
			        System.out.println("No products ordered for User ID " + userIdForOrders);
			    }
				break;
			case 0:
				flag=false;
				System.out.println("Thankyou");
				default:
					break;
			}}			

	}
	
	public static User readUser() {
		User user=new User();
		System.out.println("Enter UserID=");
		user.setUserId(scanner.nextInt());
		System.out.println("Enter  User Name=");
		user.setUsername(scanner.next());
		System.out.println("Enter Password=");
		user.setPassword(scanner.next());
		System.out.println("Enter Role=");
		user.setRole(scanner.next());
		
		return user;
	}
	
	public static Product readProduct() {
		Product product =new Product();
		System.out.println("Enter productID=");
		product.setProductId(scanner.nextInt());
		System.out.println("Enter  Product Name=");
		product.setProductName(scanner.next());
		System.out.println("Enter description=");
		product.setDescription(scanner.next());
		System.out.println("Enter price=");
		product.setPrice(scanner.nextDouble());
		System.out.println("Enter quantity in stock=");
		product.setQuantityInStock(scanner.nextInt());
		System.out.println("Enter type=");
		product.setType(scanner.next());
		
		return product;
	}
		
		
}


