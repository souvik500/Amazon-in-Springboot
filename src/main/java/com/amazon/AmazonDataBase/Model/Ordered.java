package com.amazon.AmazonDataBase.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordered
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String transactionId;

    @CreationTimestamp
    private Date orderDate;

    private int totalCost;

    private int deliveryCharge;

    private String cardUsedForPayment;

    // Relation b/w child (Order) to parent (Customer) [Many : 1]
    @ManyToOne
    @JoinColumn
    Customer customer;

    /**
     * Relation b/w Parent (Order) to Child (Item) [1 : Many]
     */
    @OneToMany(mappedBy = "ordered" , cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
}
