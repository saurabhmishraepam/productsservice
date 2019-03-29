package com.epam.productsreview.repository;

import com.epam.productsreview.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by saurabh on 26/2/19.
 */
@Repository
public interface ProductsRepository extends CrudRepository<Product, Long>{


}
