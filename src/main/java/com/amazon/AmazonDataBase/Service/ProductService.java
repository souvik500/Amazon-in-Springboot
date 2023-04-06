package com.amazon.AmazonDataBase.Service;

import com.amazon.AmazonDataBase.Convertor.ProductConverter;
import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Enum.ProductStatus;
import com.amazon.AmazonDataBase.Exception.CategoryNotFoundException;
import com.amazon.AmazonDataBase.Exception.DimensionParameterNullException;
import com.amazon.AmazonDataBase.Exception.ProductNotFoundException;
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
import java.util.NoSuchElementException;

@Service
public class ProductService
{
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;


    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException, DimensionParameterNullException {

        Seller seller;
        try{
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (NoSuchElementException e){
            throw new SellerNotFoundException("Enter a valid Seller Id!");
        }
        if(productRequestDto.getWeight() == 0 || productRequestDto.getHeight() == 0 || productRequestDto.getWidth() == 0 || productRequestDto.getLength() == 0){
            throw new DimensionParameterNullException("Dimension Parameters cannot be null");
        }

        Product product = ProductConverter.productRequestDtoToProduct(productRequestDto,seller);

        seller.getProductList().add(product);
        sellerRepository.save(seller);

        return ProductConverter.productToProductResponseDto(product);

    }


    public String updateProduct(int productId, ProductRequestDto productRequestDto) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }catch (NoSuchElementException e){
            throw new ProductNotFoundException("Product Id does not exit, Enter a valid Product Id!");
        }

        String newName;

        int newPrice;

        int newQuantity;

        ProductCategory newProductCategory;

        String newProductDetail;

        String newDimension;

        String message = "Details has been updated: \n";
        if(productRequestDto.getName() != null){
            newName = productRequestDto.getName();
            product.setName(newName);
            message += "Product Name :" + newName + "\n";
        }
        if(productRequestDto.getPrice() != 0){
            newPrice = productRequestDto.getPrice();
            product.setPrice(newPrice);
            message += "Product Price :" + newPrice + "\n";
        }
        if(productRequestDto.getQuantity() != 0){
            newQuantity = productRequestDto.getQuantity();
            product.setQuantity(newQuantity);
            message += "Product Quantity :" + newQuantity + "\n";
        }
        if(productRequestDto.getProductCategory()!= null){
            newProductCategory = productRequestDto.getProductCategory();
            product.setProductCategory(newProductCategory);
            message += "Product Category :" + newProductCategory + "\n";
        }
        if(productRequestDto.getProductDetail()!=null){
            newProductDetail = productRequestDto.getProductDetail();
            product.setProductDetail(newProductDetail);
            message += "Product Details :" + newProductDetail + "\n";
        }
        newDimension = getNewProductDimension(product,productRequestDto);
        if(newDimension != "$$$$"){
            product.setProductDimension(newDimension);
            message += "Product Dimension :" + newDimension + "\n";
        }

        productRepository.save(product);
        return message;

    }


    public List<ProductResponseDto> getAllProducts(int sellerId) throws SellerNotFoundException {
        Seller seller;
        try{
            seller = sellerRepository.findById(sellerId).get();
        }catch (Exception e){
            throw new SellerNotFoundException("Seller Id does not exist, Enter a valid Seller Id");
        }

        List<Integer> productIds = productRepository.findAllBySellerId(sellerId);

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Integer productId : productIds){
            ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDto(productRepository.findById(productId).get());
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }


    public void deleteProductById(int productId) throws ProductNotFoundException {
        Product product;
        try{
            product = productRepository.findById(productId).get();
        }catch (Exception e){
            throw new ProductNotFoundException("Product Id does not exist, Enter a valid product Id");
        }
    }


    private String getNewProductDimension(Product product, ProductRequestDto productRequestDto) {

        String productDimension = product.getProductDimension();// weight$height$width$length
        double weight = productRequestDto.getWeight();
        double height = productRequestDto.getHeight();
        double width = productRequestDto.getWidth();
        double length = productRequestDto.getLength();

        String[] dimension = productDimension.split("\\$");
        if(weight != 0)dimension[0] = String.valueOf(weight);
        if(height != 0)dimension[1] = String.valueOf(height);
        if(width != 0)dimension[2] = String.valueOf(width);
        if(length != 0)dimension[3] = String.valueOf(length);
        String dimes= "";
        for(int i =0; i < dimension.length; i++){
            dimes += dimension[i]+"$";
        }
        if(productDimension.equals(dimes)) return "$";

        return dimes;

    }

//    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException
//    {
//        Seller seller;
//
//        /**
//         * I need to find seller with his id, Is he exists or not
//         */
//        try {
//            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
//        }
//        catch (Exception e) {
//            throw new SellerNotFoundException("This Seller is not register, Pls Register First");
//        }
//
//        //If Seller Exists then add his/her products using Builder
//        //Product product = ProductConverter.productRequestDtoToProduct(productRequestDto);
//        Product product = Product.builder()
//                .name(productRequestDto.getProductName())
//                .price(productRequestDto.getPrice())
//                .productCategory(productRequestDto.getCategory())
//                .quantity(productRequestDto.getQuantity())
//                .productStatus(ProductStatus.AVAILABLE)
//                .build();
//        product.setSeller(seller);
//
//        seller.getProductList().add(product);
//
//        sellerRepository.save(seller);
//
//        //Prepare ProductResponseDto
//        ProductResponseDto productResponseDto = ProductConverter.productToProductResponseDto(product);
//
//        productResponseDto.setSellerName(seller.getName());
//
//        return productResponseDto;
//
//    }

    /*==================================Start===========================================*/
//    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
//
//        System.out.println("a:"+productRequestDto.getSellerId());
//        System.out.println("b:"+productRequestDto.getProductCategory());
//        System.out.println("c:"+productRequestDto.getProductName());
//        System.out.println("d:"+productRequestDto.getQuantity());
//        System.out.println("f:"+productRequestDto.getPrice());
//        Seller seller;
//
//        try{
//
//            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
//        }
//        catch(Exception e){
//            throw new SellerNotFoundException("Invalid Seller Id");
//        }
//
//        Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
//        product.setSeller(seller);
//
//        seller.getProductList().add(product);
//
//        // saved the seller and product - parent and child
//        sellerRepository.save(seller);
//
//        // prepare response
//        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
//        return productResponseDto;
//    }

    /*========================================End=================================*/

//    public List<ProductResponseDto> getProductByCategory(ProductCategory productCategory) throws CategoryNotFoundException {
//        List<Product> productList;
//        try {
//            productList = productRepository.findAllByProductCategory(productCategory);
//        }
//        catch (Exception e){
//            throw new CategoryNotFoundException("Category does not Exists");
//        }
//        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
//
//        for (Product product : productList)
//        {
//            ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
//
//            productResponseDtoList.add(productResponseDto);
//        }
//
//        return productResponseDtoList;
//    }

//    public ProductResponseDto addProduct(int sellerId, String productName, int price, int quantity, ProductCategory productCategory) throws SellerNotFoundException {
//
//        Seller seller;
//        try{
//
//            seller = sellerRepository.findById(sellerId).get();
//        }
//        catch(Exception e){
//            throw new SellerNotFoundException("Invalid Seller Id");
//        }
//
//        //Product product = ProductConvertor.productRequestDtoToProduct(productRequestDto);
//        Product product = Product.builder()
//                .name(productName)
//                .productStatus(ProductStatus.AVAILABLE)
//                .price(price)
//                .productCategory(productCategory)
//                .seller(seller)
//                .quantity(quantity)
//                .build();
//        product.setSeller(seller);
//
//        seller.getProductList().add(product);
//
//        // saved the seller and product - parent and child
//        sellerRepository.save(seller);
//
//        // prepare response
//        ProductResponseDto productResponseDto = ProductConvertor.productToProductResponseDto(product);
//        return productResponseDto;
//    }
}
