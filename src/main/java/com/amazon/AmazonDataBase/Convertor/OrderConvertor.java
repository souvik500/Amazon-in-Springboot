package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Ordered;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.RequestDTO.OrderRequestDto;

import java.util.UUID;

public class OrderConvertor
{
    public static Ordered OrderRequestDtoToOrdered (OrderRequestDto orderRequestDto,int totalCost)
    {
        int deliveryCharge = 0;
        if (totalCost < 500) {
            deliveryCharge = 40;
            totalCost += deliveryCharge;
        }
        Ordered ordered = Ordered.builder()
                .transactionId(String.valueOf(UUID.randomUUID()))
                .totalCost(totalCost)
                .deliveryCharge(deliveryCharge)
                .cardUsedForPayment("Debit")
                .build();
    }
}
