package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
