package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Exception.CustomerNotFoundException;
import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
import com.amazon.AmazonDataBase.RequestDTO.OrderRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.OrderResponseDto;
import com.amazon.AmazonDataBase.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    CartService cartService;


    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody OrderRequestDto orderRequestDto){
        String message;
        try{
            message = cartService.addToCart(orderRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);

    }

    @PostMapping("/checkOut/{id}")
    public ResponseEntity checkOutCart( @PathVariable("id") int customerId){
        List<OrderResponseDto> responseDtoList;
        try{
            responseDtoList = cartService.checkOutCart(customerId);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(responseDtoList,HttpStatus.CREATED);
    }


//    @PostMapping("/addToCart")
//    public ResponseEntity addToCart (@RequestParam int productId,@RequestParam int customerId,@RequestParam int quantity) throws ProductNotFoundException, CustomerNotFoundException
//    {
//        try {
//            cartService.addToCart(productId, customerId, quantity);
//            return new ResponseEntity<>("Order add to cart", HttpStatus.ACCEPTED);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    @PutMapping("/checkOutCart/{id}")
//    public ResponseEntity checkOutCart (@PathVariable("id") int customerId)
//    {
//        List<OrderResponseDto> orderResponseDtoList;
//        try {
//            orderResponseDtoList = cartService.checkOutCart(customerId);
//            return new ResponseEntity (orderResponseDtoList, HttpStatus.ACCEPTED);
//        } catch (CustomerNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
}
