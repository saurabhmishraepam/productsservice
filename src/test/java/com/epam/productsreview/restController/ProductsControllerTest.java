package com.epam.productsreview.restController;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.epam.productsreview.entity.Product;
import com.epam.productsreview.entity.ProductRequestBean;
import com.epam.productsreview.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;

/**
 * Created by saurabh on 26/2/19.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductsControllerTest {

	@InjectMocks
	private ProductsController productsController;

	@Mock
	private ProductService productService;

	@Before
	public void init() {
		// todo prepare data

	}

	@Test
	public void shouldReturnValidResponseForAddProductsRequest() {
		Product prd = populateMockProducts(1).get(0);
		ProductRequestBean prdBean = new ProductRequestBean();
		prdBean.setName(prd.getName());
		prdBean.setCategory(prd.getCategory());
		prdBean.setPrice((double) prd.getPrice());

		when(productService.addProducts(any(Product.class))).thenReturn(prd);
		productsController.saveProducts(prdBean);
		verify(productService, timeout(1)).addProducts(any(Product.class));

	}
	
	@Test
	public void shouldReturnErrorForBadRequest() {

		Product prd = populateMockProducts(1).get(0);
		ProductRequestBean prdBean = new ProductRequestBean();
		//prdBean.setName(prd.getName());
		//prdBean.setCategory(prd.getCategory());
		//prdBean.setPrice((double) prd.getPrice());

		when(productService.addProducts(any(Product.class))).thenReturn(prd);
		productsController.saveProducts(prdBean);
		verify(productService, timeout(1)).addProducts(any(Product.class));

	}

	private List<Product> populateMockProducts(int count) {
		Random rnd = new Random(140);// seed value
		List<Product> products = new ArrayList<>(count);
		for (int counter = 0; counter < count; counter++) {

			Product prd = new Product();
			prd.setProductId((long) rnd.nextInt(40000));
			prd.setName("Prd" + counter);
			prd.setCategory("Category" + counter);
			prd.setPrice(rnd.nextFloat());
			products.add(prd);
		}

		return products;
	}
}
