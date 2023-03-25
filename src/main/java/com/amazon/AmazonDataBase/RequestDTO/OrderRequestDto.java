package com.amazon.AmazonDataBase.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto
{
    private int productId;

    private int customerId;

    private int requiredQuantity;
}
