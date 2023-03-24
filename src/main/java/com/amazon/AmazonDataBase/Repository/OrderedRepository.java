package com.amazon.AmazonDataBase.Repository;

import com.amazon.AmazonDataBase.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {
}
