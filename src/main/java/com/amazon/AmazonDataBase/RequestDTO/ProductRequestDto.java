package com.amazon.AmazonDataBase.RequestDTO;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto
{
    private int sellerId;

    private String productName;

    private int price;

    private ProductCategory category;

    private int quantity;
}
