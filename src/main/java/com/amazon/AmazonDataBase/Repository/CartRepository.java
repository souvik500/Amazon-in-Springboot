package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
