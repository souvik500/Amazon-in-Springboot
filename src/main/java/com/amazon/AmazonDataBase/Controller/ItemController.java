package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
import com.amazon.AmazonDataBase.RequestDTO.ItemRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ItemResponseDto;
import com.amazon.AmazonDataBase.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
public class ItemController
{
    @Autowired
    ItemService itemService;


//    @PutMapping("/viewItem")
//    public ResponseEntity viewItem (@RequestBody ItemRequestDto itemRequestDto)
//    {
//        try {
//            ItemResponseDto itemResponseDto = itemService.viewItem(itemRequestDto);
//            return new ResponseEntity (itemResponseDto, HttpStatus.ACCEPTED);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/view/{productId}")
    public ResponseEntity viewItem(@PathVariable("productId") int productId){

        ItemResponseDto itemResponseDto;
        try{
            itemResponseDto = itemService.viewItem(productId);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(itemResponseDto,HttpStatus.ACCEPTED);
    }
}
