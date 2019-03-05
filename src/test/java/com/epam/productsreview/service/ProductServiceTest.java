package com.epam.productsreview.service;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.epam.productsreview.entity.Product;
import com.epam.productsreview.exceptionHadlers.ProductsException;
import com.epam.productsreview.repository.ProductsRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by saurabh on 26/2/19.
 */

@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {

	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductsRepository productsRepository;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void init() {
		// prepare this
	}

	@Test
	public void shouldInsertProducts() {
		Product product = new Product();
		when(productsRepository.save(any(Product.class))).thenReturn(product);
		product.setName("product1");
		product.setCategory("cat1");
		product.setPrice(12.00f);
		assertThat(productService.addProducts(product), is(product));
		verify(productsRepository, times(1)).save(any(Product.class));
	}

	@Test
	public void shouldFailToInsertProductsWhenRepositoryThrowsException() {
		Product product = new Product();
		when(productsRepository.save(any(Product.class))).thenThrow(new ProductsException("Bad input"));
		product.setName("product1");
		product.setCategory("cat1");
		product.setPrice(12.00f);
		exception.expect(ProductsException.class);
		exception.expectMessage("Bad input");
		productService.addProducts(product);
		verify(productsRepository, times(1)).save(any(Product.class));
	}

	@Test
	public void shouldBeSuccessfullForFindAll() {

		when(productsRepository.findAll()).thenReturn(populateMockProducts(20));
		assertTrue(productService.getProducts().size() == 20);
		verify(productsRepository, times(1)).findAll();
	}

	@Test
	public void shouldNotThrowExceptionForEmptyResponse() {
		when(productsRepository.findAll()).thenReturn(new ArrayList<Product>(1));
		assertThat(productService.getProducts().size(), is(0));
		verify(productsRepository, times(1)).findAll();
	}

	@Test
	public void shouldThrowExceptionForRespositoryError() {
		when(productsRepository.findAll()).thenThrow(new ProductsException("empty Response"));
		exception.expect(ProductsException.class);
		exception.expectMessage("empty Response");
		productService.getProducts();
		verify(productsRepository, times(1)).findAll();
	}

	@Test
	public void shouldReturnValidproductForFindById() {
		Product repoResponsePrd = populateMockProducts(1).get(0);
		when(productsRepository.findById(anyLong())).thenReturn(Optional.of(repoResponsePrd));
		Product prd = productService.getProductById(repoResponsePrd.getProductId());
		assertThat(repoResponsePrd, is(prd));
		assertThat(prd.getName(), containsString("Prd0"));
		verify(productsRepository, times(1)).findById(anyLong());
	}

	@Test
	public void shouldThrowExceptionWhenTheRepoisReturningException() {

		when(productsRepository.findById(anyLong())).thenThrow(new ProductsException("not found"));

		exception.expect(ProductsException.class);
		exception.expectMessage("not found");
		productService.getProductById(123L);
		verify(productsRepository, times(1)).findById(anyLong());
	}

	@Test
	public void shouldThrowExceptionForEmptyOptionalFromRepo() {

		when(productsRepository.findById(anyLong())).thenReturn(Optional.empty());

		exception.expect(ProductsException.class);
		exception.expectMessage("Not found productId");
		productService.getProductById(123L);
		verify(productsRepository, times(1)).findById(anyLong());

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
