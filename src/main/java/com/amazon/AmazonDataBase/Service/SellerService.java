package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.SellerConverter;
import com.amazon.AmazonDataBase.Model.Seller;
import com.amazon.AmazonDataBase.Repository.SellerRepository;
import com.amazon.AmazonDataBase.RequestDTO.RequestSellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService
{
    @Autowired
    SellerRepository sellerRepository;


    public void addSeller(RequestSellerDto requestSellerDto)
    {
        Seller seller = SellerConverter.SellerRequestDtoToSeller(requestSellerDto);

        sellerRepository.save(seller);
    }
}
