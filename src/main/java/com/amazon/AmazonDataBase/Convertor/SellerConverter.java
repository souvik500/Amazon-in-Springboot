package com.amazon.AmazonDataBase.Convertor;

//import com.amazon.AmazonDataBase.Model.Seller;
//import com.amazon.AmazonDataBase.RequestDTO.RequestSellerDto;
//import lombok.experimental.UtilityClass;
//
//@UtilityClass
//public class SellerConverter
//{
//    public static Seller SellerRequestDtoToSeller (RequestSellerDto requestSellerDto)
//    {
//        return Seller.builder()
//                .name(requestSellerDto.getName())
//                .age(requestSellerDto.getAge())
//                .pan(requestSellerDto.getPan())
//                .email(requestSellerDto.getEmail())
//                .mobileNo(requestSellerDto.getMobileNo())
//                .build();
//    }
//}

import com.amazon.AmazonDataBase.Model.Seller;
import com.amazon.AmazonDataBase.RequestDTO.SellerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;
import com.amazon.AmazonDataBase.ResponseDTO.SellerResponseDto;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class SellerConverter {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        String sellerAddress = sellerRequestDto.getStreet1() +"$" + sellerRequestDto.getStreet2() + "$" + sellerRequestDto.getCity() + "$" + sellerRequestDto.getState() + "$" + sellerRequestDto.getCountry() + "$" + sellerRequestDto.getPinCode();
        return Seller.builder()
                .age(sellerRequestDto.getAge())
                .email(sellerRequestDto.getEmail())
                .mobileNo(sellerRequestDto.getMobileNo())
                .panNo(sellerRequestDto.getPanNo())
                .sellerAddress(sellerAddress)
                .build();
    }

    public static SellerResponseDto sellerTOSellerResponseDto(Seller seller, List<ProductResponseDto> productResponseDtoList){
        return SellerResponseDto.builder()
//                .name(seller.getName())
                .age(seller.getAge())
                .mobileNo(seller.getMobileNo())
                .email(seller.getEmail())
                .panNo(seller.getPanNo())
                .sellerAddress(seller.getSellerAddress())
                .productList(productResponseDtoList)
                .build();

    }
}