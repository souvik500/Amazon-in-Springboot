package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.OrderConvertor;
import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.Model.Item;
import com.amazon.AmazonDataBase.Model.Ordered;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.Repository.CartRepository;
import com.amazon.AmazonDataBase.Repository.CustomerRepository;
import com.amazon.AmazonDataBase.Repository.ProductRepository;
import com.amazon.AmazonDataBase.RequestDTO.OrderRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService
{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;


    public OrderResponseDto addOrder (OrderRequestDto orderRequestDto) throws ProductNotFoundException
    {
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        } catch (Exception e) {
            throw new ProductNotFoundException("Currently product isn't Exists");
        }

        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new ProductNotFoundException("You are not register Yet!!!, Create Account First");
        }

        //if productQuantity stock is very low than required Quantity
        if (product.getQuantity() < orderRequestDto.getRequiredQuantity())
        {
            throw new RuntimeException("InSufficient Product, Remove Some Quantity");
        }

        //If Product and Customer Both Present then you can Buy this item

        int totalCost = orderRequestDto.getRequiredQuantity() * product.getPrice();

        Ordered ordered = OrderConvertor.OrderRequestDtoToOrdered(orderRequestDto, totalCost);

        //set customer
        ordered.setCustomer(customer);

        //set Item
        Item item = Item.builder()
                .ordered(ordered)
                .product(product)
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .build();

        //Set Item to itemList
        ordered.getItemList().add(item);
    }
}
