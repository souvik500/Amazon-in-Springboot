package com.amazon.AmazonDataBase.RequestDTO;

import com.amazon.AmazonDataBase.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto
{

    private int cvv;

    private CardType cardType;

    private int customerId;
}
