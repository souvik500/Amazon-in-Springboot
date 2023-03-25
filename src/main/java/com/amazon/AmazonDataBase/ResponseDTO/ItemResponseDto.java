package com.amazon.AmazonDataBase.ResponseDTO;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDto
{
    private String  productName;

    private int price;

    private ProductCategory productCategory;

    private String customerName;
}
