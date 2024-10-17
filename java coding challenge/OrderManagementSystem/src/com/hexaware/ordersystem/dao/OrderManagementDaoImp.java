package com.hexaware.ordersystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.ordersystem.entity.Product;
import com.hexaware.ordersystem.entity.User;
import com.hexaware.ordersystem.exceptions.UserNotFound;



public class OrderManagementDaoImp implements IOrderManagementDao{
Connection conn;
	
	public OrderManagementDaoImp()
	{
		conn=DBUtil.getDBConnection();
	}

	@Override
	public List<Product> createOrder(User user,List<Product> products) {
		if (!userExists(user.getUserId())) {
	        createUser(user);
	    }
		int count=0;
		String insert="insert into Orders(userId) values(?)";
		
		return null;
	}

	@Override
	public int cancelOrder(int userId, int orderId) throws UserNotFound{
		if (!userExists(userId)) {
	        throw new UserNotFound(); 
	    }
		 int count = 0;
		    String delete = "DELETE FROM Orders WHERE orderId = ? AND userId = ?";
		    PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(delete);
				pstmt.setInt(1, orderId);
		        pstmt.setInt(2, userId);
		        count = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		return count;
	}
	private boolean userExists(int userId) {
	    String query = "SELECT COUNT(*) FROM User WHERE userId = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	

	@Override
	public int createProduct( Product product) {
		
		int  count=0;
		String insert="insert into Product values(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(insert);
			pstmt.setInt(1,product.getProductId());
			pstmt.setString(2, product.getProductName());
			pstmt.setString(3, product.getDescription());
			pstmt.setDouble(4,product.getPrice());
			pstmt.setInt(5,product.getQuantityInStock());
			pstmt.setString(6,product.getType());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int createUser(User user) {
		int  count=0;
		String insert="insert into User values(?,?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(insert);
			pstmt.setInt(1,user.getUserId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4,user.getRole());
			count=pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<Product>();

		String selectAll = "select * from Product ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(selectAll);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				int productId = rs.getInt("productId");
				String productName = rs.getString("productName");
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int quantityInStock= rs.getInt("quantityInStock");
				String type=rs.getString("type");

				Product product = new Product(productId,productName,description,price,quantityInStock,type);

				list.add(product);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	

	@Override
	public List<Product> getOrderByUser(User user) {
	    List<Product> orderedProducts = new ArrayList<>();
	    String selectOrders = "SELECT o.orderId, o.productId, p.productName, p.price " +
                "FROM Orders o JOIN Product p ON o.productId = p.productId " +
                "WHERE o.userId = ?";
	    try {
			PreparedStatement pstmt = conn.prepareStatement(selectOrders);
			pstmt.setInt(1, user.getUserId());
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int orderId = rs.getInt("orderId");
	            int productId = rs.getInt("productId");
	            String productName = rs.getString("productName");
	            double price = rs.getDouble("price");

	            Product product = new Product(productId, productName, "", price, 0, "");
	            orderedProducts.add(product);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		return orderedProducts;
	    
	
	}}
