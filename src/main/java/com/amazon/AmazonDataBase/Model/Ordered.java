package com.amazon.AmazonDataBase.Model;


import com.amazon.AmazonDataBase.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Ordered
//{
//    @Id
//    @Column(name = "order_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String transactionId;
//
//    @CreationTimestamp
//    private Date orderDate;
//
//    private int totalCost;
//
//    private int deliveryCharge;
//
//    private String cardUsedForPayment;
//
//    // Relation b/w child (Order) to parent (Customer) [Many : 1]
//    @ManyToOne
//    @JoinColumn
//    Customer customer;
//
//    /**
//     * Relation b/w Parent (Order) to Child (Item) [1 : Many]
//     */
//    @OneToMany(mappedBy = "ordered" , cascade = CascadeType.ALL)
//    List<Item> itemList = new ArrayList<>();
//}


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="ordered")
public class Ordered {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date orderDate;

    private String TransactionId;

    private OrderStatus status;

    private int totalCost;

    private int deliveryCharges;

    private String cardUsedForPayment;

    private String deliveryAddress;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();


}
