package com.example.inventorymanagement.service;
import com.example.inventorymanagement.model.InventoryModel;
import com.example.inventorymanagement.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryModel addProduct(InventoryModel product) {
        return inventoryRepository.save(product);
    }

    @Override
    public List<InventoryModel> getAllProducts() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryModel getProductById(Integer serialNumber) {
        Optional<InventoryModel> optionalProduct = inventoryRepository.findById(serialNumber);
        return optionalProduct.orElse(null);
    }

    @Override
    public InventoryModel updateProduct(Integer serialNumber, InventoryModel product) {
        Optional<InventoryModel> optionalProduct = inventoryRepository.findById(serialNumber);
        if (optionalProduct.isPresent()) {
            InventoryModel existingProduct = optionalProduct.get();
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductQuantity(product.getProductQuantity());
            existingProduct.setProductDescription(product.getProductDescription());
            existingProduct.setProductExpiry(product.getProductExpiry());
            existingProduct.setProductMfgDate(product.getProductMfgDate());

            return inventoryRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteProduct(Integer serialNumber) {
        Optional<InventoryModel> optionalProduct = inventoryRepository.findById(serialNumber);
        if (optionalProduct.isPresent()) {
            inventoryRepository.delete(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }
}
