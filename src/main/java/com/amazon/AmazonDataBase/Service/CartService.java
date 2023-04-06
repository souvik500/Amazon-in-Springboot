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

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService
{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    OrderedServiceImpl orderedService;

    public String addToCart(OrderRequestDto orderRequestDto) throws Exception {

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

        Cart cart = customer.getCart();
        int newCost = cart.getCartTotal() + orderRequestDto.getRequiredQuantity() * product.getPrice();
        cart.setCartTotal(newCost);
        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        cart.getItems().add(item);

        customerRepository.save(customer);

        return "Item has been added";


    }


    public List<OrderResponseDto> checkOutCart(int customerId) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Enter a valid customer id");
        }

        List<OrderResponseDto> responseDtoList = new ArrayList<>();
        Cart cart = customer.getCart();
        for(Item item : cart.getItems()){
//            OrderRequestDto requestDto = new OrderRequestDto();
//            requestDto.setRequiredQuantity(item.getRequiredQuantity());
//            requestDto.setProductId(item.getProduct().getId());
//            requestDto.setCustomerId(customerId);
//
//            OrderResponseDto orderResponseDto = orderedService.placeOrder(requestDto);
//            responseDtoList.add(orderResponseDto);
            Ordered order = new Ordered();
            order.setTotalCost(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharges(0);
            order.setCustomer(customer);
            order.getItemList().add(item);

            Card card = customer.getCards().get(0);

        }
        cart.setItems(new ArrayList<>());
        cart.setCartTotal(0);

        return  responseDtoList;
    }

//    @Autowired
//    CustomerRepository customerRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    CartRepository cartRepository;
//
//    public void addToCart (int productId, int customerId, int quantity) throws ProductNotFoundException, CustomerNotFoundException {
//        Product product;
//        try {
//
//            product = productRepository.findProduct(productId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ProductNotFoundException("Currently product isn't Exists");
//        }
//
//        Customer customer;
//        try {
//            customer = customerRepository.findById(customerId).get();
//        } catch (Exception e) {
//            throw new CustomerNotFoundException("You are not register Yet!!!, Create Account First");
//        }
//
//        if (product.getQuantity() < quantity)
//        {
//            throw new RuntimeException("InSufficient Product, Remove Some Quantity");
//        }
//
//        //If Product and Customer Both Present then you can Buy this item
//        Cart cart = customer.getCart();
//
//        int newCost = cart.getCartTotal() + quantity * product.getPrice();
//
//        cart.setCartTotal(newCost);
//
//       // System.out.println(cart.getCartTotal());
//
//        //Add Item
////        Item item = Item.builder()
////                .requiredQuantity(orderRequestDto.getRequiredQuantity())
////                .cart(cart)
////                .product(product)
////                .build();
//        Item item = new Item();
//        item.setRequiredQuantity(quantity);
//        item.setProduct(product);
//        item.setCart(cart);
//
//        cart.getItems().add(item);
//
//        //System.out.println(cart.getItems().toString());
//
//        customerRepository.save(customer);
//    }
//
//    public List<OrderResponseDto> checkOutCart (int customerId) throws CustomerNotFoundException {
//
//        Customer customer;
//        try {
//            customer = customerRepository.findById(customerId).get();
//            //System.out.println(customerId);
//        } catch (Exception e) {
//            throw new CustomerNotFoundException("You are not register Yet!!!, Create Account First");
//        }
//
//        /**
//         * Create List of OrderResponseDto
//         */
//        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
//
//        // find TotalCost of cart
//        int totalCost = customer.getCart().getCartTotal();
//
//        //Create Cart object from customer entity
//        Cart cart = customer.getCart();
//
//        for (Item item : cart.getItems())
//        {
//            //set order details of each product that i want to check out from cart
//            Ordered ordered = new Ordered();
//
//            ordered.getItemList().add(item);
//            ordered.setTotalCost(item.getRequiredQuantity() * item.getProduct().getPrice());
//            ordered.setCustomer(customer);
//            ordered.setDeliveryCharge(0);
//
//            //SetCard for payment by Customer
//            Card card = customer.getCardList().get(0);
//            String cardNo = card.getCardNo();
//
//            StringBuilder crossMarkCardNo = new StringBuilder();
//            for (int i = 0; i < cardNo.length()  - 4; i++)
//            {
//                crossMarkCardNo.append("X");
//            }
//
//            crossMarkCardNo.append(cardNo.substring(cardNo.length() - 4));
//            //Now we have to set current used cardNo to Order Db.
//            //toString()==> StringBuilder to String Converting
//            ordered.setCardUsedForPayment(crossMarkCardNo.toString());
//
//            //Set and Update Products Product Details from Item Entity because Item is Connected with Product
//            int leftQuantity = item.getProduct().getQuantity() - item.getRequiredQuantity();
//
//            if (leftQuantity <= 0) item.getProduct().setProductStatus(ProductStatus.OUTOFSTOCK);
//
//            //set Product Quantity
//            item.getProduct().setQuantity(leftQuantity);
//
//            //Then set Each Ordered to Customer Entity Ordered list
//            customer.getOrderedList().add(ordered);
//
//
//            //Prepare Response DTO
//            OrderResponseDto orderResponseDto = OrderResponseDto.builder()
//                    .orderDate(item.getOrdered().getOrderDate())
//                    .orderQuantity(item.getRequiredQuantity())
//                    .cardUsedForPayment(String.valueOf(crossMarkCardNo))
//                    .itemPrice(item.getProduct().getPrice())
//                    .deliveryCharge(0)
//                    .productName(item.getProduct().getName())
//                    .totalCost(ordered.getTotalCost())
//                    .build();
//
//            orderResponseDtoList.add(orderResponseDto);
//
//        }
//
//        /**
//         * After every product customer ordered from Cart entity then cart will empty
//         * so thats wy we many empty arraylist in cart
//         */
//        //cartRepository.deleteAllById(customerId);
//        cart.setCartTotal(0);
//
//        //Save to Customer repository
//        customerRepository.save(customer);
//
//        return orderResponseDtoList;
//    }
}
