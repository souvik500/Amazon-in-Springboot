package com.amazon.AmazonDataBase.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This DTO is nothing but that which person is viewing which product
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto
{
    private int customerId;

    private int productId;
}
