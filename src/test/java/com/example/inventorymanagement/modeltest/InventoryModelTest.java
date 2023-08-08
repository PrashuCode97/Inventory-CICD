/**
 * Unit tests for the InventoryModel class.
 */
package com.example.inventorymanagement.modeltest;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Calendar;
import java.util.Date;

import com.example.inventorymanagement.model.InventoryModel;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link InventoryModel}.
 */
public class InventoryModelTest {

    /**
     * Test for the getters and setters of the {@link InventoryModel} class.
     * It creates an InventoryModel object using the setters and verifies that the getters return the expected values.
     */
    @Test
    public void testGettersAndSetters() {
        // Given
        Integer productSerialNumber = 1;
        String productName = "Test Product";
        Double productPrice = 19.99;
        Integer productQuantity = 100;
        String productDescription = "Sample product";

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.NOVEMBER, 11);
        Date productExpiry = calendar.getTime();

        calendar.set(2010, Calendar.NOVEMBER, 11);
        Date productMfgDate = calendar.getTime();

        // When
        InventoryModel inventoryModel = new InventoryModel();
        inventoryModel.setProductSerialNumber(productSerialNumber);
        inventoryModel.setProductName(productName);
        inventoryModel.setProductPrice(productPrice);
        inventoryModel.setProductQuantity(productQuantity);
        inventoryModel.setProductDescription(productDescription);
        inventoryModel.setProductExpiry(productExpiry);
        inventoryModel.setProductMfgDate(productMfgDate);

        // Then
        assertEquals(productSerialNumber, inventoryModel.getProductSerialNumber());
        assertEquals(productName, inventoryModel.getProductName());
        assertEquals(productPrice, inventoryModel.getProductPrice());
        assertEquals(productQuantity, inventoryModel.getProductQuantity());
        assertEquals(productDescription, inventoryModel.getProductDescription());
        assertEquals(productExpiry, inventoryModel.getProductExpiry());
        assertEquals(productMfgDate, inventoryModel.getProductMfgDate());
    }

    /**
     * Test for the constructor of the {@link InventoryModel} class.
     * It creates an InventoryModel object using the constructor and verifies that the getters return the expected values.
     */
    @Test
    public void testConstructor() {
        // Given
        Integer productSerialNumber = 1;
        String productName = "Test Product";
        Double productPrice = 19.99;
        Integer productQuantity = 100;
        String productDescription = "Sample product";

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.NOVEMBER, 11);
        Date productExpiry = calendar.getTime();

        calendar.set(2010, Calendar.NOVEMBER, 11);
        Date productMfgDate = calendar.getTime();

        // When
        InventoryModel inventoryModel = new InventoryModel(productSerialNumber, productName, productPrice, productQuantity, productDescription, productExpiry, productMfgDate);

        // Then
        assertEquals(productSerialNumber, inventoryModel.getProductSerialNumber());
        assertEquals(productName, inventoryModel.getProductName());
        assertEquals(productPrice, inventoryModel.getProductPrice());
        assertEquals(productQuantity, inventoryModel.getProductQuantity());
        assertEquals(productDescription, inventoryModel.getProductDescription());
        assertEquals(productExpiry, inventoryModel.getProductExpiry());
        assertEquals(productMfgDate, inventoryModel.getProductMfgDate());
    }
}
