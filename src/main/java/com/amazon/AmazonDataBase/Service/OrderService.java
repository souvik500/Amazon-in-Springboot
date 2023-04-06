package com.amazon.AmazonDataBase.Service;


import com.amazon.AmazonDataBase.Enum.ProductStatus;
import com.amazon.AmazonDataBase.Exception.CustomerNotFoundException;
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
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    EmailCommunication emailCommunication;

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Invalid Product Id");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        // Prepare Order
        int totalCost = orderRequestDto.getRequiredQuantity()*product.getPrice();
        int deliveryCharge = 0;
        if(totalCost<500){
            deliveryCharge = 50;
            totalCost += deliveryCharge;
        }
        Ordered order = Ordered.builder()
                .totalCost(totalCost)
                .deliveryCharges(deliveryCharge)
                .build();

        // prepare the Card String;
        Card card = customer.getCards().get(0);
        String cardUsed = "";
        int cardNo = card.getCardNo().length();
        for(int i = 0;i<cardNo-4;i++){
            cardUsed += 'X';
        }
        cardUsed += card.getCardNo().substring(cardNo-4);
        order.setCardUsedForPayment(cardUsed);
        // update item
        Item item = Item.builder()
                .requiredQuantity(orderRequestDto.getRequiredQuantity())
                .build();
        // update customer's current order list
        customer.getOrderedList().add(order);
        order.setCustomer(customer);
        order.getItemList().add(item);

        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getOrderedList().get(savedCustomer.getOrderedList().size()-1);

        // update the quantity of product left in warehouse
        int leftQuantity = product.getQuantity()- orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        // update item in order

        item.setOrder(order);
        product.setItem(item);
        item.setProduct(product);

        // save product-item and customer-order
        customerRepository.save(customer);


        //prepare response DTO
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .productName(product.getName())
                .orderDate(order.getOrderDate())
                .quantityOrdered(orderRequestDto.getRequiredQuantity())
                .cardUsedForPayment(cardUsed)
                .ItemCost(product.getPrice())
                .totalCost(order.getTotalCost())
                .deliveryCharge(deliveryCharge)
                .build();

//        emailCommunication.sendSimpleMessage(customer.getEmail(),"This is mail subject","This is mail text");
        //MobileCommunication.orderStatus(customer.getMobileNo(),orderResponseDto.getProductName());
        return orderResponseDto;
    }


//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    CustomerRepository customerRepository;
//
//
//    public OrderResponseDto addOrder (OrderRequestDto orderRequestDto) throws ProductNotFoundException
//    {
//        Product product;
//        try {
//            //System.out.println(orderRequestDto.getProductId());
//            product = productRepository.findById(orderRequestDto.getProductId()).get();
//        } catch (Exception e) {
//            throw new ProductNotFoundException("Currently product isn't Exists");
//        }
//
//        Customer customer;
//        try {
//            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
//        } catch (Exception e) {
//            throw new ProductNotFoundException("You are not register Yet!!!, Create Account First");
//        }
//
//        //if productQuantity stock is very lower than required Quantity
//        if (product.getQuantity() < orderRequestDto.getRequiredQuantity())
//        {
//            throw new RuntimeException("InSufficient Product, Remove Some Quantity");
//        }
//
//        //If Product and Customer Both Present then you can Buy this item
//
//        int totalCost = orderRequestDto.getRequiredQuantity() * product.getPrice();
//
//        int deliveryCharge = 0;
//        if (totalCost < 500) {
//            deliveryCharge = 40;
//            totalCost += deliveryCharge;
//        }
//
//        //Set all details in Ordered Entity
//        //Ordered ordered = OrderConvertor.OrderRequestDtoToOrdered(orderRequestDto,deliveryCharge, totalCost);
//        Ordered ordered = new Ordered();
//        ordered.setTransactionId(String.valueOf(UUID.randomUUID()));
//        ordered.setTotalCost(totalCost);
//        ordered.setDeliveryCharge(deliveryCharge);
//
//        //SetCard for payment by Customer
//        Card card = customer.getCardList().get(0);
//        String cardNo = card.getCardNo();
//
//        StringBuilder crossMarkCardNo = new StringBuilder();
//        for (int i = 0; i < cardNo.length()  - 4; i++)
//        {
//            crossMarkCardNo.append("X");
//        }
//
//        crossMarkCardNo.append(cardNo.substring(cardNo.length() - 4));
//
//        //Now we have to set current used cardNo to Order Db.
//        //toString()==> StringBuilder to String Converting
//        ordered.setCardUsedForPayment(crossMarkCardNo.toString());
//
//
//        //set List of Item to order Entity
//        Item item = new Item();
//        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
//        item.setOrdered(ordered);
//        item.setProduct(product);
//
//        //Set Item to itemList in Ordered Entity
//        ordered.getItemList().add(item);
//
//        //set customer
//        ordered.setCustomer(customer);
//
//
//        //Set and Update Products Product Details from Item Entity because Item is Connected with Product
//        int leftQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
//
//        if (leftQuantity <= 0) product.setProductStatus(ProductStatus.OUTOFSTOCK);
//
//        //set Product Quantity
//        product.setQuantity(leftQuantity);
//
//        //Set Product to Item
//        //product.setItem(item);
//
//
//
//        customer.getOrderedList().add(ordered);
//
//        // Set to Customer Repository
//        Customer saveCustomer = customerRepository.save(customer);
//
//        int size = saveCustomer.getOrderedList().size();
//        Ordered saveOrder = saveCustomer.getOrderedList().get(size - 1);
//
//        //Prepare Response DTO
//        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
//                .orderDate(saveOrder.getOrderDate())
//                .orderQuantity(orderRequestDto.getRequiredQuantity())
//                .cardUsedForPayment(ordered.getCardUsedForPayment())
//                .itemPrice(product.getPrice())
//                .deliveryCharge(deliveryCharge)
//                .productName(product.getName())
//                .totalCost(totalCost)
//                .build();
//
//        /*
//        * Ordered order = new Ordered();
//        order.setTotalCost(orderRequestDto.getRequiredQuantity()* product.getPrice());
//        order.setDeliveryCharge(40);
//        Card card = customer.getCards().get(0);
//        String cardNo = "";
//        for(int i=0;i<card.getCardNo().length()-4;i++)
//            cardNo += 'X';
//        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
//        order.setCardUsedForPayment(cardNo);
//
//        Item item = new Item();
//        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
//        item.setProduct(product);
//        item.setOrder(order);
//        order.getOrderedItems().add(item);
//        order.setCustomer(customer);
//
//        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
//        if(leftQuantity<=0)
//            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
//        product.setQuantity(leftQuantity);
//
//        customer.getOrders().add(order);
//        Customer savedCustomer = customerRepository.save(customer);
//        Ordered savedOrder = savedCustomer.getOrders().get(savedCustomer.getOrders().size()-1);
//
//        //prepare response DTO
//        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
//                .productName(product.getProductName())
//                .orderDate(savedOrder.getOrderDate())
//                .quantityOrdered(orderRequestDto.getRequiredQuantity())
//                .cardUsedForPayment(cardNo)
//                .itemPrice(product.getPrice())
//                .totalCost(order.getTotalCost())
//                .deliveryCharge(40)
//                .build();*/
//
//        return orderResponseDto;
//    }
}
