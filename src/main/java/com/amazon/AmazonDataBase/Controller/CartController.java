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
@RequestMapping("/item")
public class CartController
{
    @Autowired
    CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity addToCart (@RequestBody OrderRequestDto orderRequestDto) throws ProductNotFoundException, CustomerNotFoundException
    {

        cartService.addToCart(orderRequestDto);

        return new ResponseEntity<>("Order add to cart", HttpStatus.ACCEPTED);
    }

    @PutMapping("/checkOutCart/{id}")
    public ResponseEntity checkOutCart (@PathVariable("id") int customerId)
    {
        List<OrderResponseDto> orderResponseDtoList;
        try {
            orderResponseDtoList = cartService.checkOutCart(customerId);
            return new ResponseEntity (orderResponseDtoList, HttpStatus.ACCEPTED);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
