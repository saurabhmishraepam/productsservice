package com.epam.productsreview.restController;

import com.epam.productsreview.entity.Product;
import com.epam.productsreview.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by saurabh on 28/2/19.
 */


@RunWith(SpringRunner.class)
@WebMvcTest(ProductsController.class)
public class ProductsControllerIntegrationTest {

    String productControllerEndpoint = "/product/";
    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mvcMock;

    @Test
    public void shouldReturnStatusOkForAddAPI() throws Exception {
        Product prd=populateMockProducts(1).get(0);
        prd= productService.addProducts(prd);
        mvcMock.perform(get(productControllerEndpoint + "1"))
                .andExpect(status().isOk()).andDo(res->{
            System.out.println(res.getResponse().getContentType());
        });


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
