package com.example.inventorymanagement.service;
import com.example.inventorymanagement.model.InventoryModel;

import java.util.List;

public interface InventoryService {
    // Method to add a new product to the inventory
    InventoryModel addProduct(InventoryModel product);

    // Method to get all products from the inventory
    List<InventoryModel> getAllProducts();

    // Method to get a product from the inventory by its serial number
    InventoryModel getProductById(Integer serialNumber);

    // Method to update an existing product in the inventory
    InventoryModel updateProduct(Integer serialNumber, InventoryModel product);

    // Method to delete a product from the inventory by its serial number
    boolean deleteProduct(Integer serialNumber);
}
