package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.RequestDTO.ItemRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ItemResponseDto;
import com.amazon.AmazonDataBase.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController
{
    @Autowired
    ItemService itemService;


    @PutMapping("/viewItem")
    public ResponseEntity viewItem (@RequestBody ItemRequestDto itemRequestDto)
    {
        try {
            ItemResponseDto itemResponseDto = itemService.viewItem(itemRequestDto);
            return new ResponseEntity (itemResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
