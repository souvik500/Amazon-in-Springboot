package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.RequestDTO.CustomerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.CustomerResponseDto;
import lombok.experimental.UtilityClass;

//public class CustomerConvertor
//{
//    public static Customer CustomerRequestDtoToCustomer (CustomerRequestDto customerRequestDto)
//    {
//        return  Customer.builder()
//                .name(customerRequestDto.getName())
//                .email(customerRequestDto.getEmail())
//                .age(customerRequestDto.getAge())
//                .mobileNo(customerRequestDto.getMobileNo())
//                .build();
//    }
//}

@UtilityClass
public class CustomerConverter {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .email(customerRequestDto.getEmail())
                .mobileNo(customerRequestDto.getMobileNo())
                .build();

    }
    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer,int noOfItems){

        return CustomerResponseDto.builder()
                .CustomerId(customer.getId())
                .age(customer.getAge())
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNo(customer.getMobileNo())
                .noOfItemsInCart(noOfItems)
                .build();

    }
}