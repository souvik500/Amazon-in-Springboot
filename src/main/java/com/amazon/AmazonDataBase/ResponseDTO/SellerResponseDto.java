package com.amazon.AmazonDataBase.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerResponseDto {

    private String name;

    private int age;

    private String mobileNo;

    private String email;

    private String panNo;

    private String sellerAddress;

    List<ProductResponseDto> productList = new ArrayList<>();

}