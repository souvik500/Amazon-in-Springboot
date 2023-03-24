package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.RequestDTO.RequestSellerDto;
import com.amazon.AmazonDataBase.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController
{
    @Autowired
    SellerService sellerService;


    @PostMapping("/addSeller")
    public ResponseEntity addSeller (@RequestBody RequestSellerDto requestSellerDto)
    {
        sellerService.addSeller(requestSellerDto);
        return new ResponseEntity(requestSellerDto.getName()+" Added, Now You can sell of your products",HttpStatus.CREATED);
    }
}
