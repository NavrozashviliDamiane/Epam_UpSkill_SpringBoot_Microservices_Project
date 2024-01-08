package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductSearchAndFilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductSearchAndSortServiceImplTest {

    @InjectMocks
    private ProductSearchAndSortServiceImpl productSearchAndSortService;

    @Mock
    private ProductSearchAndFilterRepository productSearchAndFilterRepository;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        product1 = new Product(1L, "Product 1", "Description 1", 10.0);
        product2 = new Product(2L, "Product 2", "Description 2", 20.0);
    }

    @Test
    public void testSearchByName() {
        when(productSearchAndFilterRepository.findByNameContainingIgnoreCase("product")).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.searchByName("product");

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testSearchByDescription() {
        when(productSearchAndFilterRepository.findByDescriptionContainingIgnoreCase("description")).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.searchByDescription("description");

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testSearchByPriceRange() {
        when(productSearchAndFilterRepository.findByPriceBetween(10.0, 20.0)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.searchByPriceRange(10.0, 20.0);

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testFilterByPriceLessThanOrEqual() {
        when(productSearchAndFilterRepository.findByPriceLessThanEqual(10.0)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.filterByPriceLessThanOrEqual(10.0);

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testFilterByPriceGreaterThanOrEqual() {
        when(productSearchAndFilterRepository.findByPriceGreaterThanEqual(20.0)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.filterByPriceGreaterThanOrEqual(20.0);

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testFindByNameContaining() {
        when(productSearchAndFilterRepository.findByNameContaining("product")).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.findByNameContaining("product");

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testFindByDescriptionContaining() {
        when(productSearchAndFilterRepository.findByDescriptionContaining("description")).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.findByDescriptionContaining("description");

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }

    @Test
    public void testFindProductsFuzzy() {
        when(productSearchAndFilterRepository.findByNameFuzzy("product")).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productSearchAndSortService.findProductsFuzzy("product");

        assertEquals(2, products.size());
        assertEquals(product1, products.get(0));
        assertEquals(product2, products.get(1));
    }
}
