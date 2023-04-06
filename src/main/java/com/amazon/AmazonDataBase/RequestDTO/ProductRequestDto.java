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
public class ProductRequestDto {

    private String name;

    private int price;

    private int quantity;

    private ProductCategory productCategory;

    private String productDetail;

    private int sellerId;

    private double weight;

    private double height;

    private double width;

    private double length;
}