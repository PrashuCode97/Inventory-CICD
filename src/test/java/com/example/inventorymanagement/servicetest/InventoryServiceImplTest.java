/**
 * Unit tests for the InventoryServiceImpl class.
 */
package com.example.inventorymanagement.servicetest;

import com.example.inventorymanagement.model.InventoryModel;
import com.example.inventorymanagement.repository.InventoryRepository;
import com.example.inventorymanagement.service.InventoryServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

/**
 * Unit tests for the InventoryServiceImpl class.
 */
public class InventoryServiceImplTest {

    /**
     * Test for the addProduct method of the InventoryServiceImpl class.
     * It verifies that the product is added successfully and returned by the service.
     */
    @Test
    public void testAddProduct() {
        // Given
        // Mock product data
        Calendar cal = new GregorianCalendar();
        cal.set(2000, Calendar.NOVEMBER, 11);
        Date expiryDate = cal.getTime();

        cal.set(2010, Calendar.DECEMBER, 12);
        Date mfgDate = cal.getTime();

        InventoryModel productToAdd = new InventoryModel(1, "New Product", 39.99, 200, "Sample new product",
                expiryDate, mfgDate);

        // Mock the InventoryRepository save method
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.save(productToAdd)).thenReturn(productToAdd);

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        InventoryModel addedProduct = inventoryService.addProduct(productToAdd);

        // Then
        assertNotNull(addedProduct);
        assertEquals(productToAdd, addedProduct);
    }

    /**
     * Test for the getAllProducts method of the InventoryServiceImpl class.
     * It verifies that the service returns all products correctly.
     */
    @Test
    public void testGetAllProducts() {
        // Given
        // Mock product data
        Calendar cal1 = new GregorianCalendar();
        cal1.set(2011, Calendar.NOVEMBER, 11);
        Date expiryDate1 = cal1.getTime();

        cal1.set(2020, Calendar.DECEMBER, 12);
        Date mfgDate1 = cal1.getTime();

        Calendar cal2 = new GregorianCalendar();
        cal2.set(1999, Calendar.DECEMBER, 12);
        Date expiryDate2 = cal2.getTime();

        cal2.set(1980, Calendar.JANUARY, 4);
        Date mfgDate2 = cal2.getTime();

        List<InventoryModel> products = new ArrayList<>();
        products.add(new InventoryModel(1, "Product 1", 19.99, 100, "Sample product 1", expiryDate1, mfgDate1));
        products.add(new InventoryModel(2, "Product 2", 29.99, 50, "Sample product 2", expiryDate2, mfgDate2));

        // Mock the InventoryRepository findAll method
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findAll()).thenReturn(products);

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        List<InventoryModel> resultProducts = inventoryService.getAllProducts();

        // Then
        assertNotNull(resultProducts);
        assertEquals(products, resultProducts);
    }


    /**
     * Test for the getProductById method of the InventoryServiceImpl class.
     * It verifies that the service returns the correct product for a given ID.
     */
    @Test
    public void testGetProductById() {
        // Given
        Integer productId = 1;

        Calendar cal1 = new GregorianCalendar();
        cal1.set(2011, Calendar.NOVEMBER, 11);
        Date expiryDate = cal1.getTime();

        cal1.set(2020, Calendar.DECEMBER, 12);
        Date mfgDate = cal1.getTime();

        InventoryModel expectedProduct = new InventoryModel(1, "Product 1", 19.99, 100, "Sample product 1",
                expiryDate, mfgDate);

        // Mock the InventoryRepository findById method
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        InventoryModel resultProduct = inventoryService.getProductById(productId);

        // Then
        assertNotNull(resultProduct);
        assertEquals(expectedProduct, resultProduct);
    }


    /**
     * Test for the getProductById method of the InventoryServiceImpl class.
     * It verifies that the service returns null when the product is not found for a given ID.
     */
    @Test
    public void testGetProductById_NotFound() {
        // Given
        Integer productId = 100;

        // Mock the InventoryRepository findById method to return an empty Optional
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findById(productId)).thenReturn(Optional.empty());

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        InventoryModel resultProduct = inventoryService.getProductById(productId);

        // Then
        assertNull(resultProduct);
    }

    /**
     * Test for the updateProduct method of the InventoryServiceImpl class.
     * It verifies that the product is updated successfully and returned by the service.
     */
    @Test
    public void testUpdateProduct() {
        // Given
        Integer productId = 2;


        Calendar cal1 = new GregorianCalendar();
        cal1.set(1990, Calendar.JULY, 9);
        Date expiryDate = cal1.getTime();

        cal1.set(2000, Calendar.NOVEMBER, 12);
        Date mfgDate = cal1.getTime();

        InventoryModel productToUpdate = new InventoryModel(2, "Product 2 Updated", 39.99, 200, "Sample product 2 updated",expiryDate,mfgDate);

        productToUpdate.setProductExpiry(expiryDate);
        productToUpdate.setProductMfgDate(mfgDate);

        // Mock the InventoryRepository findById and save methods
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findById(productId)).thenReturn(Optional.of(productToUpdate));
        when(inventoryRepository.save(productToUpdate)).thenReturn(productToUpdate);

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        InventoryModel updatedProduct = inventoryService.updateProduct(productId, productToUpdate);

        // Then
        assertNotNull(updatedProduct);
        assertEquals(productToUpdate, updatedProduct);
    }


    /**
     * Test for the updateProduct method of the InventoryServiceImpl class.
     * It verifies that the service returns null when trying to update a non-existing product.
     */
    @Test
    public void testUpdateProduct_NotFound() {
        // Given
        Integer productId = 100;
        InventoryModel productToUpdate = new InventoryModel(100, "Product Not Found", 0.0, 0, "", new Date(), new Date());

        // Mock the InventoryRepository findById method to return an empty Optional
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findById(productId)).thenReturn(Optional.empty());

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        InventoryModel updatedProduct = inventoryService.updateProduct(productId, productToUpdate);

        // Then
        assertNull(updatedProduct);
    }

    /**
     * Test for the deleteProduct method of the InventoryServiceImpl class.
     * It verifies that the service deletes the product successfully and returns true.
     */
    @Test
    public void testDeleteProduct() {
        // Given
        Integer productId = 2;
        InventoryModel productToDelete = new InventoryModel(2, "Product 2", 29.99, 50, "Sample product 2",
                new Date(), new Date());

        // Mock the InventoryRepository findById method
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findById(productId)).thenReturn(Optional.of(productToDelete));

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        boolean isDeleted = inventoryService.deleteProduct(productId);

        // Then
        assertTrue(isDeleted);
        verify(inventoryRepository, times(1)).delete(productToDelete);
    }

    /**
     * Test for the deleteProduct method of the InventoryServiceImpl class.
     * It verifies that the service returns false when trying to delete a non-existing product.
     */
    @Test
    public void testDeleteProduct_NotFound() {
        // Given
        Integer productId = 100;

        // Mock the InventoryRepository findById method to return an empty Optional
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        when(inventoryRepository.findById(productId)).thenReturn(Optional.empty());

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository);

        // When
        boolean isDeleted = inventoryService.deleteProduct(productId);

        // Then
        assertFalse(isDeleted);
        verify(inventoryRepository, never()).delete(any());
    }
}
