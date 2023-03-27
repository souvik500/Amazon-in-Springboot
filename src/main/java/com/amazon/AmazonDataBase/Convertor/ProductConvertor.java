package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Enum.ProductStatus;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.RequestDTO.ProductRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;

public class ProductConvertor
{
    public static Product productRequestDtoToProduct (ProductRequestDto productRequestDto)
    {
        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
//        Product product = Product.builder()
//                .name(productRequestDto.getProductName())
//                .price(productRequestDto.getPrice())
//                .productCategory(productRequestDto.getCategory())
//                .quantity(productRequestDto.getQuantity())
//                .productStatus(ProductStatus.AVAILABLE)
//                .build();
//        System.out.println(productRequestDto.getQuantity());
//        System.out.println(productRequestDto.getPrice());
        //return product;
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
