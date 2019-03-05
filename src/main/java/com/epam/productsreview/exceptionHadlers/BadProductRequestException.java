package com.epam.productsreview.exceptionHadlers;

public class BadProductRequestException extends RuntimeException{
	private static final long serialVersionUID = 1778898889L;
	public BadProductRequestException(String exceptionDetails) {
		super(exceptionDetails);
	}

}
