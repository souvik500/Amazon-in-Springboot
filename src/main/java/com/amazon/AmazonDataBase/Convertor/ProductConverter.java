package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.Model.Seller;
import com.amazon.AmazonDataBase.RequestDTO.ProductRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;

public class ProductConverter {

    public static Product productRequestDtoToProduct(ProductRequestDto product, Seller seller){
        String productDimens = product.getWeight() + "$" + product.getHeight() + "$" + product.getWidth() + "$" + product.getLength();

        return Product.builder()
                .name(product.getName())
                .productDetail(product.getProductDetail()).price(product.getPrice())
                .productCategory(product.getProductCategory())
                .quantity(product.getQuantity())
                .seller(seller)
                .productDimension(productDimens)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productCategory(product.getProductCategory())
                .productDetail(product.getProductDetail())
                //.sellerName(product.getSeller().getName())
                .sellerAddress(product.getSeller().getSellerAddress())
                .productDimension(product.getProductDimension())
                .build();
    }
}


//import com.amazon.AmazonDataBase.Enum.ProductStatus;
//import com.amazon.AmazonDataBase.Model.Product;
//import com.amazon.AmazonDataBase.Model.Seller;
//import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;
//
//public class ProductConvertor
//{
////    public static Product productRequestDtoToProduct (ProductRequestDto productRequestDto)
////    {
////        return Product.builder()
////                .name(productRequestDto.getProductName())
////                .price(productRequestDto.getPrice())
////                .quantity(productRequestDto.getQuantity())
////                .productCategory(productRequestDto.getProductCategory())
////                .productStatus(ProductStatus.AVAILABLE)
////                .build();
////        Product product = Product.builder()
////                .name(productRequestDto.getProductName())
////                .price(productRequestDto.getPrice())
////                .productCategory(productRequestDto.getCategory())
////                .quantity(productRequestDto.getQuantity())
////                .productStatus(ProductStatus.AVAILABLE)
////                .build();
////        System.out.println(productRequestDto.getQuantity());
////        System.out.println(productRequestDto.getPrice());
//        //return product;
// //   }
//
//    public static ProductResponseDto productToProductResponseDto (Product product)
//    {
//        ProductResponseDto productResponseDto = ProductResponseDto.builder()
//                .productName(product.getName())
//                .category(product.getProductCategory())
//                .status(product.getProductStatus())
//                .quantity(product.getQuantity())
//                .price(product.getPrice())
//                .build();
//
//        return productResponseDto;
//    }
//
//}
