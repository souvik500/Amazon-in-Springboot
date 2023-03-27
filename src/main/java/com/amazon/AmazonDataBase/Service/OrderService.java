package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.OrderConvertor;
import com.amazon.AmazonDataBase.Enum.ProductStatus;
import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
import com.amazon.AmazonDataBase.Model.*;
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


    public OrderResponseDto addOrder (OrderRequestDto orderRequestDto) throws ProductNotFoundException
    {
        Product product;
        try {
            System.out.println(orderRequestDto.getProductId());
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

        //if productQuantity stock is very lower than required Quantity
        if (product.getQuantity() < orderRequestDto.getRequiredQuantity())
        {
            throw new RuntimeException("InSufficient Product, Remove Some Quantity");
        }

        //If Product and Customer Both Present then you can Buy this item

        int totalCost = orderRequestDto.getRequiredQuantity() * product.getPrice();

        int deliveryCharge = 0;
        if (totalCost < 500) {
            deliveryCharge = 40;
            totalCost += deliveryCharge;
        }

        //Set all details in Ordered Entity
        Ordered ordered = OrderConvertor.OrderRequestDtoToOrdered(orderRequestDto,deliveryCharge, totalCost);

        //SetCard for payment by Customer
        Card card = customer.getCardList().get(0);
        String cardNo = card.getCardNo();

        StringBuilder crossMarkCardNo = new StringBuilder();
        for (int i = 0; i < cardNo.length()  - 4; i++)
        {
            crossMarkCardNo.append("X");
        }

        crossMarkCardNo.append(cardNo.substring(cardNo.length() - 4));

        //Now we have to set current used cardNo to Order Db.
        //toString()==> StringBuilder to String Converting
        ordered.setCardUsedForPayment(crossMarkCardNo.toString());


        //set List of Item to order Entity
        Item item = Item.builder()
                .ordered(ordered)
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .build();

        //Set and Update Products Product Details from Item Entity because Item is Connected with Product
        int leftQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();

        if (leftQuantity <= 0) product.setProductStatus(ProductStatus.OUTOFSTOCK);

        //set Product Quantity
        product.setQuantity(leftQuantity);

        //Set Product to Item
        item.setProduct(product);

        //Set Item to itemList in Ordered Entity
        ordered.getItemList().add(item);

        //set customer
        customer.getOrderedList().add(ordered);
        ordered.setCustomer(customer);

        // Set to Customer Repository
        Customer saveCustomer = customerRepository.save(customer);

        int size = saveCustomer.getOrderedList().size();
        Ordered saveOrder = saveCustomer.getOrderedList().get(size - 1);

        //Prepare Response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderDate(saveOrder.getOrderDate())
                .orderQuantity(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(ordered.getCardUsedForPayment())
                .itemPrice(product.getPrice())
                .deliveryCharge(deliveryCharge)
                .productName(product.getName())
                .totalCost(totalCost)
                .build();

        return orderResponseDto;
    }
}
