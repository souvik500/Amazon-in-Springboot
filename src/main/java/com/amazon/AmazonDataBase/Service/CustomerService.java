package com.amazon.AmazonDataBase.Service;


import com.amazon.AmazonDataBase.Convertor.CustomerConverter;
import com.amazon.AmazonDataBase.Exception.CustomerNotFoundException;
import com.amazon.AmazonDataBase.Model.Card;
import com.amazon.AmazonDataBase.Model.Cart;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.Repository.CartRepository;
import com.amazon.AmazonDataBase.Repository.CustomerRepository;
import com.amazon.AmazonDataBase.RequestDTO.CustomerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService
{
//    @Autowired
//    CustomerRepository customerRepository;
//
//
//    public void addCustomer (CustomerRequestDto customerRequestDto)
//    {
//        Customer customer = CustomerConvertor.CustomerRequestDtoToCustomer(customerRequestDto);
//
//        Cart cart = new Cart();
//        cart.setCartTotal(0);
//        cart.setCustomer(customer);
//
//        customer.setCart(cart);
//
//        customerRepository.save(customer);
//    }
//
//    public void getCustomerById(int customerId)
//    {
//        Customer customer;
//        try {
//            customer = customerRepository.findById(customerId).get();
//        }
//        catch (Exception e) {
//            throw new RuntimeException("Customer Not Found, Pls Register first");
//        }
//
//        //Prepare Customer ResponseDto
//
////        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
////                .name(customer.getName())
////                .email(customer.getEmail())
////                .age(customer.getAge())
////                .mobileNo(customer.getMobileNo())
////                .cardList(CardConvertor.CardToCardResponseDto().getCardList())
////                .build();
//    }
@Autowired
CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;


    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws SQLException {
        Customer customer = CustomerConverter.customerRequestDtoToCustomer(customerRequestDto);
       // EmailService emailService = new EmailService();
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);
        try{
//            emailService.send(customer.getEmail(),"Created","THis is text");
      //      MobileCommunication.thankYou(customer.getMobileNo());
            customerRepository.save(customer);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("User already exist by using this Email or mobile number!");
        }

        return  CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());
    }


    public CustomerResponseDto getCustomerByMobile(String mobileNo) throws CustomerNotFoundException {
        int customerId = customerRepository.findByMobileNo(mobileNo);
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Mobile no Does not exit, Enter a valid Mobile No!");
        }

        return CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());
    }


    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException {
        int customerId = customerRepository.findByEmail(email);
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Mobile no Does not exit, Enter a valid Mobile No!");
        }

        return CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());

    }


    public List<CustomerResponseDto> getAllCustomer() {
        List<Integer> customerIds = customerRepository.getAllCustomerIds();
        List<CustomerResponseDto> customers = new ArrayList<>();

        for(Integer customerId : customerIds){
            Customer customer = customerRepository.findById(customerId).get();
            CustomerResponseDto customerResponseDto = CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());
            customers.add(customerResponseDto);
        }
        return customers;
    }


    public List<String> deleteCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer;
        //EmailService emailService = new EmailService();
        try {
            customer = customerRepository.findById(customerId).get();
        }catch (NoSuchElementException e){
            throw new CustomerNotFoundException("Enter a valid Customer Id!");
        }
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add(customer.getEmail());
        customerInfo.add(customer.getMobileNo());
        customerInfo.add(Integer.toString(customer.getId()));
        cartRepository.delete(customer.getCart());

        //emailService.send(customer.getEmail(),"Deleted", "THis is text");
        customerRepository.delete(customer);


        return customerInfo;
    }
}
