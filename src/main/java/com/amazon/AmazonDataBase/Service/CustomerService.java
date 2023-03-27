package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.CardConvertor;
import com.amazon.AmazonDataBase.Convertor.CustomerConvertor;
import com.amazon.AmazonDataBase.Model.Card;
import com.amazon.AmazonDataBase.Model.Cart;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.Repository.CustomerRepository;
import com.amazon.AmazonDataBase.RequestDTO.CustomerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;


    public void addCustomer (CustomerRequestDto customerRequestDto)
    {
        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        customerRepository.save(customer);
    }

    public void getCustomerById(int customerId)
    {
        Customer customer;
        try {
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e) {
            throw new RuntimeException("Customer Not Found, Pls Register first");
        }

        //Prepare Customer ResponseDto

//        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
//                .name(customer.getName())
//                .email(customer.getEmail())
//                .age(customer.getAge())
//                .mobileNo(customer.getMobileNo())
//                .cardList(CardConvertor.CardToCardResponseDto().getCardList())
//                .build();
    }
}
