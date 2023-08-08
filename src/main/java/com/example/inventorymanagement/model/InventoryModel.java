package com.example.inventorymanagement.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "inventory")
public class InventoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productSerialNumber;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Double productPrice;

    @Column(nullable = false)
    private Integer productQuantity;

    private String productDescription;

    @Temporal(TemporalType.DATE)
    private Date productExpiry;

    @Temporal(TemporalType.DATE)
    private Date productMfgDate;

    // Constructors, getters, and setters
    // ...

    public InventoryModel() {
    }

    public InventoryModel(Integer productSerialNumber, String productName, Double productPrice, Integer productQuantity, String productDescription, Date productExpiry, Date productMfgDate) {
        this.productSerialNumber = productSerialNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.productExpiry = productExpiry;
        this.productMfgDate = productMfgDate;
    }

    public Integer getProductSerialNumber() {
        return productSerialNumber;
    }

    public void setProductSerialNumber(Integer productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getProductExpiry() {
        return productExpiry;
    }

    public void setProductExpiry(Date productExpiry) {
        this.productExpiry = productExpiry;
    }

    public Date getProductMfgDate() {
        return productMfgDate;
    }

    public void setProductMfgDate(Date productMfgDate) {
        this.productMfgDate = productMfgDate;
    }
}
