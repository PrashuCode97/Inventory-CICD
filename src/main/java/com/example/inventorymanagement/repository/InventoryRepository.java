package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Integer> {
    // You can define custom queries or methods here if needed
}

