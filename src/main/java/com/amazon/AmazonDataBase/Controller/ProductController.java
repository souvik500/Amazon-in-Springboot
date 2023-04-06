package com.amazon.AmazonDataBase.Controller;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Exception.CategoryNotFoundException;
import com.amazon.AmazonDataBase.Exception.DimensionParameterNullException;
import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
import com.amazon.AmazonDataBase.Exception.SellerNotFoundException;
import com.amazon.AmazonDataBase.RequestDTO.ProductRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;
import com.amazon.AmazonDataBase.Service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    ProductService productService;


//    @PostMapping("/addProduct/{id}")
//    public ResponseEntity addProduct(@PathVariable("id") int sellerId,@RequestParam String productName, @RequestParam int price, @RequestParam int quantity, @RequestParam ProductCategory productCategory){
//
//        ProductResponseDto productResponseDto;
//        try{
//            //productResponseDto = productService.addProduct(productRequestDto);
//            productResponseDto = productService.addProduct(sellerId,productName, price, quantity, productCategory);
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity(productResponseDto,HttpStatus.ACCEPTED);
//    }


//    @GetMapping("/get/getProduct/{category}")
//    public List<ProductResponseDto> getProductByCategory (@PathVariable("category") ProductCategory productCategory) throws CategoryNotFoundException
//    {
//            return productService.getProductByCategory(productCategory);
//    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        ProductResponseDto productResponseDto;

        try{
            productResponseDto = productService.addProduct(productRequestDto);
        }catch (SellerNotFoundException | DimensionParameterNullException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") int productId, @RequestBody ProductRequestDto productRequestDto){
        String message;
        try{
            message = productService.updateProduct(productId,productRequestDto);
        }catch (ProductNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }

    // search product using regex

    @GetMapping("/productsBySellerId/{id}")//for seller
    public ResponseEntity getAllProductsForSeller(@PathVariable("id") int sellerId){
        List<ProductResponseDto> productList;
        try{
            productList =productService.getAllProducts(sellerId);
        }catch (SellerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList,HttpStatus.ACCEPTED);
    }

    // delete product
    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") int productId){
        try{
            productService.deleteProductById(productId);
            return "Product having product Id : " + productId + " has been deleted.";
        }catch (ProductNotFoundException e){
            return e.getMessage();
        }
    }
}
