package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.ProductConverter;
import com.amazon.AmazonDataBase.Convertor.SellerConverter;
import com.amazon.AmazonDataBase.Exception.SellerNotFoundException;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.Model.Seller;
import com.amazon.AmazonDataBase.Repository.SellerRepository;
import com.amazon.AmazonDataBase.RequestDTO.SellerRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;
import com.amazon.AmazonDataBase.ResponseDTO.SellerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SellerService
{
    @Autowired
    SellerRepository sellerRepository;


    public void addSeller(SellerRequestDto sellerRequestDto) throws SQLException {
        Seller seller = SellerConverter.sellerRequestDtoToSeller(sellerRequestDto);

        try{
            sellerRepository.save(seller);
        }catch (Exception e){
            throw new SQLException("Seller already exist using this Mobile number or Email, or panNo!");
        }
    }


    public List<SellerResponseDto> getAllSeller() {
        List<Integer> sellerIds = sellerRepository.findAllSeller();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();

        for(Integer sellerId : sellerIds){
            Seller seller = sellerRepository.findById(sellerId).get();

            List<Product> productList = seller.getProductList();
            List<ProductResponseDto> responseDtoList = new ArrayList<>();
            for(Product product : productList){
                ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDto(product);
                responseDtoList.add(productResponseDto);
            }

            SellerResponseDto sellerResponseDto = SellerConverter.sellerTOSellerResponseDto(seller,responseDtoList);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }


    public String deleteSeller(int sellerId) throws SellerNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(sellerId).get();
        }catch (NoSuchElementException e){
            throw new SellerNotFoundException("Seller Id not found, Enter a valid Seller id");
        }
        sellerRepository.delete(seller);
        return "Seller with Seller Id " + sellerId + " has been deleted";
    }
}
