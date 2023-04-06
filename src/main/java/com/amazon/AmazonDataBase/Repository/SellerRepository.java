package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    @Query(value = "select s.id from seller s",nativeQuery = true)
    List<Integer> findAllSeller();
}
