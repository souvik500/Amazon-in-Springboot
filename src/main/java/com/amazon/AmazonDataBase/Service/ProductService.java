package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.ProductConverter;
import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Exception.CategoryNotFoundException;
import com.amazon.AmazonDataBase.Exception.SellerNotFoundException;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.Model.Seller;
import com.amazon.AmazonDataBase.Repository.ProductRepository;
import com.amazon.AmazonDataBase.Repository.SellerRepository;
import com.amazon.AmazonDataBase.RequestDTO.ProductRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;


    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException
    {
        Seller seller;

        /**
         * I need to find seller with his id, Is he exists or not
         */
        try {
            seller = sellerRepository.findById(1).get();
        }
        catch (Exception e) {
            throw new SellerNotFoundException("This Seller is not register, Pls Register First");
        }

        //If Seller Exists then add his/her products using Builder
        Product product = ProductConverter.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProductList().add(product);

        sellerRepository.save(seller);

        //Prepare ProductResponseDto
        ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDto(product);

        productResponseDto.setSellerName(seller.getName());

        return productResponseDto;

    }


    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory) throws CategoryNotFoundException {
        List<Product> productList;
        try {
            productList = productRepository.findAllByProductCategory(productCategory);
        }
        catch (Exception e){
            throw new CategoryNotFoundException("Category does not Exists");
        }
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (Product product : productList)
        {
            ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDto(product);

            productResponseDtoList.add(productResponseDto);
        }

        return productResponseDtoList;
    }
}
