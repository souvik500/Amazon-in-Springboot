package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select c.id from customer c where c.email=:email",nativeQuery = true)
    public int findByEmail(String email);

    @Query(value = "select c.id from customer c where c.mobile_no=:mobileNo",nativeQuery = true)
    public int findByMobileNo(String mobileNo);

    @Query(value = "select c.id from customer c",nativeQuery = true)
    List<Integer> getAllCustomerIds();
}
