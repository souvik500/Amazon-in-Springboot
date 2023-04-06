package com.amazon.AmazonDataBase.Convertor;

import com.amazon.AmazonDataBase.Model.Card;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.RequestDTO.CardRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.CardResponseDto;
import com.amazon.AmazonDataBase.ResponseDTO.CardShowingDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class CardConverter {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto, Customer customer){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .customer(customer)
                .build();
    }
}
//public class CardConvertor
//{
//    public static Card CardRequestDtoToCard (CardRequestDto cardRequestDto)
//    {
//        return Card.builder()
//                .cardType(cardRequestDto.getCardType())
//                .cvv(cardRequestDto.getCvv())
//                .build();
//    }
//
//
//    public static CardResponseDto CardToCardResponseDto (Card card)
//    {
//        List<CardShowingDto> cardShowingDtoList = new ArrayList<>();
//
//        for (Card card1 : card.getCustomer().getCardList())
//        {
//            CardShowingDto cardShowingDto = new CardShowingDto();
//            cardShowingDto.setCardNo(card1.getCardNo());
//            cardShowingDto.setCardType(card1.getCardType());
//
//            cardShowingDtoList.add(cardShowingDto);
//        }
//
//        CardResponseDto cardResponseDto = CardResponseDto.builder()
//                .cardList(cardShowingDtoList)
//                .customerName(card.getCustomer().getName())
//                .build();
//
//        return cardResponseDto;
//    }
//}
