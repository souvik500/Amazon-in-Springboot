package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Enum.ProductStatus;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.RequestDTO.ProductRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;

public class ProductConverter
{
    public static Product productRequestDtoToProduct (ProductRequestDto productRequestDto)
    {
        Product product = Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
//        System.out.println(productRequestDto.getQuantity());
//        System.out.println(productRequestDto.getPrice());
        return product;
    }

    public static ProductResponseDto productToProductResponseDto (Product product)
    {
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .productName(product.getName())
                .category(product.getProductCategory())
                .status(product.getProductStatus())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();

        return productResponseDto;
    }

}
