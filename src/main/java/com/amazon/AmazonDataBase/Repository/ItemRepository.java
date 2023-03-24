package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
