package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.RequestDTO.OrderRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.OrderResponseDto;
import com.amazon.AmazonDataBase.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity addOrder (@RequestBody OrderRequestDto orderRequestDto)
    {
        OrderResponseDto orderResponseDto;

        try {
            orderResponseDto = orderService.addOrder(orderRequestDto);
            return new ResponseEntity<>(orderResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity (e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
