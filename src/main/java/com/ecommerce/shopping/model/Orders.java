package com.ecommerce.shopping.model;

import java.io.Serializable;

public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5472597255640108523L;

	Long id;

	String productName;

	int quantity;

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String getProductName() {
		return productName;
	}

	private void setProductName(String productName) {
		this.productName = productName;
	}

	private int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", productName=" + productName + ", quantity=" + quantity + "]";
	}

}
