package com.example.inventorymanagement.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.inventorymanagement.model.InventoryModel;
import com.example.inventorymanagement.service.InventoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * Unit tests for the InventoryController class.
 */
@RunWith(MockitoJUnitRunner.class)
public class InventoryControllerTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    private List<InventoryModel> products;

    @Before
    public void setUp() {
        // Create sample products for testing
        products = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 10, 11); // Note: Month is zero-based
        Date expiryDate1 = calendar.getTime();
        calendar.set(2010, 10, 11);
        Date mfgDate1 = calendar.getTime();

        calendar.set(2001, 11, 12);
        Date expiryDate2 = calendar.getTime();
        calendar.set(2011, 11, 12);
        Date mfgDate2 = calendar.getTime();

        products.add(new InventoryModel(1, "Product 1", 19.99, 100, "Sample product 1", expiryDate1, mfgDate1));
        products.add(new InventoryModel(2, "Product 2", 29.99, 50, "Sample product 2", expiryDate2, mfgDate2));
    }

    /**
     * Test for the addProduct method in the InventoryController.
     * It mocks the addProduct method in the service and verifies that the response is as expected.
     */
    @Test
    public void testAddProduct() {
        // Given
        Calendar calendar = Calendar.getInstance();

        // Set the year, month (0-based), and day for expiry date
        calendar.set(2012, Calendar.DECEMBER, 12);
        Date expiryDate = calendar.getTime();

        // Set the year, month (0-based), and day for manufacturing date
        calendar.set(2022, Calendar.DECEMBER, 12);
        Date mfgDate = calendar.getTime();

        InventoryModel productToAdd = new InventoryModel(3, "New Product", 39.99, 200, "Sample new product",
                expiryDate, mfgDate);

        // Mock the addProduct method in the service
        when(inventoryService.addProduct(productToAdd)).thenReturn(productToAdd);

        // When
        ResponseEntity<InventoryModel> response = inventoryController.addProduct(productToAdd);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productToAdd, response.getBody());
    }

    /**
     * Test for the getAllProducts method in the InventoryController.
     * It mocks the getAllProducts method in the service and verifies that the response is as expected.
     */
    @Test
    public void testGetAllProducts() {
        // Mock the getAllProducts method in the service
        when(inventoryService.getAllProducts()).thenReturn(products);

        // When
        ResponseEntity<List<InventoryModel>> response = inventoryController.getAllProducts();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    /**
     * Test for the getProductById method in the InventoryController.
     * It mocks the getProductById method in the service and verifies that the response is as expected.
     */
    @Test
    public void testGetProductById() {
        // Given
        int productId = 1;
        InventoryModel expectedProduct = products.get(0);

        // Mock the getProductById method in the service
        when(inventoryService.getProductById(productId)).thenReturn(expectedProduct);

        // When
        ResponseEntity<InventoryModel> response = inventoryController.getProductById(productId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProduct, response.getBody());
    }

    /**
     * Test for the getProductById method in the InventoryController when the product is not found.
     * It mocks the getProductById method in the service to return null and verifies that the response is NOT_FOUND.
     */
    @Test
    public void testGetProductById_NotFound() {
        // Given
        int productId = 100;

        // Mock the getProductById method in the service to return null
        when(inventoryService.getProductById(productId)).thenReturn(null);

        // When
        ResponseEntity<InventoryModel> response = inventoryController.getProductById(productId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test for the updateProduct method in the InventoryController.
     * It mocks the updateProduct method in the service and verifies that the response is as expected.
     */
    @Test
    public void testUpdateProduct() {
        // Given
        int productId = 2;
        InventoryModel productToUpdate = products.get(1);

        // Mock the updateProduct method in the service
        when(inventoryService.updateProduct(productId, productToUpdate)).thenReturn(productToUpdate);

        // When
        ResponseEntity<InventoryModel> response = inventoryController.updateProduct(productId, productToUpdate);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productToUpdate, response.getBody());
    }

    /**
     * Test for the updateProduct method in the InventoryController when the product is not found.
     * It mocks the updateProduct method in the service to return null and verifies that the response is NOT_FOUND.
     */
    @Test
    public void testUpdateProduct_NotFound() {
        // Given
        int productId = 100;
        InventoryModel productToUpdate = new InventoryModel(100, "Product Not Found", 0.0, 0, "",
                new Date(), new Date());

        // Mock the updateProduct method in the service to return null
        when(inventoryService.updateProduct(productId, productToUpdate)).thenReturn(null);

        // When
        ResponseEntity<InventoryModel> response = inventoryController.updateProduct(productId, productToUpdate);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test for the deleteProduct method in the InventoryController.
     * It mocks the deleteProduct method in the service and verifies that the response is as expected.
     */
    @Test
    public void testDeleteProduct() {
        // Given
        int productId = 2;

        // Mock the deleteProduct method in the service
        when(inventoryService.deleteProduct(productId)).thenReturn(true);

        // When
        ResponseEntity<Void> response = inventoryController.deleteProduct(productId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test for the deleteProduct method in the InventoryController when the product is not found.
     * It mocks the deleteProduct method in the service to return false and verifies that the response is NOT_FOUND.
     */
    @Test
    public void testDeleteProduct_NotFound() {
        // Given
        int productId = 100;

        // Mock the deleteProduct method in the service to return false
        when(inventoryService.deleteProduct(productId)).thenReturn(false);

        // When
        ResponseEntity<Void> response = inventoryController.deleteProduct(productId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
