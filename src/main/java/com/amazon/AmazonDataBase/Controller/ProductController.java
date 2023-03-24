package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Exception.CategoryNotFoundException;
import com.amazon.AmazonDataBase.RequestDTO.ProductRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;
import com.amazon.AmazonDataBase.Service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;


    @PostMapping("/addProduct")
    public ResponseEntity addProduct (@RequestBody ProductRequestDto productRequestDto)
    {
        ProductResponseDto productResponseDto;
        try {
            productResponseDto = productService.addProduct(productRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/getProduct/{category}")
    public List<ProductResponseDto> getProductByCategory (@PathVariable("category") ProductCategory productCategory) throws CategoryNotFoundException
    {
            return productService.getProductByCategory(productCategory);
    }
}
