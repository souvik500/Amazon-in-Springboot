package com.amazon.AmazonDataBase.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private String productName;

    private Date orderDate;

    private int quantityOrdered;

    private int ItemCost;

    private int totalCost;

    private int deliveryCharge;

    private String cardUsedForPayment;

}