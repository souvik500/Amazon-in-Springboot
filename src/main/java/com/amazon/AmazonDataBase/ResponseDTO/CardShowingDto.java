package com.amazon.AmazonDataBase.ResponseDTO;

import com.amazon.AmazonDataBase.Enum.CardType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CardShowingDto
{
    private String cardNo;

    private CardType cardType;
}
