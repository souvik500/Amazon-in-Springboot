package com.amazon.AmazonDataBase.Model;


import com.amazon.AmazonDataBase.Enum.ProductCategory;
import com.amazon.AmazonDataBase.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Product
//{
//    @Id
//    @Column(name = "product_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private String name;
//
//    private int price;
//
//    private int quantity;
//
//    @Enumerated(EnumType.STRING)
//    private ProductCategory productCategory;
//
//    @Enumerated(EnumType.STRING)
//    private ProductStatus productStatus;
//    /**
//     * Relation b/w child (Product) to parent (Seller) [1 : Many]
//     */
//    @ManyToOne
//    @JoinColumn
//    Seller seller;
//
//    /**
//     * Relation b/w Parent (Product) to Child (Item) [1 : 1]
//     */
//    @OneToOne (mappedBy = "product" , cascade = CascadeType.ALL)
//    Item item;
//}
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private String productDetail;

    private String productDimension; // weight$height$width$length

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;

}