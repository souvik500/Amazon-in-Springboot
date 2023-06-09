package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Exception.CustomerNotFoundException;
import com.amazon.AmazonDataBase.RequestDTO.CardRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.CardResponseDto;
import com.amazon.AmazonDataBase.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/card")
public class CardController
{
    @Autowired
    CardService cardService;


    @PostMapping("/add")
    public String addCard(@RequestBody CardRequestDto cardRequestDto){

        try{
            cardService.addCard(cardRequestDto);
        }catch (CustomerNotFoundException | SQLException e) {
            return e.getMessage();
        }
        return "card Saved";
    }

//    @PostMapping ("/addCard")
//    public ResponseEntity addCard (@RequestBody CardRequestDto cardRequestDto)
//    {
//        CardResponseDto cardResponseDto;
//        try {
//            cardResponseDto = cardService.addCard(cardRequestDto);
//        }
//        catch (Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(cardResponseDto, HttpStatus.ACCEPTED);
//    }
}
