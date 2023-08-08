package com.example.inventorymanagement.controllertest;

import com.example.inventorymanagement.model.InventoryModel;
import com.example.inventorymanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryModel> addProduct(@RequestBody InventoryModel product) {
        InventoryModel addedProduct = inventoryService.addProduct(product);
        if (addedProduct != null) {
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<InventoryModel>> getAllProducts() {
        List<InventoryModel> products = inventoryService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<InventoryModel> getProductById(@PathVariable Integer serialNumber) {
        InventoryModel product = inventoryService.getProductById(serialNumber);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<InventoryModel> updateProduct(@PathVariable Integer serialNumber, @RequestBody InventoryModel product) {
        InventoryModel updatedProduct = inventoryService.updateProduct(serialNumber, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer serialNumber) {
        boolean isDeleted = inventoryService.deleteProduct(serialNumber);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

