package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Seller;
import com.amazon.AmazonDataBase.RequestDTO.RequestSellerDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConverter
{
    public static Seller SellerRequestDtoToSeller (RequestSellerDto requestSellerDto)
    {
        return Seller.builder()
                .name(requestSellerDto.getName())
                .age(requestSellerDto.getAge())
                .pan(requestSellerDto.getPan())
                .email(requestSellerDto.getEmail())
                .mobileNo(requestSellerDto.getMobileNo())
                .build();
    }
}
