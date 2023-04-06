package com.amazon.AmazonDataBase.ResponseDTO;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private String productName;
    private int price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;

}
