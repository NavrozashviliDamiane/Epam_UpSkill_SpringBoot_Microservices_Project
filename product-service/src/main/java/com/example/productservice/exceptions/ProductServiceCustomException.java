package com.example.productservice.exceptions;

public class ProductServiceCustomException extends RuntimeException {
    public ProductServiceCustomException(String message) {
        super(message);
    }

    public ProductServiceCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
