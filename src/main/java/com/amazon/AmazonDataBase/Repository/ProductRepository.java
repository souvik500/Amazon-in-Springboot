package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
   // List<Product> findAllByProductCategory(ProductCategory productCategory);

    @Query(value = "select p.id from product p where p.seller_id=:sellerId",nativeQuery = true)
    List<Integer> findAllBySellerId(int sellerId);
}
