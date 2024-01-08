package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exceptions.ProductServiceCustomException;
import com.example.productservice.repository.ProductRepository;
import com.google.cloud.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Storage storage;

    private Product product;
    private List<Product> products;

    @BeforeEach
    public void setUp() {
        product = new Product(1L, "Product1", "Description1", "dsadsasadadsaddasdsa", 10.1, 10);
        products = new ArrayList<>();
        products.add(product);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> returnedProducts = productService.getAllProducts();

        assertEquals(1, returnedProducts.size());
        assertEquals(product, returnedProducts.get(0));
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> returnedProduct = productService.getProductById(1L);

        assertTrue(returnedProduct.isPresent());
        assertEquals(product, returnedProduct.get());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateProduct() {
        Product updatedProduct = new Product(1L, "UpdatedProduct1", "UpdatedDescription1", "dsadsasadadsaddasdsa", 10.1, 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.updateProduct(updatedProduct);

        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    public void testReduceQuantity() {
        try {
            productService.reduceQuantity(1L, 5);

            verify(productRepository, times(1)).findById(1L);
            verify(productRepository, times(1)).save(product);
        } catch (ProductServiceCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReduceQuantity_productNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductServiceCustomException.class, () -> productService.reduceQuantity(1L, 5));
    }

    @Test
    public void testReduceQuantity_insufficientQuantity() {
        product.setQuantity(2);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(ProductServiceCustomException.class, () -> productService.reduceQuantity(1L, 5));
    }
}
