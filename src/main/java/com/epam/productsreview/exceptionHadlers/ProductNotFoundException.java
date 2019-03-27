package com.epam.productsreview.exceptionHadlers;

/**
 * Created by saurabh on 27/3/19.
 */
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1778898889L;
    public ProductNotFoundException(String exceptionDetails) {
        super(exceptionDetails);
    }

}
