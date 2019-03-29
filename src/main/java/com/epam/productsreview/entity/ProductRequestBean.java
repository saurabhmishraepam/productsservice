package com.epam.productsreview.entity;

import javax.validation.constraints.Size;

/**
 * Created by saurabh on 26/2/19.
 */
public class ProductRequestBean {
	@Size(min=3, max=20)
	private String name;
	@Size(min=3, max=20)
	private String category;
	private Double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductRequestBean{" + "name='" + name + '\'' + ", category='" + category + '\'' + ", price=" + price
				+ '}';
	}
}
