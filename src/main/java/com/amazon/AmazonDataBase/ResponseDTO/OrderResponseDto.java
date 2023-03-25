package com.amazon.AmazonDataBase.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto
{
    private String productName;

    private Date orderDate;

    private int itemPrice;

    private int orderQuantity;

    private int deliveryCharge;

    private int totalCost;

    private String cardUsedForPayment;


}
