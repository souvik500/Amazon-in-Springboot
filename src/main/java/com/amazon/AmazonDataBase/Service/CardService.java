package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.CardConverter;
import com.amazon.AmazonDataBase.Exception.CustomerNotFoundException;
import com.amazon.AmazonDataBase.Model.Card;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.Repository.CustomerRepository;
import com.amazon.AmazonDataBase.RequestDTO.CardRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CardService
{
//    @Autowired
//    CardRepository cardRepository;

//    @Autowired
//    CustomerRepository customerRepository;
//
//
//    public CardResponseDto addCard (CardRequestDto cardRequestDto)
//    {
//        Customer customer;
//
//        try {
//            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
//        }
//        catch (Exception e) {
//            throw new RuntimeException ("This Customer does not registered");
//        }
//
//        Card card = CardConvertor.CardRequestDtoToCard(cardRequestDto);
//        card.setCardNo(String.valueOf(UUID.randomUUID()));
//        card.setCustomer(customer);
//
//        card.getCustomer().getCardList().add(card);
//
//        customerRepository.save(customer);
//
//
//        //Prepare cardResponseDto for showing to customer
//        CardResponseDto cardResponseDto = CardConvertor.CardToCardResponseDto(card);
//
//        return cardResponseDto;
//    }

    @Autowired
    CustomerRepository customerRepository;
    public void addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException, SQLException {
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Enter a valid Customer Id");
        }

        Card card = CardConverter.CardRequestDtoToCard(cardRequestDto,customer);

        customer.getCards().add(card);

        try{
            customerRepository.save(customer);
        }catch (Exception e){
            throw new SQLException("This Card already Exist!");
        }

    }
}
