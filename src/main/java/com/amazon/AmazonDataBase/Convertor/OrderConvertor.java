package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Ordered;
import com.amazon.AmazonDataBase.RequestDTO.OrderRequestDto;

import java.util.UUID;

public class OrderConvertor
{
    //private static int deliveryCharge = 0;
    public static Ordered OrderRequestDtoToOrdered (OrderRequestDto orderRequestDto, int deliveryCost, int totalCost)
    {

//        if (totalCost < 500) {
//            deliveryCharge = 40;
//            totalCost += deliveryCharge;
//        }
        Ordered ordered = Ordered.builder()
                .transactionId(String.valueOf(UUID.randomUUID()))
                .totalCost(totalCost)
                .deliveryCharge(deliveryCost)
                .build();

        return ordered;
    }

//    public static OrderResponseDto OrderedToOrderResponseDto (int totalCost, Order saveOrder, Product product, )
//    {
//
//    }
}
