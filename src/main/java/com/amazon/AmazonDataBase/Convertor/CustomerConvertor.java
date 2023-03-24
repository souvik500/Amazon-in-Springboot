package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.RequestDTO.CustomerRequestDto;

public class CustomerConvertor
{
    public static Customer CustomerRequestDtoToCustomer (CustomerRequestDto customerRequestDto)
    {
        return  Customer.builder()
                .name(customerRequestDto.getName())
                .email(customerRequestDto.getEmail())
                .age(customerRequestDto.getAge())
                .mobileNo(customerRequestDto.getMobileNo())
                .build();
    }
}
