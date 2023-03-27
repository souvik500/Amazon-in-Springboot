package com.amazon.AmazonDataBase.RequestDTO;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto
{
    private int sellerId;

    private String productName;

    private int price;

    private int quantity;

    private ProductCategory productCategory;

//    public ProductRequestDto(int sellerId, String productName, int price, int quantity, ProductCategory productCategory) {
//        this.sellerId = sellerId;
//        this.productName = productName;
//        this.price = price;
//        this.quantity = quantity;
//        this.productCategory = productCategory;
//    }

//    public int getSellerId() {
//        return sellerId;
//    }
//
//    public void setSellerId(int sellerId) {
//        this.sellerId = sellerId;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public ProductCategory getProductCategory() {
//        return productCategory;
//    }
//
//    public void setProductCategory(ProductCategory productCategory) {
//        this.productCategory = productCategory;
//    }
}
