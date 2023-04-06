package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
import com.amazon.AmazonDataBase.Model.Customer;
import com.amazon.AmazonDataBase.Model.Item;
import com.amazon.AmazonDataBase.Model.Product;
import com.amazon.AmazonDataBase.Repository.CustomerRepository;
import com.amazon.AmazonDataBase.Repository.ProductRepository;
import com.amazon.AmazonDataBase.RequestDTO.ItemRequestDto;
import com.amazon.AmazonDataBase.ResponseDTO.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService
{
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    CustomerRepository customerRepository;
//
//
//    public ItemResponseDto viewItem (ItemRequestDto itemRequestDto)
//    {
//        Customer customer;
//        try {
//            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
//        } catch (Exception e) {
//            throw new RuntimeException("Before viewing this product, You have to register first!!");
//        }
//
//        Product product = productRepository.findById(itemRequestDto.getProductId()).get();
//
//        Item item = Item.builder()
//                .product(product)
//                .build();
//
//        product.setItem(item);
//
//        productRepository.save(product);
//
//        //Prepare ItemResponseDto
//        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
//                .productName(product.getName())
//                .price(product.getPrice())
//                .productCategory(product.getProductCategory())
//                .customerName(customer.getName())
//                .build();
//
//        return itemResponseDto;
//    }

    @Autowired
    ProductRepository productRepository;

    public ItemResponseDto viewItem(int productId) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }
        catch (Exception e){
            throw new ProductNotFoundException("Sorry! Invalid product id.");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product)
                .build();

        // map item to product
        product.setItem(item);

        // save both item and product
        productRepository.save(product);

        // prepare the response dto
        ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();

        return itemResponseDto;
    }
}
