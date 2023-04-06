package com.amazon.AmazonDataBase.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Seller
//{
//    @Id
//    @Column(name = "seller_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//
//    private int age;
//
//    private String  email;
//
//    private Long mobileNo;
//
//    private String pan;
//
//    /**
//     * Relation b/w Parent (Seller) to Child (Product) [1 : Many]
//     */
//    @OneToMany (mappedBy = "seller" , cascade = CascadeType.ALL)
//    List<Product> productList = new ArrayList<>();
//}

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="seller")
public class Seller {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    //private String name;

    private int age;

    @Column(unique = true)
    private String mobileNo;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String panNo;

    private String sellerAddress;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();

}
