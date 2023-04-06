package com.amazon.AmazonDataBase.RequestDTO;


import com.amazon.AmazonDataBase.Enum.CardType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    private String cardNo;

    private String cvv;

    private CardType cardType;

    private int customerId;
}
