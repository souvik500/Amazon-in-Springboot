package com.amazon.AmazonDataBase.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardResponseDto
{
    private String customerName;

    List<CardShowingDto> cardList;
}
