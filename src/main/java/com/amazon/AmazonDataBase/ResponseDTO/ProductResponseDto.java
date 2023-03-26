package com.amazon.AmazonDataBase.ResponseDTO;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto
{
    private String sellerName;

    private String productName;

    private int price;

    private ProductCategory category;

    private int quantity;

    private ProductStatus status;
}
