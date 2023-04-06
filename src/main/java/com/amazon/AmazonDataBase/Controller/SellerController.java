package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Exception.SellerNotFoundException;
import com.amazon.AmazonDataBase.RequestDTO.SellerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.SellerResponseDto;
import com.amazon.AmazonDataBase.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController
{
    @Autowired
    SellerService sellerService;


//    @PostMapping("/addSeller")
//    public ResponseEntity addSeller (@RequestBody RequestSellerDto requestSellerDto)
//    {
//        sellerService.addSeller(requestSellerDto);
//        return new ResponseEntity(requestSellerDto.getName()+" Added, Now You can sell of your products",HttpStatus.CREATED);
//    }

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try{
            sellerService.addSeller(sellerRequestDto);
        }catch (SQLException e){
            return e.getMessage();
        }
        return "Seller added";
    }

    @GetMapping("/getSellers")
    public List<SellerResponseDto> gelAllSeller(){
        List<SellerResponseDto> sellerResponseDtoList = sellerService.getAllSeller();
        return sellerResponseDtoList;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteSeller(int sellerId){
        String message;
        try{
            message = sellerService.deleteSeller(sellerId);
        }catch (SellerNotFoundException e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }
}
