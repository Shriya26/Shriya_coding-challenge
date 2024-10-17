package com.hexaware.ordersystem.entity;

public class Orders {
	private int orderId;
    private int userId;
    private int productId;

    public Orders(int orderId, int userId, int productId) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

}
